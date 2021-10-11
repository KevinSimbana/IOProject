package com.sparta.io.control;

import com.sparta.io.model.Employee;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class IOMain {
    private static Logger logger = Logger.getLogger("IO Application");

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<Employee> specialEmployeeList = new ArrayList<>();

        //Time for Large record (no threads): 1:25
        //Time 2 Threads: 1:10
        //Time 4 Threads: 1:30?
        try (BufferedReader in = new BufferedReader((new FileReader("EmployeeRecordsLarge.csv")))) {
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
            logger.error("Initial attempt to read file has caused an error. File does not exist.");
        }

        employeeListDuplicateChecker(employeeList);

        System.out.println("Complete Employee records: " + employeeList.size());
        System.out.println("Special cases Employee records: " + specialEmployeeList.size());


        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:employee.db")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES; " +
                    "CREATE TABLE \"EMPLOYEES\" " +
                    "(\"ID\" INTEGER NOT NULL, " +
                    "\"PREFIX\" VARCHAR(6) NOT NULL, " +
                    "\"FIRST_NAME\" VARCHAR(50), " +
                    "\"MIDDLE_INITIAL\" VARCHAR(1), " +
                    "\"LAST_NAME\" VARCHAR(100), " +
                    "\"GENDER\" VARCHAR(1), " +
                    "\"EMAIL\" VARCHAR(200), " +
                    "\"DOB\" DATE NOT NULL, " +
                    "\"DOJ\" DATE NOT NULL, " +
                    "\"SALARY\" INTEGER)");


            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO EMPLOYEES " +
                            "(ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, GENDER, EMAIL, DOB, DOJ, SALARY)" +
                            "VALUES(?,?,?,?,?,?,?,?,?,?)");

            int half = employeeList.size()/2;
            int quater = half/2;
            //Creation of threads -> do prepared statement
            MyRunnable instance1 = new MyRunnable();
            instance1.setRunnableID(1);
            instance1.setPreparedStatement(preparedStatement);
            instance1.setEmployeeList(employeeList.subList(0, quater));
            Thread t1 = new Thread(instance1);
            t1.start();

            MyRunnable instance2 = new MyRunnable();
            instance2.setRunnableID(2);
            instance2.setPreparedStatement(preparedStatement);
            instance2.setEmployeeList(employeeList.subList(quater, half));
            Thread t2 = new Thread(instance2);
            t2.start();

            MyRunnable instance3 = new MyRunnable();
            instance3.setRunnableID(3);
            instance3.setPreparedStatement(preparedStatement);
            instance3.setEmployeeList(employeeList.subList(half, half+quater));
            Thread t3 = new Thread(instance3);
            t3.start();

            MyRunnable instance4 = new MyRunnable();
            instance4.setRunnableID(4);
            instance4.setPreparedStatement(preparedStatement);
            instance4.setEmployeeList(employeeList.subList(half+quater, employeeList.size()));
            Thread t4 = new Thread(instance4);
            t4.start();

            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("an interruption call has stopped a thread");
            }



//            for (Employee emp : employeeList) {
//                preparedStatement.setInt(1, emp.getId());
//                preparedStatement.setString(2, emp.getPrefix());
//                preparedStatement.setString(3, emp.getFirstName());
//                preparedStatement.setString(4, emp.getMiddleInitial());
//                preparedStatement.setString(5, emp.getLastName());
//                preparedStatement.setString(6, emp.getGender());
//                preparedStatement.setString(7, emp.getEmail());
//                preparedStatement.setDate(8, emp.getDob());
//                preparedStatement.setDate(9, emp.getDoj());
//                preparedStatement.setInt(10, emp.getSalary());
//                preparedStatement.execute();
//            }
//            connection.setAutoCommit(false);
//            connection.commit();



        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error("an error has occurred during a database access");
        }

    }
    //Double-check the validity of the method.
    public static void employeeListDuplicateChecker(ArrayList<Employee> employeeList) {
        HashSet<Employee> uniqueElements = new HashSet<>(employeeList);
        employeeList.clear();
        employeeList.addAll(uniqueElements);
    }

}
