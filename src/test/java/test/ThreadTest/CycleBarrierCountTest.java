package test.ThreadTest;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/28
 * \* Time: 上午9:55
 * \* Description:
 * \
 */
public class CycleBarrierCountTest {

    final CyclicBarrier barrier=new CyclicBarrier(4);
    private volatile AtomicInteger total=new AtomicInteger(0);



    @Test
    public void test() throws Exception{
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(2)).start();
        barrier.await();
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
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
