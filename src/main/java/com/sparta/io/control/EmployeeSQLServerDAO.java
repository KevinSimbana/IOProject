package com.sparta.io.control;

import com.sparta.io.model.Employee;
import com.sparta.io.model.EmployeeDAO;
import com.sparta.io.model.Connector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeSQLServerDAO implements EmployeeDAO {
    private static Logger logger = Logger.getLogger("IO Application");

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployee(int id) {
        try {
            Connector connector = Connector.getInstance();
            Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEES WHERE ID = " + id);
            System.out.println(
                    "ID: " + rs.getInt("ID") + ", " +
                    "Name: " + rs.getString("PREFIX") + " " +
                            rs.getString("FIRST_NAME") + " " +
                            rs.getString("MIDDLE_INITIAL") + " " +
                            rs.getString("LAST_NAME") + ", " +
                    "Email: " + rs.getString("EMAIL") + ", " +
                    "DOB: " + rs.getDate("DOB") + ", " +
                    "DOJ: " + rs.getDate("DOJ") + ", " +
                    "Salary: " + rs.getInt("SALARY"));
            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error("an error has occurred during a database access");
        }
        return null;
    }

    @Override
    public void insertEmployee(Employee emp) {
        try {
            Connector connector = Connector.getInstance();
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEES " +
                    "(ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, GENDER, EMAIL, DOB, DOJ, SALARY)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)");

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
            connection.setAutoCommit(false);
            connection.commit();
            connection.close();
            System.out.println("Employee added to the database!");

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error("an error has occurred during a database access");
        }

    }

    @Override
    public void updateEmployee(Employee e) {

    }

    @Override
    public void deleteEmployee(Employee emp) {
        try {
            Connector connector = Connector.getInstance();
            Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM EMPLOYEES " +
                    "WHERE ID = " + emp.getId());

            statement.close();
            connection.close();
            System.out.println("Employee deleted from the database!");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            logger.error("an error has occurred during a database access");
        }

    }
}
