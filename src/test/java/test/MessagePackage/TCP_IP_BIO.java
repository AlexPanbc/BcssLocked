package test.MessagePackage;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LiuHuiChao on 2016/11/15.
 * description:based on TCP/IP+BIO to deliver the message
 */
public class TCP_IP_BIO {

    /**
     * client端
     * @throws IOException
     */
    @Test
    public void startClient() throws IOException {
        Socket socket =new Socket("127.0.0.1",8888);
        /**创建读取服务器端返回流的BufferedReader*/
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        /**创建向服务器写入流的PrintWriter*/
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        /**向服务器发送字符串信息，此处即使写失败，也不会抛出异常信息，并且一直阻塞到写入操作系统或网络IO出现异常*/
        out.println("chifanla");
        /**阻塞读取服务端的返回信息，一下代码会阻塞到服务端返回信息或者网络IO出现异常，如果希望在超时一段时间就不阻塞了，那么久需要在创建socket之后，调用setTimeOut*/
        //in.readLine();
    }

    /**
     * 服务端
     * @throws IOException
     */
    @Test
    public void startServer() throws IOException {
        /**创建对本地指定端口的监听，如果端口冲突，则抛出SocketException;其他网络IO异常抛出IOException*/
        ServerSocket serverSocket=new ServerSocket(8888);
        /**接受客户端简历的连接请求，并且返回一个Socket对象，以便与客户端进行交互；*/
        Socket socket=serverSocket.accept();
        byte[] read=new byte[128];
        socket.getInputStream().read(read,0,128);
        System.out.println(read.toString());

    }
}
