package com.xiaoming.unit1;

import com.xiaoming.unit2.ServletProcessor;
import com.xiaoming.unit2.StaticResourceProcessor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by panxiaoming on 15/12/2.
 */
public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //create Request object and parse
                Request request = new Request(input);
                request.parse();

                //create Response object
                Response response = new Response(output);
                response.setRequest(request);
//                response.sendStaticResource();

                if(request.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                //Close the socket
                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
