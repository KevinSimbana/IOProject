package com.sparta.io.testing;

import com.sparta.io.control.DataChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.channels.AsynchronousServerSocketChannel;

public class DataCheckerTester {
    @Test
    public void givenInvalidPrefix_returnFalse() {
        String[] metadata = {"10","world"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenInvalidFirstName_returnFalse() {
        String[] metadata = {"11","Mr.","123"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenInvalidLastName_returnFalse() {
        String[] metadata = {"12","Mrs.","Kate","D","!@hi"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenInvalidGender_returnFalse() {
        String[] metadata = {"14","Mr.","Kevin","M","Sim","turtle"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenInvalidEmail_returnFalse() {
        String[] metadata = {"15","Doc.","Kate","D","Silva","F","kate@hello"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenInvalidDOB_returnFalse() {
        String[] metadata = {"15","Doc.","Kate","D","Silva","F","kate@gmail.com","12/07/1998","25/12/2021","-1"};
        Assertions.assertFalse(DataChecker.isDataValid(metadata));
    }

    @Test
    public void givenValidData_returnTrue() {
        String[] metadata = {"15","Doc.","Kate","D","Silva","F","kate@gmail.com","12/07/1998","25/12/2021","15000"};
        Assertions.assertTrue(DataChecker.isDataValid(metadata));
    }
}
