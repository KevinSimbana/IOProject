package com.sparta.io.control;

import com.google.protobuf.EmptyOrBuilder;
import com.sparta.io.model.Connector;
import com.sparta.io.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyRunnable implements Runnable{
    private int runnableID;
    private PreparedStatement preparedStatement;
    private List<Employee> employeeList;

    public void doStatement(){
        System.out.println("starting statement from id: " + runnableID);
        try {
            Connector connector = Connector.getInstance();
            //Connection connection = connector.getConnection();

            for (Employee emp : employeeList) {
                preparedStatement.setInt(1, emp.getId());
                preparedStatement.setString(2, emp.getPrefix());
                preparedStatement.setString(3, emp.getFirstName());
                preparedStatement.setString(4, emp.getMiddleInitial());
                preparedStatement.setString(5, emp.getLastName());
                preparedStatement.setString(6, emp.getGender());
                preparedStatement.setString(7, emp.getEmail());
                preparedStatement.setDate(8, emp.getDob());
                preparedStatement.setDate(9, emp.getDoj());
                preparedStatement.setInt(10, emp.getSalary());
                preparedStatement.execute();
            }
            connector.getConnection().setAutoCommit(false);
            connector.getConnection().commit();
            System.out.println("finishing statement form id: " + runnableID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public void setRunnableID(int runnableID) {
        this.runnableID = runnableID;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    @Override
    public void run() {
        doStatement();
    }
}
