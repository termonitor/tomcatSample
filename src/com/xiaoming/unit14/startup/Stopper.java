package com.xiaoming.unit14.startup;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by panxiaoming on 15/12/21.
 */
public class Stopper {
    public static void main(String[] args) {
        int port = 8005;
        try {
            Socket socket = new Socket("127.0.0.1", port);
            OutputStream stream = socket.getOutputStream();
            String shutdown = "SHUTDOWN";
            for(int i=0; i<shutdown.length(); i++) {
                stream.write(shutdown.charAt(i));
            }
            stream.flush();
            stream.close();
            socket.close();
            System.out.println("The server was successfully shut down.");
        } catch(IOException e) {
//            e.printStackTrace();
            System.out.println("Error. The server has not been started.");
        }
    }
}
