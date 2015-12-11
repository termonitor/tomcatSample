package com.xiaoming.unit5.core;

import org.apache.catalina.Container;
import org.apache.catalina.Loader;

import java.beans.PropertyChangeListener;

/**
 * Created by panxiaoming on 15/12/11.
 */
public class SimpleLoader implements Loader {

    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void addRepository(String repository) {

    }

    @Override
    public String[] findRepositories() {
        return new String[0];
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public boolean getDelegate() {
        return false;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public boolean getReloadable() {
        return false;
    }

    @Override
    public boolean modified() {
        return false;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void setContainer(Container container) {

    }

    @Override
    public void setDelegate(boolean delegate) {

    }

    @Override
    public void setReloadable(boolean reloadable) {

    }
}
