package com.sparta.io.model;

import java.util.ArrayList;

public interface EmployeeDAO {
    //TODO: complete DAO interface implementation.
    //maybe have access to connection object.
    //also to think about: creation of connection object?? (maybe)
        //whether this is included here or in its own interface.
    public ArrayList<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public void insertEmployee(Employee e);
    public void updateEmployee(Employee e);
    public void deleteEmployee(Employee e);
}
