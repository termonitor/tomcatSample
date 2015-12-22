package com.xiaoming.unit15.startup;

import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;

/**
 * Created by panxiaoming on 15/12/22.
 */
public class BootStrap {
    public static void main(String[] args) {
        System.setProperty("catalina.base", System.getProperty("user.dir"));
        Connector connector = new HttpConnector();

        Context context = new StandardContext();
        context.setPath("/app1");
        context.setDocBase("app1");
        LifecycleListener listener = new ContextConfig();
        ((Lifecycle) context).addLifecycleListener(listener);
        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        host.setAppBase("webapps");

        Loader loader = new WebappLoader();
        context.setLoader(loader);
        connector.setContainer(host);
        try {
            connector.initialize();
            ((Lifecycle) connector).start();
            ((Lifecycle) host).start();
            Container[] containers = context.findChildren();
            int length = containers.length;
            for(int i=0; i<length; i++) {
                Container child = containers[i];
                System.out.println(child.getName());
            }

            System.in.read();
            ((Lifecycle) host).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
