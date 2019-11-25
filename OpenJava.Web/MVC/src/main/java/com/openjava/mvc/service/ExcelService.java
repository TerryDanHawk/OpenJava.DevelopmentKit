package com.openjava.mvc.service;

import com.openjava.mvc.mapper.ExchangeRateMapper;
import com.openjava.mvc.mapper.PAndLDataMapper;
import com.openjava.mvc.mapper.PAndLManualInputMapper;
import com.openjava.mvc.mapper.UserMapper;
import com.openjava.mvc.model.ExchangeRateModel;
import com.openjava.mvc.model.PAndLDataModel;
import com.openjava.mvc.model.PAndLManualInputModel;
import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.property.FileProperties;
import com.openjava.mvc.util.ExcelHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class ExcelService {

    private final PAndLDataMapper pldao;
    private final ExchangeRateMapper exdao;
    private final PAndLManualInputMapper plmdao;
    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public ExcelService(PAndLDataMapper _pldao, ExchangeRateMapper _exdao, PAndLManualInputMapper _plmdao, FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        this.pldao = _pldao;
        this.exdao=_exdao;
        this.plmdao=_plmdao;

    }

    public void Import(String fileName,String year,String month,BigDecimal exchangerate,BigDecimal headcount) throws IOException {
        Path savelocation = this.fileStorageLocation.resolve(fileName);
        String excelName=savelocation.toAbsolutePath().toString();
        //将文件读入
        InputStream in  = new FileInputStream(new File(excelName));
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //将所选文件数据存储进数据库
        {
            //读取第一个sheet PAndLData
            Sheet sheet = wb.getSheetAt(0);
            int maxrow = sheet.getPhysicalNumberOfRows();
            this.pldao.delete(year,month);
            for (int i = 1; i < maxrow; i++) {
                try {


                    Row row = sheet.getRow(i);
                    PAndLDataModel model = new PAndLDataModel();
                    model.setYear(year);
                    model.setMonth(month);
                    model.setAccountDescription(row.getCell(0).getStringCellValue());
                    model.setMonthActual(new BigDecimal(row.getCell(1).getNumericCellValue()));
                    model.setYTDActual(new BigDecimal(row.getCell(2).getNumericCellValue()));
                    this.pldao.insert(model);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        {
            this.plmdao.delete(year,month);
            PAndLManualInputModel model=new PAndLManualInputModel();
            model.setYear(year);
            model.setMonth(month);
            model.setItem("HeadCount");
            model.setValue(headcount);
            this.plmdao.insert(model);
        }
        {
            this.exdao.delete(year,month);
            ExchangeRateModel model=new ExchangeRateModel();
            model.setYear(year);
            model.setMonth(month);
            model.setFmCurr("CNY");
            model.setToCurr("");
            model.setCategory("PRate");
            model.setRate(exchangerate);
            this.exdao.insert(model);
        }




    }



}
