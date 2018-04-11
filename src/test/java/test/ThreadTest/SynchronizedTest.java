package test.ThreadTest;

import org.junit.Test;

public class SynchronizedTest {

    static Object lock = new Object();
    static int i = 0;

    @Test
    public  void addTest() throws InterruptedException {
        Thread t1=new Thread(new SyncThread());
        Thread t2=new Thread(new SyncThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i="+i);

    }

    private synchronized void addMethod(){
        i++;
    }

    class SyncThread implements Runnable {


        @Override
        public void run() {
           // synchronized (lock){
                for (int j = 0; j < 10000; j++) {
                    addMethod();
                }
            //}


        }
    }
}
