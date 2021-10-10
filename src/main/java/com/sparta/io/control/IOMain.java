package com.sparta.io.control;

import com.sparta.io.model.Employee;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

//TODO: Implement JDBS creation and CRUD to instantiate database.
//TODO: Setup MySQL root account and build connection against that.
//TODO: Prepared statements (CRUD).

public class IOMain {
    public static void main(String[] args) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<Employee> specialEmployeeList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader((new FileReader("EmployeeRecords.csv")))) {
            String line = in.readLine();
            while((line = in.readLine())!= null) {
                String[] attributes = line.split(",");
                if (!DataChecker.isDataValid(attributes)) {
                    Employee employee = EmployeeCreator.tryCreateEmployee(attributes);
                    if (employeeList.contains(employee)) {
                        specialEmployeeList.add(employee);
                    }
                    employeeList.add(employee);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        employeeListDuplicateChecker(employeeList);

        System.out.println("Complete Employee records: " + employeeList.size());
        System.out.println("Special cases Employee records: " + specialEmployeeList.size());

    }
    //Double-check the validity of the method.
    public static void employeeListDuplicateChecker(ArrayList<Employee> employeeList) {
        HashSet<Employee> uniqueElements = new HashSet<>(employeeList);
        employeeList.clear();
        employeeList.addAll(uniqueElements);
    }

}
