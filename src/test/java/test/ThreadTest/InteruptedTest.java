package test.ThreadTest;

import org.junit.Test;

public class InteruptedTest {


    @Test
    public void interuptTest() {
        Thread t = new Thread(new IntruptTest());
        t.start();
        t.interrupt();
        System.out.println("test=" + t.isInterrupted());

    }

    class IntruptTest implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                   System.out.println("exception="+Thread.currentThread().isInterrupted());
                }
                System.out.println(i++);
            }
        }
    }
}
