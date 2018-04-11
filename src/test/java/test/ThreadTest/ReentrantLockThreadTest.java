package test.ThreadTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock测试
 * Created by liuhuichao on 2017/6/20.
 */
public class ReentrantLockThreadTest implements Runnable {

    public static ReentrantLock reentrantLock=new ReentrantLock();
    public static int i=0;

    @Override
    public void run() {
        for(int j=0;j<100000;j++){
            reentrantLock.lock();
            //reentrantLock.lock();//注意，加锁几次，就需要解锁几次，不然会出现线程一直等待
            try{
                i++;
            }finally {
                reentrantLock.unlock();
               // reentrantLock.unlock();

            }
        }
    }

    public static void main(String[] args)  throws Exception{
        ReentrantLockThreadTest runClass=new ReentrantLockThreadTest();
        Thread t1=new Thread(runClass);
        Thread t2=new Thread(runClass);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
