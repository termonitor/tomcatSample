package com.xiaoming.unit10.realm;

import org.apache.catalina.realm.RealmBase;

import java.security.Principal;

/**
 * Created by panxiaoming on 15/12/16.
 */
public class SimpleUserDatabaseRealm extends RealmBase {

    @Override
    protected String getName() {
        return null;
    }

    @Override
    protected String getPassword(String username) {
        return null;
    }

    @Override
    protected Principal getPrincipal(String username) {
        return null;
    }
}
