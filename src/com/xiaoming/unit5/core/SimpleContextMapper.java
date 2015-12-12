package com.xiaoming.unit5.core;

import org.apache.catalina.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by panxiaoming on 15/12/12.
 */
public class SimpleContextMapper implements Mapper {

    private SimpleContext context = null;

    @Override
    public Container getContainer() {
        return context;
    }

    @Override
    public String getProtocol() {
        return null;
    }

    @Override
    public Container map(Request request, boolean update) {
        String contextPath = ((HttpServletRequest) request.getRequest()).getContextPath();
//        String requestURI = ((HttpRequest) request).getDecodedRequestURI();
        String requestURI = ((HttpServletRequest) request.getRequest()).getRequestURI();
        String relativeURI = requestURI.substring(contextPath.length());

        Wrapper wrapper = null;
        String servletPath = relativeURI;
        String pathInfo = null;
        String name = context.findServletMapping(relativeURI);
        if(name != null)
            wrapper = (Wrapper) context.findChild(name);
        return wrapper;
    }

    @Override
    public void setContainer(Container container) {
        if(!(container instanceof SimpleContext)) {
            throw new IllegalArgumentException("Illegal type of container");
        }
        context = (SimpleContext) container;
    }

    @Override
    public void setProtocol(String protocol) {

    }
}
