package com.sparta.io.model;
//equals method
//import java.util.Date;
import java.sql.Date;
import java.util.Objects;

public class Employee {
    private int id;
    private String prefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private boolean gender;
    private String email;
    private Date dob;
    private Date doj;
    private int salary;

    public Employee(int id, String prefix, String firstName, String middleInitial,
                    String lastName, boolean gender, String email,
                    Date dob, Date doj, int salary) {
        this.id = id;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.doj = doj;
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        if (gender){
            return "M";
        } else {
            return "F";
        }
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    public Date getDoj() {
        return doj;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", doj=" + doj +
                ", salary=" + salary +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
