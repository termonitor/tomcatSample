package com.xiaoming.unit20.standardmbeantest;

/**
 * Created by panxiaoming on 15/12/27.
 */
public class Car implements CarMBean {
    private String color = "red";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void drive() {
        System.out.println("Baby you can drive my car.");
    }
}
