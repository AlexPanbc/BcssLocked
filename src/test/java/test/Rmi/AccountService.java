package test.Rmi;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
public interface AccountService {
    int queryBalance(String mobileNo);
    String shoopingPayment(String mobileNo, byte protocol);
}
