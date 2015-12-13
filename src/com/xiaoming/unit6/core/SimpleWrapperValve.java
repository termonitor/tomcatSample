package com.xiaoming.unit6.core;

import org.apache.catalina.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/13.
 */
public class SimpleWrapperValve implements Valve, Contained {
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
    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
        SimpleWrapper wrapper = (SimpleWrapper) getContainer();
        ServletRequest serq = request.getRequest();
        ServletResponse serp = response.getResponse();
        Servlet servlet = null;
        HttpServletRequest hsq = null;
        if(serq instanceof HttpServletRequest) {
            hsq = (HttpServletRequest) serq;
        }
        HttpServletResponse hsp = null;
        if(serp instanceof HttpServletResponse) {
            hsp = (HttpServletResponse) serp;
        }

        try {
            servlet = wrapper.allocate();
            if(hsq != null && hsp != null) {
                servlet.service(hsq, hsp);
            } else {
                servlet.service(serq, serp);
            }
        } catch(ServletException e) {

        }
    }
}
