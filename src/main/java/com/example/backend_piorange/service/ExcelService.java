package com.example.backend_piorange.service;


import com.example.backend_piorange.entity.User;
import com.example.backend_piorange.repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelService {

    @Autowired
    private UserRepository userRepository;

    public void importExcelData(String filePath) throws IOException {
        // Load Excel file
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0); // Use the first sheet

        // Iterate through rows
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Skip the header row
            if (row.getRowNum() == 0) {
                continue;
            }

            // Get cell values
            Cell phoneNumberCell = row.getCell(0);
            Cell passwordCell = row.getCell(1);

            String phoneNumber = phoneNumberCell.getStringCellValue();
            String password = passwordCell.getStringCellValue();

            // Save to database
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);

            userRepository.save(user);
        }

        workbook.close();
        fileInputStream.close();
    }
}

