package com.xiaoming.unit15.digestertest;

/**
 * Created by panxiaoming on 15/12/23.
 */
public class Address {
    private String streetName;
    private String streetNumber;

    public Address() {
        System.out.println("Creating Address");
    }

    public void setStreetName(String streetName) {
        System.out.println("....Setting streetName : " + streetName);
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        System.out.println("....Setting streetNumber : " + streetNumber);
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String toString() {
        return "...." + streetNumber + " " + streetName;
    }
}
