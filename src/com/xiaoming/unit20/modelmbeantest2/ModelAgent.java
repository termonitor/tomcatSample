package com.xiaoming.unit20.modelmbeantest2;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by panxiaoming on 15/12/26.
 */
public class ModelAgent {
    private Registry registry;
    private MBeanServer mBeanServer;

    public ModelAgent() {
        registry = createRegistry();
        try {
            mBeanServer = Registry.getServer();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public MBeanServer getmBeanServer() {
        return mBeanServer;
    }

    public Registry createRegistry() {
        Registry registry = null;
        try {
            URL url = ModelAgent.class.getResource("car-mbean-descriptor.xml");
            InputStream stream = url.openStream();
            Registry.loadRegistry(stream);
            stream.close();
            registry = Registry.getRegistry();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return registry;
    }

    public ModelMBean createModelMBean(String mBeanName) throws Exception {
        ManagedBean managed = registry.findManagedBean(mBeanName);
        if(managed == null) {
            System.out.println("ManagedBean null");
            return null;
        }
        ModelMBean mbean = managed.createMBean();
        ObjectName objectName =createObjectName();
        return mbean;
    }

    public ObjectName createObjectName() {
        ObjectName objectName = null;
        String domain = mBeanServer.getDefaultDomain();
            try {
            objectName = new ObjectName(domain + ":type=MyCar");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return objectName;
    }

    public static void main(String[] args) {
        ModelAgent agent = new ModelAgent();
        MBeanServer mBeanServer = agent.getmBeanServer();
        Car car = new Car();
        System.out.println("Creating ObjectName");
        ObjectName objectName = agent.createObjectName();
        try {
            ModelMBean modelMBean = agent.createModelMBean("myMBean");
            modelMBean.setManagedResource(car, "ObjectReference");
            mBeanServer.registerMBean(modelMBean, objectName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Attribute attribute = new Attribute("Color", "green");
            mBeanServer.setAttribute(objectName, attribute);
            String color = (String) mBeanServer.getAttribute(objectName, "Color");
            System.out.println("Color:" + color);

            attribute = new Attribute("Color", "blue");
            mBeanServer.setAttribute(objectName, attribute);
            color = (String) mBeanServer.getAttribute(objectName, "Color");
            System.out.println("Color:" + color);
            mBeanServer.invoke(objectName, "drive", null, null);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
