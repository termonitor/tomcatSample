package com.xiaoming.unit5.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class SimpleContextValve implements Valve, Contained {
    protected Container container;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        if(!(request.getRequest() instanceof HttpServletRequest) || !(response.getResponse() instanceof HttpServletResponse)) {
            return;
        }
        HttpServletRequest hsr = (HttpServletRequest) request.getRequest();
        String contextPath = hsr.getContextPath();
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();
        Context context = (Context)getContainer();
        Wrapper wrapper = null;
        try {
            wrapper = (Wrapper) context.map(request, true);
        } catch(IllegalArgumentException e) {
            badRequest(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }
        if(wrapper == null) {
            notFound(requestURI, (HttpServletResponse) response.getResponse());
            return;
        }
        response.setContext(context);
        wrapper.invoke(request, response);
    }

    private void badRequest(String requestURI, HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, requestURI);
        } catch(IllegalStateException e) {
            ;
        } catch(IOException e) {
            ;
        }
    }

    private void notFound(String requestURI, HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, requestURI);
        } catch(IllegalStateException e) {
            ;
        } catch(IOException e) {
            ;
        }
    }
}
