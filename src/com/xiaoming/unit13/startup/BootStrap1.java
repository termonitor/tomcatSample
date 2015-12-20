package com.xiaoming.unit13.startup;

import com.xiaoming.unit11.core.SimpleContextConfig;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;

/**
 * Created by panxiaoming on 15/12/20.
 */
public class BootStrap1 {
    public static void main(String[] args) {
        System.setProperty("catalina.base", System.getProperty("user.dir"));
        Connector connector = new HttpConnector();

        Wrapper wrapper1 = new StandardWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new StandardWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new StandardContext();
        context.setPath("/app1");
        context.setDocBase("app1");
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        LifecycleListener listener = new SimpleContextConfig();
        ((Lifecycle) context).addLifecycleListener(listener);

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        context.addServletMapping("/Primitive", "Primitive");
        context.addServletMapping("/Modern", "Modern");

        connector.setContainer(host);
        try {
            connector.initialize();
            ((Lifecycle) connector).start();
            ((Lifecycle) host).start();

            System.in.read();
            ((Lifecycle) host).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
