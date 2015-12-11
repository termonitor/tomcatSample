package com.xiaoming.unit5.startup;

import com.xiaoming.unit5.core.SimpleLoader;
import com.xiaoming.unit5.core.SimpleWrapper;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class BootStrap1 {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper = new SimpleWrapper();
        wrapper.setServletClass("ModernServlet");
        Loader loader = new SimpleLoader();
    }
}
