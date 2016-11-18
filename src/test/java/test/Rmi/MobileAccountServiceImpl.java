package test.Rmi;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
public class MobileAccountServiceImpl implements AccountService  {

    public int queryBalance(String mobileNo) {
        if (mobileNo != null)
            return 100;
        return 0;
    }

    public String shoopingPayment(String mobileNo, byte protocol) {
       return mobileNo;
    }
}
