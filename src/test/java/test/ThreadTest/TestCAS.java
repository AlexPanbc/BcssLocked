package test.ThreadTest;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/8
 * \* Time: 上午9:46
 * \* Description:测试CAS算法
 * \
 */
public class TestCAS {

    private AtomicInteger data=new AtomicInteger(0);

    private  int dataint=0;

    class AddThread implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<10000;i++){
                data.incrementAndGet();
            }
        }
    }

    class AddIntThread implements Runnable{

        @Override
        public void run() {

            for(int i=0;i<10000;i++){
                dataint++;
            }

        }
    }

    @Test
    public  void test() throws InterruptedException {
        Thread t1=new Thread(new AddIntThread());
        Thread t2=new Thread(new AddIntThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("data="+dataint);
    }



}
