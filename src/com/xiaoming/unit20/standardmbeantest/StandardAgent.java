package com.xiaoming.unit20.standardmbeantest;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

/**
 * Created by panxiaoming on 15/12/27.
 */
public class StandardAgent {
    private MBeanServer mBeanServer = null;

    public StandardAgent() {
        mBeanServer = MBeanServerFactory.createMBeanServer();
    }

    public MBeanServer getmBeanServer() {
        return mBeanServer;
    }

    public ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }

    private void createStandardBean(ObjectName objectName, String managedResourceClassName) {
        try {
            mBeanServer.createMBean(managedResourceClassName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StandardAgent agent = new StandardAgent();
        MBeanServer mBeanServer = agent.getmBeanServer();
        String domain = mBeanServer.getDefaultDomain();
        String managedResourceClassName = "com.xiaoming.unit20.standardmbeantest.Car";
        ObjectName objectName = agent.createObjectName(domain + ":type=" + managedResourceClassName);
        agent.createStandardBean(objectName, managedResourceClassName);
        try {
            Attribute colorAttribute = new Attribute("Color", "blue");
            mBeanServer.setAttribute(objectName, colorAttribute);
            System.out.println(mBeanServer.getAttribute(objectName, "Color"));
            mBeanServer.invoke(objectName,"drive",null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
