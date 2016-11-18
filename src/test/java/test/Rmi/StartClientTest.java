package test.Rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext-test-client-rmi.xml"})
public class StartClientTest {

    @Resource
    AccountService accountService;

    @Test
    public void testRmiAtClient(){
        String result = accountService.shoopingPayment("123333456357654", (byte) 5);
        System.out.println(result);
    }

}
