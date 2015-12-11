package com.xiaoming.unit5.valve;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class ClientIPLoggerValve implements Valve, Contained {

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

        System.out.println("Client IP Logger Valve");
        ServletRequest serq = request.getRequest();
        System.out.println(serq.getRemoteAddr());
        System.out.println("------------------------");
    }
}
