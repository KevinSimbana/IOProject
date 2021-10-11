package com.sparta.io.control;

import com.sparta.io.model.Employee;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;

public class EmployeeCreator {
    private static Logger logger = Logger.getLogger("IO Application");

    public static Employee tryCreateEmployee(String[] metadata) throws ParseException {
        int id = Integer.parseInt(metadata[0]);
        String prefix = metadata[1];
        String firstName = metadata[2];
        String middleInitial = metadata[3];
        String lastName = metadata[4];
        boolean gender = metadata[5].equalsIgnoreCase("m");
        String email = metadata[6];
        Date dob = DateFormatter.tryDateFormatSql(metadata[7]);
        Date doj = DateFormatter.tryDateFormatSql(metadata[8]);
        int salary = Integer.parseInt(metadata[9]);

        logger.info("employee object created for given record in file.");

        return new Employee(id, prefix, firstName, middleInitial, lastName,
                gender, email, dob, doj, salary);
    }
}
