package com.xiaoming.unit8.core;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Created by panxiaoming on 15/12/14.
 */
public class SimpleContextConfig implements LifecycleListener {
    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if(Lifecycle.START_EVENT.equals(event.getType())) {
            Context context = (Context)event.getLifecycle();
            context.setConfigured(true);
        }
    }
}
