package com.sparta.io.view;

import com.sparta.io.control.EmployeeSQLServerDAO;
import com.sparta.io.model.Employee;
import com.sparta.io.model.EmployeeDAO;

import java.sql.Date;

public class DAODriver {
    public static void main(String[] args) {
        EmployeeDAO obj = new EmployeeSQLServerDAO();

        Date dob = new Date(21/9/1998);
        Date doj = new Date(1/2/2021);

        Employee emp1 = new Employee(69, "Doc.", "Kevin", "M", "Simbana",
                true, "kevin@hotmail.co.uk", dob, doj, 5000);

        obj.deleteEmployee(emp1);
    }
}
