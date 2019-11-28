package com.openjava.mvc.service;

import com.openjava.mvc.mapper.*;
import com.openjava.mvc.model.*;
import com.openjava.mvc.property.FileProperties;
import com.openjava.mvc.util.ExcelHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class ExcelService {

    private final PAndLDataMapper pldao;
    private final ExchangeRateMapper exdao;
    private final PAndLManualInputMapper plmdao;
    private final YTDTrendMapper ytddao;
    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public ExcelService(PAndLDataMapper _pldao, ExchangeRateMapper _exdao, PAndLManualInputMapper _plmdao, YTDTrendMapper _ytddao, FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        this.pldao = _pldao;
        this.exdao=_exdao;
        this.plmdao=_plmdao;
        this.ytddao=_ytddao;

    }

    public void Import(String fileName,String year,String month,double exchangerate,double staff_headcount,double secondee_headcount) throws IOException {
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
                    model.setMonthActual(row.getCell(1).getNumericCellValue());
                    model.setYTDActual(row.getCell(2).getNumericCellValue());
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
            {
                PAndLManualInputModel model = new PAndLManualInputModel();
                model.setYear(year);
                model.setMonth(month);
                model.setItem("Staff");
                model.setValue(staff_headcount);
                this.plmdao.insert(model);
            }
            {

                PAndLManualInputModel model = new PAndLManualInputModel();
                model.setYear(year);
                model.setMonth(month);
                model.setItem("Secondee");
                model.setValue(secondee_headcount);
                this.plmdao.insert(model);
            }
        }
        {
            this.exdao.delete(year,month);
            ExchangeRateModel model=new ExchangeRateModel();
            model.setYear(year);
            model.setMonth(month);
            model.setFmCurr("CNY");
            model.setToCurr("SGD");
            model.setCategory("PRate");
            model.setRate(exchangerate);
            this.exdao.insert(model);
        }
        //计算并保存数据
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Intercompany Revenue excluding mark-up");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"         50141  Interco IT Services").getYTDActual()/1000.0-
                             this.pldao.GetItem(year,month,"         50142  Interco BPO Services").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Staff Costs");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"       Staff Cost").getYTDActual()/1000.0-
                    this.pldao.GetItem(year,month,"         61281  Rebill of MP Costs").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Less: Rebilling and grants received");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"         61281  Rebill of MP Costs").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);
        }
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Cost Of Sales - Manpower");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"       Total Cost of Sales (COS/MP/Costs Recove").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Depreciation & Amortisation");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Depreciation").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }
        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Administrative");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Administrative").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }

        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Repair & Maint.");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Repair & Maintenance").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }

        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Property Related");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Property Related").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }

        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("gain/(loss) of Realised / Unreal exchange difference (Trade)");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Other Operating Expenses").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }


        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Interest income");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Finance Income").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }

        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Interest expense");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"        Finance Cost").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }


        {
            YTDTrendModel model=new YTDTrendModel();
            model.setItemName("Exceptional items");
            model.setYear(year);
            model.setMonth(month);
            double value=this.pldao.GetItem(year,month,"         61502  Special Release Scheme Payment").getYTDActual()/1000.0;
            model.setYTDMoney(value);
            this.ytddao.insert(model);

        }


    }



}
