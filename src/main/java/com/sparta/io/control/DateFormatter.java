package com.sparta.io.control;

import java.sql.Date;

public class DateFormatter {
    public static Date tryDateFormatSql(String date) {
        java.time.format.DateTimeFormatter df = java.time.format.DateTimeFormatter.ofPattern("M/d/yyyy");
        return java.sql.Date.valueOf(java.time.LocalDate.parse(date, df));
    }
}
