package com.java.unit1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by panxiaoming on 15/12/30.
 */
public class NIOClient {
    public static void main(String[] args) {
        try {
            SocketChannel channel = SocketChannel.open();
            //设置为非阻塞模式
            channel.configureBlocking(false);
            //对于非阻塞模式，立刻返回false，表示连接正在建立中
            channel.connect(channel.getLocalAddress());
            Selector selector = Selector.open();
            //向channel注册selector以及感兴趣的连接事件
            channel.register(selector, SelectionKey.OP_CONNECT);
            int nKeys = selector.select(1000);
            SelectionKey sKey = null;
            if(nKeys > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                for(SelectionKey key : keys) {
                    if(key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.configureBlocking(false);
                        sKey = sc.register(selector, SelectionKey.OP_READ);
                        sc.finishConnect();
                    } else if(key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        SocketChannel sc = (SocketChannel) key.channel();
                        int readBytes = 0;
                        try {
                            int ret = 0;
                            try {
                                while((ret=sc.read(buffer)) > 0) {
                                    readBytes += ret;
                                }
                            } finally {
                                buffer.flip();
                            }
                        } finally {
                            if(buffer != null) {
                                buffer.clear();
                            }
                        }
                    } else if(key.isWritable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                        SocketChannel sc = (SocketChannel) key.channel();
                        int writtenedSize = sc.write(buffer);
                        if(writtenedSize == 0) {
                            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                        }
                    }
                }
                selector.selectedKeys().clear();
            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int wSize = channel.write(buffer);
            if(wSize == 0) {
                sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
