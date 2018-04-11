package test.ThreadTest;

import com.yammer.metrics.util.DeadlockHealthCheck;

import java.util.concurrent.locks.ReentrantLock;

/**ReentrantLock可中断性测试
 * Created by liuhuichao on 2017/6/20.
 */
public class ReentrantLockInterruptedTest implements Runnable {

    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public ReentrantLockInterruptedTest(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            if(lock==1){
                lock1.lockInterruptibly();
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println("线程退出："+Thread.currentThread().getId());
        }

    }

    public static void main(String[] args)  throws Exception{
        ReentrantLockInterruptedTest l1=new ReentrantLockInterruptedTest(1);
        ReentrantLockInterruptedTest l2=new ReentrantLockInterruptedTest(2);
        Thread t1=new Thread(l1);
        Thread t2=new Thread(l2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
       DeadLockCheckChecker.check();

    }
}
