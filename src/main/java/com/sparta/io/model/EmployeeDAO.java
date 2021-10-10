package com.sparta.io.model;

import java.util.ArrayList;

public interface EmployeeDAO {
    public ArrayList<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public void insertEmployee(Employee emp);
    public void updateEmployee(Employee emp);
    public void deleteEmployee(Employee emp);
}
