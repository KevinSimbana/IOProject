package com.sparta.io.testing;

import com.sparta.io.control.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateFormatterTester {
    @BeforeAll
    void setup () {
        Date validDate = new Date(21/7/1998);
    }
    @Test
    public void givenDate_returnSQLDate(){
        java.sql.Date validDate = DateFormatter.tryDateFormatSql("12/7/1998");
        java.sql.Date date = new java.sql.Date(12/7/1998);
        Assertions.assertEquals(date,validDate);
    }
}
