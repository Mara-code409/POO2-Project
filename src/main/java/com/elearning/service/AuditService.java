package com.elearning.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuditService {

    private static final String CSV_PATH = "audit.csv";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static AuditService instance;

    public static AuditService getInstance() {
        if (instance == null) instance = new AuditService();
        return instance;
    }

    public void log(String actiune) {
        String timestamp = LocalDateTime.now().format(FMT);
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH, true))) {
            pw.println(actiune + "," + timestamp);
        } catch (IOException e) {
            System.err.println("Audit write failed: " + e.getMessage());
        }
    }
}
