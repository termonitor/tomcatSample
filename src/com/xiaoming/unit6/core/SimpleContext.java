package com.xiaoming.unit6.core;

import org.apache.catalina.*;
import org.apache.catalina.deploy.*;
import org.apache.catalina.util.CharsetMapper;
import org.apache.catalina.util.LifecycleSupport;

import javax.naming.directory.DirContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by panxiaoming on 15/12/13.
 */
public class SimpleContext implements Context, Pipeline, Lifecycle {
    protected HashMap children = new HashMap();
    protected Loader loader = null;
    protected SimplePipeline pipeline = new SimplePipeline(this);
    protected HashMap servletMappings = new HashMap();
    protected Mapper mapper = null;
    protected HashMap mappers = new HashMap();
    private Container parent = null;
    protected LifecycleSupport lifecycle = new LifecycleSupport(this);
    protected boolean started = false;

    public SimpleContext() {
        pipeline.setBasic(new SimpleContextValve());
    }

    @Override
    public void addApplicationListener(String listener) {

    }

    @Override
    public void addApplicationParameter(ApplicationParameter parameter) {

    }

    @Override
    public void addConstraint(SecurityConstraint constraint) {

    }

    @Override
    public void addEjb(ContextEjb ejb) {

    }

    @Override
    public void addEnvironment(ContextEnvironment environment) {

    }

    @Override
    public void addErrorPage(ErrorPage errorPage) {

    }

    @Override
    public void addFilterDef(FilterDef filterDef) {

    }

    @Override
    public void addFilterMap(FilterMap filterMap) {

    }

    @Override
    public void addInstanceListener(String listener) {

    }

    @Override
    public void addLocalEjb(ContextLocalEjb ejb) {

    }

    @Override
    public void addMimeMapping(String extension, String mimeType) {

    }

    @Override
    public void addParameter(String name, String value) {

    }

    @Override
    public void addResource(ContextResource resource) {

    }

    @Override
    public void addResourceEnvRef(String name, String type) {

    }

    @Override
    public void addRoleMapping(String role, String link) {

    }

    @Override
    public void addSecurityRole(String role) {

    }

    @Override
    public void addServletMapping(String pattern, String name) {
        synchronized (servletMappings) {
            servletMappings.put(pattern, name);
        }
    }

    @Override
    public void addTaglib(String uri, String location) {

    }

    @Override
    public void addWelcomeFile(String name) {

    }

    @Override
    public void addWrapperLifecycle(String listener) {

    }

    @Override
    public void addWrapperListener(String listener) {

    }

    @Override
    public Wrapper createWrapper() {
        return null;
    }

    @Override
    public String[] findApplicationListeners() {
        return new String[0];
    }

    @Override
    public ApplicationParameter[] findApplicationParameters() {
        return new ApplicationParameter[0];
    }

    @Override
    public SecurityConstraint[] findConstraints() {
        return new SecurityConstraint[0];
    }

    @Override
    public ContextEjb findEjb(String name) {
        return null;
    }

    @Override
    public ContextEjb[] findEjbs() {
        return new ContextEjb[0];
    }

    @Override
    public ContextEnvironment findEnvironment(String name) {
        return null;
    }

    @Override
    public ContextEnvironment[] findEnvironments() {
        return new ContextEnvironment[0];
    }

    @Override
    public ErrorPage findErrorPage(int errorCode) {
        return null;
    }

    @Override
    public ErrorPage findErrorPage(String exceptionType) {
        return null;
    }

    @Override
    public ErrorPage[] findErrorPages() {
        return new ErrorPage[0];
    }

    @Override
    public FilterDef findFilterDef(String filterName) {
        return null;
    }

    @Override
    public FilterDef[] findFilterDefs() {
        return new FilterDef[0];
    }

    @Override
    public FilterMap[] findFilterMaps() {
        return new FilterMap[0];
    }

    @Override
    public String[] findInstanceListeners() {
        return new String[0];
    }

    @Override
    public ContextLocalEjb findLocalEjb(String name) {
        return null;
    }

    @Override
    public ContextLocalEjb[] findLocalEjbs() {
        return new ContextLocalEjb[0];
    }

    @Override
    public String findMimeMapping(String extension) {
        return null;
    }

    @Override
    public String[] findMimeMappings() {
        return new String[0];
    }

    @Override
    public String findParameter(String name) {
        return null;
    }

    @Override
    public String[] findParameters() {
        return new String[0];
    }

    @Override
    public ContextResource findResource(String name) {
        return null;
    }

    @Override
    public String findResourceEnvRef(String name) {
        return null;
    }

    @Override
    public String[] findResourceEnvRefs() {
        return new String[0];
    }

    @Override
    public ContextResource[] findResources() {
        return new ContextResource[0];
    }

    @Override
    public String findRoleMapping(String role) {
        return null;
    }

    @Override
    public boolean findSecurityRole(String role) {
        return false;
    }

    @Override
    public String[] findSecurityRoles() {
        return new String[0];
    }

    @Override
    public String findServletMapping(String pattern) {
        synchronized (servletMappings) {
            return ((String) servletMappings.get(pattern));
        }
    }

    @Override
    public String[] findServletMappings() {
        return new String[0];
    }

    @Override
    public String findStatusPage(int status) {
        return null;
    }

    @Override
    public int[] findStatusPages() {
        return new int[0];
    }

    @Override
    public String findTaglib(String uri) {
        return null;
    }

    @Override
    public String[] findTaglibs() {
        return new String[0];
    }

    @Override
    public boolean findWelcomeFile(String name) {
        return false;
    }

    @Override
    public String[] findWelcomeFiles() {
        return new String[0];
    }

    @Override
    public String[] findWrapperLifecycles() {
        return new String[0];
    }

    @Override
    public String[] findWrapperListeners() {
        return new String[0];
    }

    @Override
    public Object[] getApplicationListeners() {
        return new Object[0];
    }

    @Override
    public boolean getAvailable() {
        return false;
    }

    @Override
    public CharsetMapper getCharsetMapper() {
        return null;
    }

    @Override
    public boolean getConfigured() {
        return false;
    }

    @Override
    public boolean getCookies() {
        return false;
    }

    @Override
    public boolean getCrossContext() {
        return false;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public boolean getDistributable() {
        return false;
    }

    @Override
    public String getDocBase() {
        return null;
    }

    @Override
    public LoginConfig getLoginConfig() {
        return null;
    }

    @Override
    public boolean getOverride() {
        return false;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getPublicId() {
        return null;
    }

    @Override
    public boolean getReloadable() {
        return false;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public int getSessionTimeout() {
        return 0;
    }

    @Override
    public String getWrapperClass() {
        return null;
    }

    @Override
    public void reload() {

    }

    @Override
    public void removeApplicationListener(String listener) {

    }

    @Override
    public void removeApplicationParameter(String name) {

    }

    @Override
    public void removeConstraint(SecurityConstraint constraint) {

    }

    @Override
    public void removeEjb(String name) {

    }

    @Override
    public void removeEnvironment(String name) {

    }

    @Override
    public void removeErrorPage(ErrorPage errorPage) {

    }

    @Override
    public void removeFilterDef(FilterDef filterDef) {

    }

    @Override
    public void removeFilterMap(FilterMap filterMap) {

    }

    @Override
    public void removeInstanceListener(String listener) {

    }

    @Override
    public void removeLocalEjb(String name) {

    }

    @Override
    public void removeMimeMapping(String extension) {

    }

    @Override
    public void removeParameter(String name) {

    }

    @Override
    public void removeResource(String name) {

    }

    @Override
    public void removeResourceEnvRef(String name) {

    }

    @Override
    public void removeRoleMapping(String role) {

    }

    @Override
    public void removeSecurityRole(String role) {

    }

    @Override
    public void removeServletMapping(String pattern) {

    }

    @Override
    public void removeTaglib(String uri) {

    }

    @Override
    public void removeWelcomeFile(String name) {

    }

    @Override
    public void removeWrapperLifecycle(String listener) {

    }

    @Override
    public void removeWrapperListener(String listener) {

    }

    @Override
    public void setApplicationListeners(Object[] listeners) {

    }

    @Override
    public void setAvailable(boolean available) {

    }

    @Override
    public void setCharsetMapper(CharsetMapper mapper) {

    }

    @Override
    public void setConfigured(boolean configured) {

    }

    @Override
    public void setCookies(boolean cookies) {

    }

    @Override
    public void setCrossContext(boolean crossContext) {

    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public void setDistributable(boolean distributable) {

    }

    @Override
    public void setDocBase(String docBase) {

    }

    @Override
    public void setLoginConfig(LoginConfig config) {

    }

    @Override
    public void setOverride(boolean override) {

    }

    @Override
    public void setPath(String path) {

    }

    @Override
    public void setPublicId(String publicId) {

    }

    @Override
    public void setReloadable(boolean reloadable) {

    }

    @Override
    public void setSessionTimeout(int timeout) {

    }

    @Override
    public void setWrapperClass(String wrapperClass) {

    }

    @Override
    public void addChild(Container child) {
        child.setParent((Container) this);
        children.put(child.getName(), child);
    }

    @Override
    public void addContainerListener(ContainerListener listener) {

    }

    @Override
    public void addMapper(Mapper mapper) {
        mapper.setContainer((Container) this);
        this.mapper = mapper;
        synchronized (mappers) {
            if(mappers.get(mapper.getProtocol()) != null) {
                throw new IllegalArgumentException("addMapper: Protocol '" + mapper.getProtocol() + "' is not unique");
            }
            mapper.setContainer((Container) this);
            mappers.put(mapper.getProtocol(), mapper);
            if(mappers.size() == 1)
                this.mapper = mapper;
            else
                this.mapper = null;
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public Container findChild(String name) {
        if(name == null)
            return null;
        synchronized(children) {
            return ((Container) children.get(name));
        }
    }

    @Override
    public Container[] findChildren() {
        synchronized(children) {
            Container results[] = new Container[children.size()];
            return ((Container[]) children.values().toArray(results));
        }
    }

    @Override
    public Mapper findMapper(String protocol) {
        if(mapper != null)
            return mapper;
        else
            synchronized(mappers) {
                return ((Mapper) mappers.get(protocol));
            }
    }

    @Override
    public Mapper[] findMappers() {
        return null;
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
        return null;
    }

    @Override
    public Container getParent() {
        return null;
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
        Mapper mapper = findMapper(request.getRequest().getProtocol());
        if(mapper == null)
            return null;
        return mapper.map(request, update);
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

    }

    @Override
    public void setParent(Container container) {

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
    public void addValve(Valve valve) {
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
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLifecycleListener(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public synchronized void start() throws LifecycleException {
        if(started)
            throw new LifecycleException("SimpleContext has already started");
        System.out.println("before_start_event");
        started = true;
        try {
            if((loader != null) && (loader instanceof Lifecycle)) {
                ((Lifecycle) loader).start();
            }
            Container children[] = findChildren();
            for(int i=0; i<children.length; i++) {
                if(children[i] instanceof Lifecycle) {
                    ((Lifecycle) children[i]).start();
                }
            }
            if((pipeline != null) && (pipeline instanceof Lifecycle)) {
                ((Lifecycle) pipeline).start();
            }
            lifecycle.fireLifecycleEvent(START_EVENT, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after_start_event");
    }

    @Override
    public void stop() throws LifecycleException {
        if(!started)
            throw new LifecycleException("SimpleContext has not been started");
        System.out.println("before_stop_event");
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        started = false;
        try {
            if((pipeline != null) && (pipeline instanceof Lifecycle)) {
                ((Lifecycle) pipeline).stop();
            }
            Container children[] = findChildren();
            for(int i=0; i<children.length; i++) {
                if(children[i] instanceof Lifecycle) {
                    ((Lifecycle) children[i]).stop();
                }
            }
            if((loader != null) && (loader instanceof Lifecycle)) {
                ((Lifecycle) loader).stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after_stop_event");
    }
}
