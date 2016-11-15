package test.MessagePackage;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by LiuHuiChao on 2016/11/15.
 * description:based on TCP/IP+NIO to deliver the message
 */
public class TCP_IP_NIO {

    @Test
    public void clientStart() throws IOException {
        SocketChannel channel=SocketChannel.open();
        channel.configureBlocking(false);//设置为非阻塞方式
        SocketAddress remote=new InetSocketAddress("127.0.0.1",8888);
        channel.connect(remote);
        Selector selector= Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        /**阻塞至有感兴趣的IO事件发生，或到达超时时间，如果希望一直等至有感兴趣的IO事件发生，可调用无参数select方法，
         * 如果希望不阻塞直接返回目前是否有感兴趣的事件发生，可以调用selectNow方法
         * */
        int nkeys=selector.select();//如果nkeys大于0，说明有感兴趣的IO事件发生
        SelectionKey selectionKey=null;
        if(nkeys>0){
            Set<SelectionKey> keys=selector.selectedKeys();
            for(SelectionKey key : keys){
                //对于发生连接的事件
                if(key.isConnectable()){
                    SocketChannel sc= (SocketChannel) key.channel();
                    sc.configureBlocking(false);
                    /**注册感兴趣的IO读事件，通常不直接注册写事件，在发送缓冲区未满的情况下，一直是可写的，
                     * 因此，如注册了写事件，而又不用写数据，很容易造成CUP消耗100%的情况；
                     * */
                    selectionKey=sc.register(selector,SelectionKey.OP_READ);
                    sc.finishConnect();
                }else if(key.isReadable()){/**有流可读*/
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    SocketChannel sc= (SocketChannel) key.channel();
                    int readBytes=0;
                    try{
                        int ret=0;
                        try{


                        }finally{

                        }
                    }finally{

                    }

                }
            }
        }




    }

    @Test
    public void serverStart(){

    }
}
