package com.xiaoming.unit5.startup;

import com.xiaoming.unit5.core.SimpleWrapper;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class BootStrap2 {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

    }
}
