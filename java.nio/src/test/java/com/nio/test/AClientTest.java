package com.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by frinder_liu on 2016/4/14.
 */
public class AClientTest {

    @Test
    public void client() {
        try (
                AsynchronousSocketChannel client = AsynchronousSocketChannel.open()
        ) {
            SocketAddress address = new InetSocketAddress("localhost", 21);
            client.connect(address);
            String str = "hello server...";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            System.out.println("the client say : ".concat(str));
            client.write(buffer).get();
            buffer.flip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
