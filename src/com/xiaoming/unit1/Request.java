package com.xiaoming.unit1;

import java.io.InputStream;

/**
 * Created by panxiaoming on 15/12/2.
 */
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {

    }

    private String parseUri(String requestString) {
        return null;
    }

    public String getUri() {
        return uri;
    }
}
