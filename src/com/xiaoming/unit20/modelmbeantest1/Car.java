package com.xiaoming.unit20.modelmbeantest1;

/**
 * Created by panxiaoming on 15/12/25.
 */
public class Car {

    private String color = "red";

    private String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void drive() {
        System.out.println("Baby you can drive my car.");
    }
}
