package test.nettyTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 测试Netty类库：服务端代码
 * Created by LiuHuiChao on 2016/10/24.
 */
public class NettyServerTest {
    private int port;
    public NettyServerTest(int port){
        this.port=port;
    }

    /**
     * EventLoopGroup 是用来处理I/O操作的多线程事件循环器，
     * Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议。
     *
     *
     * @throws Exception
     */
    public void run() throws Exception{
        //一旦 boss 接收到连接，就会把连接信息注册到worker上面
        EventLoopGroup bossGroup=new NioEventLoopGroup();//用来接收进来的连接
        EventLoopGroup workerGroup=new NioEventLoopGroup();//用来处理已经被接收的连接

        System.out.println("准备运行端口："+port);
        try{
            //ServerBootstrap是一个启动NIO服务的辅助启动类，可以在这个服务中直接使用CChannel
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap=serverBootstrap.group(bossGroup,workerGroup);//这一步是必须的
            serverBootstrap=serverBootstrap.channel(NioServerSocketChannel.class);
            /*
            * 这里的事件处理类经常会被用来处理一个最近的已经接收的Channel。
                              * ChannelInitializer是一个特殊的处理类，
                                * 他的目的是帮助使用者配置一个新的Channel。
                               * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
                               * 或者其对应的ChannelPipeline来实现你的网络程序。
                               * 当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
                                * 然后提取这些匿名类到最顶层的类上。
              */
            serverBootstrap=serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //socketChannel.pipeline().addLast(new DiscardServerHandler());
                    socketChannel.pipeline().addLast(new TimeServerHandler());
                }
            });
            /**
             * 可以设置这里指定的通道实现配置参数
             * 我们正在写一个TCP/IP的服务端
             * 因此我们被允许设置socket的参数选项比如tcpNoDelay,keepAlive
             */
            serverBootstrap=serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
            serverBootstrap=serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
            /**
             * 绑定端口并启动区接收进来的连接
             */
            ChannelFuture f=serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();//这里会一直等待，知道socket被关闭
           }catch (Exception e){

        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }

    public  static void main(String[] args) throws Exception {
        int port;
        if(args.length>0){
            port=Integer.parseInt(args[0]);
        }else{
            port=8088;
        }
        new NettyServerTest(port).run();
    }

}
