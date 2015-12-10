package com.xiaoming.unit4;

import com.xiaoming.unit3.http.HttpConnector;

/**
 * Created by panxiaoming on 15/12/10.
 */
public class BootStrap {
    public static void main(String[] args) {
        //调用的是tomcat默认的连接器，只不过这里替换掉了它默认servlet容器。具体还是跟unit3中的差不多。
        org.apache.catalina.connector.http.HttpConnector httpConnector = new org.apache.catalina.connector.http.HttpConnector();
        SimpleContainer simpleContainer = new SimpleContainer();
        httpConnector.setContainer(simpleContainer);
        try {
            httpConnector.initialize();
            httpConnector.start();

            System.in.read();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
