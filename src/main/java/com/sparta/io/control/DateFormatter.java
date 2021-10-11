package com.sparta.io.control;

import org.apache.log4j.Logger;

import java.sql.Date;

public class DateFormatter {
    private static Logger logger = Logger.getLogger("IO Application");

    public static Date tryDateFormatSql(String date) {
        logger.info("date has requested to be reformatted for SQL implementation.");
        java.time.format.DateTimeFormatter df = java.time.format.DateTimeFormatter.ofPattern("M/d/yyyy");
        return java.sql.Date.valueOf(java.time.LocalDate.parse(date, df));
    }
}
