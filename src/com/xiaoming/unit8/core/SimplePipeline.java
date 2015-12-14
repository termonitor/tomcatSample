package com.xiaoming.unit8.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/14.
 */
public class SimplePipeline implements Pipeline, Lifecycle {
    protected Valve basic = null;
    protected Container container = null;
    protected Valve valves[] = new Valve[0];

    public SimplePipeline(Container container) {
        setContainer(container);
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public void addValve(Valve valve) {
        if(valve instanceof Contained) {
            ((Contained) valve).setContainer(this.container);
        }
        synchronized(valves) {
            Valve results[] = new Valve[valves.length + 1];
            System.arraycopy(valves, 0, results, 0, valves.length);
            results[valves.length] = valve;
            valves = results;
        }
    }

    @Override
    public Valve getBasic() {
        return basic;
    }

    @Override
    public Valve[] getValves() {
        return valves;
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        (new SimplePipelineValveContext()).invokeNext(request, response);
    }

    @Override
    public void removeValve(Valve valve) {

    }

    @Override
    public void setBasic(Valve valve) {
        this.basic = valve;
        ((Contained) valve).setContainer(this.container);
    }

    protected class SimplePipelineValveContext implements ValveContext {

        protected int stage = 0;

        @Override
        public String getInfo() {
            return null;
        }

        @Override
        public void invokeNext(Request request, Response response) throws IOException, ServletException {
            int subscript = stage;
            stage = stage + 1;
            if(subscript < valves.length) {
                valves[subscript].invoke(request, response, this);
            } else if((subscript == valves.length) && basic != null) {
                basic.invoke(request, response, this);
            } else {
                throw new ServletException("No valve");
            }
        }
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public synchronized void start() throws LifecycleException {

    }

    @Override
    public void stop() throws LifecycleException {

    }
}
