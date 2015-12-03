package com.xiaoming.unit2;

import com.xiaoming.unit1.Request;
import com.xiaoming.unit1.Response;

import java.io.IOException;

/**
 * Created by panxiaoming on 15/12/3.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
