package test.ThreadTest;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/28
 * \* Time: 上午11:08
 * \* Description:
 * \
 */
public class FutureTest {
    final ExecutorService excutorService=Executors.newSingleThreadExecutor();


    @Test
    public void test() throws Exception{
        Future<Integer> future=getDataFromRemoteSync();
        System.out.println("do sth else.........");
        Integer result=future.get();
        System.out.println("result="+result);
    }

    public Integer getDataFromRemote(){
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1000;
    }

    public Future<Integer> getDataFromRemoteSync(){
       return excutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return getDataFromRemote();
            }
        });


    }
}
