package com.xiaoming.unit5.startup;

import com.xiaoming.unit5.core.SimpleLoader;
import com.xiaoming.unit5.core.SimpleWrapper;
import com.xiaoming.unit5.valve.ClientIPLoggerValve;
import com.xiaoming.unit5.valve.HeaderLoggerValve;
import org.apache.catalina.Loader;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
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
        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();
        wrapper.setLoader(loader);
        ((Pipeline) wrapper).addValve(valve1);
        ((Pipeline) wrapper).addValve(valve2);
        connector.setContainer(wrapper);

        try{
            connector.initialize();
            connector.start();

            System.in.read();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
