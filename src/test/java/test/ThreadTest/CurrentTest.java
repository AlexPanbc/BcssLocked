package test.ThreadTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/26
 * \* Time: 下午1:33
 * \* Description:
 * \
 */
public class CurrentTest {
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public void add(String key) {
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
        } else {
            map.put(key, value + 1);
        }
    }

    class AddThread implements Runnable {

        @Override
        public void run() {
            add("test");
        }
    }

    @Test
    public void test() throws Exception {
        Thread t1=new Thread(new AddThread());
        t1.start();
        Thread t2=new Thread(new AddThread());
        t2.start();
        Thread t3=new Thread(new AddThread());
        t3.start();
        Thread t4=new Thread(new AddThread());
        t4.start();
        Thread t5=new Thread(new AddThread());
        t5.start();
        Thread t6=new Thread(new AddThread());
        t6.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();






        System.out.println(map.get("test"));


    }
}
