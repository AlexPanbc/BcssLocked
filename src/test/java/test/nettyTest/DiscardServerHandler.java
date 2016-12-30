package test.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 *
 * 服务端处理通道，这里只是打印一下请求的内容，并不对请求进行任何的响应
 * DiscardServerHandler继承自ChannelHandlerAdapter，
 * 这个类实现了ChannelHandler接口，
 * ChannelHandler提供了许多时间处理的接口方法，
 * 可以覆盖这些方法
 *
 * Created by LiuHuiChao on 2016/10/24.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    /**
     * 这里覆盖了channelRead事件处理方法
     * 每当从客户端收到新的数据时候，这个方法会在接收到消息时候被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
   // @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf in=(ByteBuf) msg;
            while (in.isReadable()){
                System.out.println((char)in.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 这个方法会在异常发生时候触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
