package com.xiaoming.unit5.startup;

import com.xiaoming.unit5.core.SimpleContext;
import com.xiaoming.unit5.core.SimpleContextMapper;
import com.xiaoming.unit5.core.SimpleLoader;
import com.xiaoming.unit5.core.SimpleWrapper;
import com.xiaoming.unit5.valve.ClientIPLoggerValve;
import com.xiaoming.unit5.valve.HeaderLoggerValve;
import org.apache.catalina.*;
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

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();

        ((Pipeline) context).addValve(valve1);
        ((Pipeline) context).addValve(valve2);

        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        context.addMapper(mapper);
        Loader loader = new SimpleLoader();
        context.setLoader(loader);

        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");
        connector.setContainer(context);
        try {
            connector.initialize();
            connector.start();

            System.in.read();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
