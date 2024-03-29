package com.openjava.mvc.controller;

import com.openjava.mvc.service.ExcelService;
import com.openjava.mvc.service.FileService;
import com.openjava.mvc.util.ErrorInformation;
import com.openjava.mvc.util.FileException;
import com.openjava.mvc.util.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;


@RestController
public class ExcelController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ExcelService excelService;

    @PostMapping("/excelImport")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("year") String year, @RequestParam("month") String month,@RequestParam("staff_headcount") double staff_headcount,@RequestParam("secondee_headcount") double secondee_headcount, @RequestParam("exchangerate") double exchangerate){
        try {
            String fileName = fileService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();
            excelService.Import(fileName,year,month,exchangerate,staff_headcount,secondee_headcount);

            return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        }
        catch (IOException ex)
        {
            return new UploadFileResponse(ex.getMessage(),"","error",0);

        }
    }
}
