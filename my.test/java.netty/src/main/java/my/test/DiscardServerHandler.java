package my.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        System.out.print(in.toString(io.netty.util.CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
//        ctx.write(msg);
//        ctx.flush();
        // 默默地丢弃收到的数据
//        ((ByteBuf) msg).release(); // (3)
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            System.out.println(in.toString());
//            System.out.print(in.toString(io.netty.util.CharsetUtil.UTF_8));
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
    }

   /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // Do something with msg
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}


