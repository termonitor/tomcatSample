package com.xiaoming.unit5.valve;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class HeaderLoggerValve implements Valve, Contained {

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
        context.invokeNext(request, response);

        System.out.println("Header Logger Valve");
        ServletRequest serq = request.getRequest();
        if(serq instanceof HttpServletRequest) {
            HttpServletRequest hsr = (HttpServletRequest) serq;
            Enumeration headerNames = hsr.getHeaderNames();
            while(headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement().toString();
                String headerValue = hsr.getHeader(headerName);
                System.out.println(headerName+":"+headerValue);
            }
        } else {
            System.out.println("Not an Http Request");
        }
    }
}
