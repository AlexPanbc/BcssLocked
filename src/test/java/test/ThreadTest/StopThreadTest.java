package test.ThreadTest;

import org.junit.Test;

public class StopThreadTest {


    @Test
    public void stopTest() throws InterruptedException {

        Thread t = new someThread();
        t.start();
        Thread.sleep(7);
        t.stop();

    }


    class someThread extends Thread {

        public void run() {
            for (int i = 0; i < 1000000; i++) {
                System.out.println("i=" + i);//一直全部打印完
            }
            System.out.println("打印完成！！！");
        }
    }
}
