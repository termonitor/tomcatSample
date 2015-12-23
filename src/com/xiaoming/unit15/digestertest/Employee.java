package com.xiaoming.unit15.digestertest;

import java.util.ArrayList;

/**
 * Created by panxiaoming on 15/12/23.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private ArrayList offices = new ArrayList();

    public Employee() {
        System.out.println("Creating Employee");
    }

    public String getFirstName() {
        System.out.println("Setting firstName : " + firstName);
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.println("Setting lastName : " + lastName);
        this.lastName = lastName;
    }

    public ArrayList getOffices() {
        return offices;
    }

    public void addOffice(Office office) {
        System.out.println("Adding Office to this employee");
        offices.add(office);
    }

    public void printName() {
        System.out.println("My name is " + firstName + " " + lastName);
    }

}
