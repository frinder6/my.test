package com.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * Created by frinder_liu on 2016/4/14.
 */
public class AServerTest {

    @Test
    public void server() {
        try (
                AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        ) {
            SocketAddress address = new InetSocketAddress("localhost", 21);
            serverSocketChannel.bind(address);
            Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
            if (!future.isDone()) {
                System.out.println("not read...waiting...");
            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            AsynchronousSocketChannel channel = future.get();
            channel.read(buffer).get();
            buffer.flip();
            System.out.println(buffer.toString());
            System.out.println("the server receive : " + Charset.defaultCharset().decode(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
