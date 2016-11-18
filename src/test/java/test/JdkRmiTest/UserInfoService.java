package test.JdkRmiTest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
public interface UserInfoService extends Remote{
    /**
     * 定义远程接口，必须继承Remote接口，
     * 其中所有需要远程调用的方法都必须抛出RemoteException异常
     */
    String getUserName(int id) throws RemoteException;
}
