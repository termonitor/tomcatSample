package com.java.unit1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by panxiaoming on 15/12/30.
 */
public class NIOServer {
    public static int port = 12200;
    public static void main(String[] args) {
        //自己感兴趣的连接建立事件
//        MySelector selector = new MySelector();

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ServerSocket serverSocket = ssc.socket();
            serverSocket.bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
