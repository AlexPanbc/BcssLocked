package test.nettyTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by LiuHuiChao on 2016/10/24.
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {
        String host="127.0.0.1";
        int port=8088;
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            /**
             * 如果只指定了一个EventLoopGroup，
             * 那它就会作为boss线程，也会作为worker线程
             * 尽管客户端不需要boss线程
             */
            Bootstrap b=new Bootstrap();
            b.group(workerGroup);
            /**
             * 代替NioServerSocketChannel的是NioSocketChannel，这个类在客户端channel被创建时候调用
             */
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            ChannelFuture f=b.connect(host,port).sync();//用connect方法替代bind方法
            f.channel().closeFuture().sync();//等到运行结束，关闭
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
