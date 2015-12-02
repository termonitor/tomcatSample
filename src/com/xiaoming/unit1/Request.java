package com.xiaoming.unit1;

import java.io.IOException;
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
        StringBuffer stringBuffer = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch(IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for(int j=0;j<i;j++) {
            stringBuffer.append((char) buffer[j]);
        }
//        System.out.println(stringBuffer.toString());
        uri = parseUri(stringBuffer.toString());
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1) {
            index2 = requestString.indexOf(' ', index1+1);
            if(index2 > index1) {

                return requestString.substring(index1+1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
