package com.xiaoming.unit3;

/**
 * Created by panxiaoming on 15/12/3.
 */
public final class BootStrap {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
