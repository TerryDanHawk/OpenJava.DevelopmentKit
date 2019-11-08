package com.openjava.mvc.service;

import com.openjava.mvc.mapper.UserMapper;
import com.openjava.mvc.property.FileProperties;
import com.openjava.mvc.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ExcelService {

    private final UserMapper dao;
    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public ExcelService(UserMapper dao, FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        this.dao = dao;
    }

    public void Import(String fileName) throws IOException {
        Path savelocation = this.fileStorageLocation.resolve(fileName);
        ExcelHelper.readExcel(savelocation.toAbsolutePath().toString());


    }



}
