package com.xiaoming.unit1;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by panxiaoming on 15/12/2.
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {

    }
}
