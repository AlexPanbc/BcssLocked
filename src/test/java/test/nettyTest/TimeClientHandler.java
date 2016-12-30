package test.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * Created by LiuHuiChao on 2016/10/24.
 */
public class TimeClientHandler  extends ChannelHandlerAdapter {
    private ByteBuf buf;

    /**
     * 开始处理的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buf=ctx.alloc().buffer(4);//分配4个字节的空间给ByteBuf
    }

    /**
     * 处理结束的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();//释放ByteBuf的空间
        buf=null;
    }

   // @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m=(ByteBuf) msg;
        buf.writeBytes(m);
        m.release();
        if(buf.readableBytes()>=4){
            long currentTimeMillis=(buf.readInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
