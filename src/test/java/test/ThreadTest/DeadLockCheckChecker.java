package test.ThreadTest;

import com.alibaba.fastjson.JSONObject;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 死锁检测工具
 * Created by liuhuichao on 2017/6/21.
 */
public class DeadLockCheckChecker {

    private  final static ThreadMXBean mbean=ManagementFactory.getThreadMXBean();
    final static Runnable deadLockCheck=new Runnable() {
        @Override
        public void run() {
            while (true){
                long[] deadLockedThreadIds=mbean.findDeadlockedThreads();
                if(deadLockedThreadIds!=null){
                    ThreadInfo[] threadInfos=mbean.getThreadInfo(deadLockedThreadIds);
                    for(Thread t: Thread.getAllStackTraces().keySet()){
                        for(int i=0;i<threadInfos.length;i++){
                            if(t.getId()==threadInfos[i].getThreadId()){
                                t.interrupt();
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }

        }
    };


    public static void check(){
        Thread thread=new Thread(deadLockCheck);
        thread.setDaemon(true);
        thread.start();
    }















    public static void main(String[] args) {

        /** ThreadMXBean常用方法test */
        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        int count=threadMXBean.getThreadCount();//获取当前线程数目
        System.out.println("当前线程数为："+count);
        long[]  threadIds=threadMXBean.getAllThreadIds();
        System.out.println("当前线程id们为："+ JSONObject.toJSONString(threadIds));
        ThreadInfo[] threadInfos=threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds());
        System.out.println("当前线程的信息："+JSONObject.toJSONString(threadInfos));
        System.out.println("是否支持测量线程执行时间："+threadMXBean.isCurrentThreadCpuTimeSupported());


    }
}
