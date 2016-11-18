package test.JdkRmiTest;

import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
public class JdkRmiServiceClientTest {

    /**
     * 使用Java 原生JDK实现RMI调用：服务端
     * @throws RemoteException
     * @throws AlreadyBoundException
     * @throws MalformedURLException
     */
    @Test
    public void startServer() throws RemoteException, AlreadyBoundException, MalformedURLException, InterruptedException {

        Object lock = new Object();
        synchronized (lock) {

            UserInfoService userInfoService=new UserInfoServiceImpl();//创建一个远程对象
            //生成远程对象注册表Registry的实例，并指定端口为8888（默认端口是1099）
            LocateRegistry.createRegistry(8888);
            //把远程对象注册到RMI服务器上，名称为UserInfoService
            ////绑定的URL标准格式为：rmi://host:port/name(协议名可以省略）
            Naming.bind("rmi://127.0.0.1:8888/UserInfoService",userInfoService);

            lock.wait();
        }
    }

    @Test
    public  void startClient() throws RemoteException, NotBoundException, MalformedURLException {
        // 在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法
        UserInfoService userInfoService=(UserInfoService)Naming.lookup("rmi://127.0.0.1:8888/UserInfoService");
        System.out.println(userInfoService.getUserName(1));
    }
}
