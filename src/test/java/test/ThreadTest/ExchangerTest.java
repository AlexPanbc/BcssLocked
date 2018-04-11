package test.ThreadTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/28
 * \* Time: 上午10:38
 * \* Description:
 * \
 */
public class ExchangerTest {

    final Exchanger<List<Integer>> exchanger = new Exchanger();



    @Test
    public void test() throws BrokenBarrierException, InterruptedException {
        new Thread() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                try {
                    List<Integer> l = exchanger.exchange(list);
                    System.out.println("thread1=" + l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
                list.add(3);
                list.add(4);
                try {
                    List<Integer> l = exchanger.exchange(list);
                    System.out.println("thread2=" + l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }


}
