package com.openjava.mvc.service;

import com.openjava.mvc.mapper.ExchangeRateMapper;
import com.openjava.mvc.mapper.PAndLDataMapper;
import com.openjava.mvc.mapper.UserMapper;
import com.openjava.mvc.model.ExchangeRateModel;
import com.openjava.mvc.model.PAndLDataModel;
import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.property.FileProperties;
import com.openjava.mvc.util.ExcelHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ExcelService {

    private final PAndLDataMapper pldao;
    private final ExchangeRateMapper exdao;
    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public ExcelService(PAndLDataMapper _pldao,ExchangeRateMapper _exdao, FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        this.pldao = _pldao;
        this.exdao=_exdao;

    }

    public void Import(String fileName,String year,String month) throws IOException {
        Path savelocation = this.fileStorageLocation.resolve(fileName);
        String excelName=savelocation.toAbsolutePath().toString();

//        PAndLDataModel tmodel = new PAndLDataModel();
//        tmodel.setYear("2019");
//        tmodel.setMonth("11");
//        tmodel.setAccountDescription("Test Item");
//        tmodel.setMonthActual(new BigDecimal(100));
//        tmodel.setYTDActual(new BigDecimal(20));
//        this.pldao.insert(tmodel);

        //将文件读入
        InputStream in  = new FileInputStream(new File(excelName));
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //将所选文件数据存储进数据库
        {
            //读取第一个sheet PAndLData
            Sheet sheet = wb.getSheetAt(0);
            int maxrow = sheet.getPhysicalNumberOfRows();
            pldao.delete(year,month);
            for (int i = 1; i < maxrow; i++) {
                Row row = sheet.getRow(i);
                PAndLDataModel model = new PAndLDataModel();
                model.setYear(year);
                model.setMonth(month);
                model.setAccountDescription(row.getCell(0).getStringCellValue());
                model.setMonthActual(new BigDecimal(row.getCell(1).getNumericCellValue()));
                model.setYTDActual(new BigDecimal(row.getCell(2).getNumericCellValue()));
                pldao.insert(model);
            }
        }
        {
            //读取第二个sheet ExchangeRate
            Sheet sheet = wb.getSheetAt(1);
            int maxrow = sheet.getPhysicalNumberOfRows();
            exdao.delete(year,month);
            for (int i = 1; i < maxrow; i++) {
                Row row = sheet.getRow(i);
                ExchangeRateModel model = new ExchangeRateModel();
                model.setYear(year);
                model.setMonth(month);
                model.setFmCurr(row.getCell(0).getStringCellValue());
                model.setToCurr(row.getCell(1).getStringCellValue());
                model.setCategory(row.getCell(2).getStringCellValue());
//                row.getCell(3).setCellType(CellType.STRING);
//                row.getCell(4).setCellType(CellType.STRING);
//                row.getCell(5).setCellType(CellType.STRING);
//                row.getCell(6).setCellType(CellType.STRING);
//                row.getCell(7).setCellType(CellType.STRING);
//                row.getCell(8).setCellType(CellType.STRING);
//                row.getCell(9).setCellType(CellType.STRING);
//                row.getCell(10).setCellType(CellType.STRING);
//                row.getCell(11).setCellType(CellType.STRING);
//                row.getCell(12).setCellType(CellType.STRING);
//                row.getCell(13).setCellType(CellType.STRING);
//                row.getCell(14).setCellType(CellType.STRING);
                model.setRate1(new BigDecimal(row.getCell(3).getNumericCellValue()));
                model.setRate2(new BigDecimal(row.getCell(4).getNumericCellValue()));
                model.setRate3(new BigDecimal(row.getCell(5).getNumericCellValue()));
                model.setRate4(new BigDecimal(row.getCell(6).getNumericCellValue()));
                model.setRate5(new BigDecimal(row.getCell(7).getNumericCellValue()));
                model.setRate6(new BigDecimal(row.getCell(8).getNumericCellValue()));
                model.setRate7(new BigDecimal(row.getCell(9).getNumericCellValue()));
                model.setRate8(new BigDecimal(row.getCell(10).getNumericCellValue()));
                model.setRate9(new BigDecimal(row.getCell(11).getNumericCellValue()));
                model.setRate10(new BigDecimal(row.getCell(12).getNumericCellValue()));
                model.setRate11(new BigDecimal(row.getCell(13).getNumericCellValue()));
                model.setRate12(new BigDecimal(row.getCell(14).getNumericCellValue()));
                exdao.insert(model);
            }

        }


    }



}
