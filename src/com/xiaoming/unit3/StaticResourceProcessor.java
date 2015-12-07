package com.xiaoming.unit3;

import com.xiaoming.unit3.http.HttpRequest;
import com.xiaoming.unit3.http.HttpResponse;

import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/7.
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
