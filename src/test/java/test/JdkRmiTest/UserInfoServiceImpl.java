package test.JdkRmiTest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
public class UserInfoServiceImpl extends UnicastRemoteObject implements UserInfoService{

    /**
     * 接口的实现类同时要实现Serializable接口，这里继承UnicastRemoteObject也是间接实现Serializable接口，
     * 同时，因为构造方法需要抛出RemoteException，所以不能缺省使用隐含的无参构造方法，而应该自己显式定义构造方法。
     * @throws RemoteException
     */
    protected UserInfoServiceImpl() throws RemoteException {
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        return "漠漠水田飞白鹭";
    }
}
