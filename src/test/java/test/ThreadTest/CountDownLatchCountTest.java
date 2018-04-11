package test.ThreadTest;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/27
 * \* Time: 下午1:22
 * \* Description:
 * \
 */
public class CountDownLatchCountTest {

    private volatile AtomicInteger total=new AtomicInteger(0);

    private CountDownLatch countDownLatch=new CountDownLatch(3);

    @Test
    public void test() throws Exception{
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(2)).start();
        countDownLatch.await();
        System.out.println("total="+total.intValue());
    }

    class CountThread implements Runnable{
        int count;
        CountThread(int count){this.count=count;}

        @Override
        public void run() {
            for (int i=0;i<count;i++)
            {
                total.incrementAndGet();
            }
            countDownLatch.countDown();
        }
    }
}
