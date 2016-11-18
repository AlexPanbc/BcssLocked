package test.Rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by LiuHuiChao on 2016/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext-test-service-rmi.xml"})
public class StartServerTest {

    @Test
    public void startServerTest() throws InterruptedException {
        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }
}
