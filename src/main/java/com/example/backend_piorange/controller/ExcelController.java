package com.example.backend_piorange.controller;


import com.example.backend_piorange.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/import")
    public String importData(@RequestParam("filePath") String filePath) {
        try {
            excelService.importExcelData(filePath);
            return "Excel data imported successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to import data.";
        }
    }
}
