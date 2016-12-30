package test.nettyTest;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 服务端处理通道
 * ResponseServerHandler继承ChannelHandlerAdapter
 * 这个类实现了ChannelHandler接口，
 *
 * Created by LiuHuiChao on 2016/10/24.
 */
public class ResponseServerHandler extends ChannelHandlerAdapter {
    /**
     * 这里覆盖了channelRead事件处理方法
     * 每当客户端接收到新的数据时候，
     * 这个方法会在收到消息时候被调用
     * ChannelHandlerContext对象提供了许多操作
     * @param ctx
     * @param msg
     * @throws Exception
     */
   // @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    /**
     * ctx.write方法不会使消息写入到通道上
     * 它被缓冲在了内部，需要调用fluse方法把缓冲区中数据强行输出
     * @param ctx
     * @throws Exception
     */
    //@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
