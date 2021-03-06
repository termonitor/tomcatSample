package com.xiaoming.unit8.core;

import org.apache.catalina.*;
import org.apache.catalina.util.LifecycleSupport;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/14.
 */
public class SimpleWrapper implements Wrapper, Pipeline, Lifecycle {
    private Servlet instance = null;
    private String servletClass;
    private Loader loader;
    private String name;
    private SimplePipeline pipeline = new SimplePipeline(this);
    protected Container parent = null;
    protected LifecycleSupport lifecycle = new LifecycleSupport(this);
    protected boolean started = false;

    public SimpleWrapper() {
        pipeline.setBasic(new SimpleWrapperValve());
    }

    @Override
    public synchronized void addValve(Valve valve) {
        pipeline.addValve(valve);
    }

    @Override
    public Valve getBasic() {
        return pipeline.getBasic();
    }

    @Override
    public Valve[] getValves() {
        return pipeline.getValves();
    }

    @Override
    public void removeValve(Valve valve) {
        pipeline.removeValve(valve);
    }

    @Override
    public void setBasic(Valve valve) {
        pipeline.setBasic(valve);
    }

    @Override
    public void addInitParameter(String name, String value) {

    }

    @Override
    public void addInstanceListener(InstanceListener listener) {

    }

    @Override
    public void addSecurityReference(String name, String link) {

    }

    @Override
    public Servlet allocate() throws ServletException {
        if(instance == null) {
            try {
                instance = loadServlet();
            } catch(ServletException e) {
                throw e;
            } catch(Throwable e) {
                throw new ServletException("Cannot allocate a servlet instance", e);
            }
        }
        return instance;
    }

    private Servlet loadServlet() throws ServletException {
        if(instance != null)
            return instance;
        Servlet servlet = null;
        String actualClass = servletClass;
        if(actualClass == null)
            throw new ServletException("servlet class has not been specified");

        Loader loader = getLoader();
        if(loader == null)
            throw new ServletException("No loader.");
        ClassLoader classLoader = loader.getClassLoader();
        Class clazz = null;
        try {
            if(classLoader != null) {
                clazz = classLoader.loadClass(actualClass);
            }
        } catch(ClassNotFoundException e) {
            throw new ServletException("Servlet class not found.");
        }
        try {
            servlet = (Servlet) clazz.newInstance();
        } catch(Throwable e) {
            throw new ServletException("Failed to instantiate servlet");
        }
        try {
            servlet.init(null);
        } catch (Throwable e) {
            throw new ServletException("Failed initialize servlet.");
        }
        return servlet;
    }

    @Override
    public void deallocate(Servlet servlet) throws ServletException {

    }

    @Override
    public String findInitParameter(String name) {
        return null;
    }

    @Override
    public String[] findInitParameters() {
        return new String[0];
    }

    @Override
    public String findSecurityReference(String name) {
        return null;
    }

    @Override
    public String[] findSecurityReferences() {
        return new String[0];
    }

    @Override
    public long getAvailable() {
        return 0;
    }

    @Override
    public String getJspFile() {
        return null;
    }

    @Override
    public int getLoadOnStartup() {
        return 0;
    }

    @Override
    public String getRunAs() {
        return null;
    }

    @Override
    public String getServletClass() {
        return servletClass;
    }

    @Override
    public boolean isUnavailable() {
        return false;
    }

    @Override
    public void load() throws ServletException {
        instance = loadServlet();
    }

    @Override
    public void removeInitParameter(String name) {

    }

    @Override
    public void removeInstanceListener(InstanceListener listener) {

    }

    @Override
    public void removeSecurityReference(String name) {

    }

    @Override
    public void setAvailable(long available) {

    }

    @Override
    public void setJspFile(String jspFile) {

    }

    @Override
    public void setLoadOnStartup(int value) {

    }

    @Override
    public void setRunAs(String runAs) {

    }

    @Override
    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    @Override
    public void unavailable(UnavailableException unavailable) {

    }

    @Override
    public void unload() throws ServletException {

    }

    @Override
    public void addChild(Container child) {

    }

    @Override
    public void addContainerListener(ContainerListener listener) {

    }

    @Override
    public void addMapper(Mapper mapper) {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public Container findChild(String name) {
        return null;
    }

    @Override
    public Container[] findChildren() {
        return new Container[0];
    }

    @Override
    public Mapper findMapper(String protocol) {
        return null;
    }

    @Override
    public Mapper[] findMappers() {
        return new Mapper[0];
    }

    @Override
    public Cluster getCluster() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public Loader getLoader() {
        if(loader != null)
            return loader;
        if(parent != null)
            return parent.getLoader();
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Manager getManager() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public ClassLoader getParentClassLoader() {
        return null;
    }

    @Override
    public Realm getRealm() {
        return null;
    }

    @Override
    public DirContext getResources() {
        return null;
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        pipeline.invoke(request, response);
    }

    @Override
    public Container map(Request request, boolean update) {
        return null;
    }

    @Override
    public void removeChild(Container child) {

    }

    @Override
    public void removeContainerListener(ContainerListener listener) {

    }

    @Override
    public void removeMapper(Mapper mapper) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void setCluster(Cluster cluster) {

    }

    @Override
    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void setLogger(Logger logger) {

    }

    @Override
    public void setManager(Manager manager) {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setParent(Container container) {
        this.parent = container;
    }

    @Override
    public void setParentClassLoader(ClassLoader parent) {

    }

    @Override
    public void setRealm(Realm realm) {

    }

    @Override
    public void setResources(DirContext resources) {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public synchronized void start() throws LifecycleException {
        System.out.println("Starting Wrapper " + name);
        if(started)
            throw new LifecycleException("Wrapper already started");
        System.out.println("before_start_event");
        started = true;
        if((loader != null) && (loader instanceof Lifecycle)) {
            ((Lifecycle) loader).start();
        }
        if((pipeline != null) && (pipeline instanceof Pipeline)) {
            ((Lifecycle) pipeline).start();
        }
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        System.out.println("after_start_event");
    }

    @Override
    public void stop() throws LifecycleException {
        System.out.println("Starting Wrapper " + name);
        try {
            instance.destroy();
        } catch(Throwable e) {
            ;
        }
        instance = null;
        if(!started) {
            throw new LifecycleException("Wrapper " + name + " not started");
        }
        System.out.println("before_stop_event");
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        started = false;
        if((pipeline != null) && (pipeline instanceof Pipeline)) {
            ((Lifecycle) pipeline).stop();
        }
        if((loader != null) && (loader instanceof Lifecycle)) {
            ((Lifecycle) loader).stop();
        }
        System.out.println("after_stop_event");
    }

    @Override
    public ContainerListener[] findContainerListeners() {
        return null;
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return null;
    }
}
