package test.ThreadTest;

import org.junit.Test;


/**
 * 有三个线程 t1,t2,t3，如何保证这三个线程顺序执行？
 */
public class ShunXuThreadTest {


    @Test
    public void threadTest() throws InterruptedException {

        Thread t1=new Thread(new soutThread(1));
        t1.setName("t1");
        t1.start();
        t1.join();

        Thread t2=new Thread(new soutThread(2));
        t2.setName("t2");
        t2.start();
        t2.join();

        Thread t3=new Thread(new soutThread(3));
        t3.setName("t3");
        t3.start();
        t3.join();

        System.out.println("执行完成！");


    }

    class soutThread implements Runnable{
        int index;

        soutThread(int index){
            this.index=index;
        }

        @Override
        public void run() {
            System.out.println("thread index="+index+" ;thread name="+Thread.currentThread().getName());
        }
    }
}
