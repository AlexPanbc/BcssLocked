package test.ThreadTest;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/28
 * \* Time: 上午10:04
 * \* Description:
 * \
 */
public class SemaphoreTest {

    final Semaphore semaphore=new Semaphore(3);
    final CyclicBarrier barrier=new CyclicBarrier(4);

    private volatile Integer total=0;

    @Test
    public void test() throws Exception{
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(3)).start();
        new Thread(new CountThread(3)).start();
        barrier.await();
        System.out.println("total="+total);

    }

    class CountThread implements Runnable{
        int count;
        CountThread(int count){this.count=count;}

        @Override
        public void run() {

            try {
                semaphore.acquire();
                for (int i=0;i<count;i++)
                {
                    total++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
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
