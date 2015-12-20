package com.xiaoming.unit10.realm;

import org.apache.catalina.Container;
import org.apache.catalina.Realm;

import java.beans.PropertyChangeListener;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by panxiaoming on 15/12/16.
 */
public class SimpleRealm implements Realm {

    private Container container;
    private ArrayList users = new ArrayList();

    public SimpleRealm() {
        createUserDatabase();
    }

    public void createUserDatabase() {
        User user1 = new User("xiaoming", "hello");
        user1.addRole("programmer");
        User user2 = new User("daming", "world");
        user2.addRole("programmer");
        user2.addRole("manager");
        users.add(user1);
        users.add(user2);
    }

    private User getUser(String username, String password) {
        Iterator iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = (User) iterator.next();
            if(user.username.equals(username) && user.password.equals(password))
                return user;
        }
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public Principal authenticate(String username, String credentials) {
        System.out.println("SimpleRealm.authenticate()");
        if(username == null || credentials == null)
            return null;
        User user = getUser(username, credentials);
        if(user == null)
            return null;

        return new GenericPrincipal(this, user.username, user.password, user.getRoles());
    }

    @Override
    public Principal authenticate(String username, String digest, String nonce, String nc, String cnonce, String qop, String realm, String md5a2) {
        return null;
    }

    @Override
    public Principal authenticate(String username, byte[] credentials) {
        return null;
    }

    @Override
    public Principal authenticate(X509Certificate[] certs) {
        return null;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public String getInfo() {
        return "A simple Realm implementation";
    }

    @Override
    public boolean hasRole(Principal principal, String role) {
        if((principal == null) || (role == null) || !(principal instanceof GenericPrincipal))
            return false;
        GenericPrincipal gp = (GenericPrincipal) principal;
        if(!(gp.getRealm() == this))
            return false;
        boolean result = gp.hasRole(role);
        return result;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    class User {
        public String username;
        public ArrayList roles = new ArrayList();
        public String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public void addRole(String role) {
            roles.add(role);
        }

        public ArrayList getRoles() {
            return roles;
        }
    }
}
