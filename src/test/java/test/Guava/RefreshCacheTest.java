package test.Guava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/2
 * \* Time: 下午11:33
 * \* Description:通过轮询安全更新缓存
 * \
 */
public class RefreshCacheTest {

    private ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);

    private LoadingCache<String,String> cahceBuilder= CacheBuilder.newBuilder()
            .maximumSize(10)
            .removalListener(new RemovalListener<String,String>() {
                @Override
                public void onRemoval(RemovalNotification removalNotification) {
                    System.out.println("移除："+removalNotification.getKey());
                }
            })
            .build(new CacheLoader<String, String>() {

                @Override
                public String load(String key) throws Exception {
                    System.out.println("load key:"+key);
                    String value=key+"_"+"end";
                    return value;
                }
            });


    /**
     * 执行定时刷新
     */
    private class RefreshJob implements Runnable{

        @Override
        public void run() {
            System.out.println("begin refresh------------");
            Set<String> keys = cahceBuilder.asMap().keySet();
            for(String key: keys){
                cahceBuilder.refresh(key);
            }
            System.out.println("end refresh------------");
        }
    }


    /**
     * 使用轮询方式更新缓存
     *
     *  主要解决问题：
     *
     *      CacheBuilder.refreshAfterWrite(long, TimeUnit)：可以为缓存增加自动定时刷新功能。
     *      和expireAfterWrite相反，refreshAfterWrite通过定时刷新可以让缓存项保持可用，但请注意：
     *      缓存项只有在被检索时才会真正刷新，即只有刷新间隔时间到了你再去get(key)
     *      才会重新去执行Loading否则就算刷新间隔时间到了也不会执行loading操作。
     *      因此，如果你在缓存上同时声明expireAfterWrite和refreshAfterWrite，
     *      缓存并不会因为刷新盲目地定时重置，如果缓存项没有被检索，那刷新就不会真的发生，
     *      缓存项在过期时间后也变得可以回收。
     *      还有一点比较重要的是refreshAfterWrite和expireAfterWrite两个方法设置以后，
     *      重新get会引起loading操作都是同步串行的。这其实可能会有一个隐患，
     *      当某一个时间点刚好有大量检索过来而且都有刷新或者回收的话，是会产生大量的请求同步调用loading方法，
     *      这些请求占用线程资源的时间明显变长。如正常请求也就20ms，
     *      当刷新以后加上同步请求loading这个功能接口可能响应时间远远大于20ms。
     *      为了预防这种井喷现象，可以不设置CacheBuilder.refreshAfterWrite(long, TimeUnit)，
     *      改用LoadingCache.refresh(K)因为它是异步执行的，不会影响正在读的请求，
     *      同时使用ScheduledExecutorService可以帮助你很好地实现这样的定时调度
     *      ，配上cache.asMap().keySet()返回当前所有已加载键，这样所有的key定时刷新就有了。
     */
    @Test
    public void refreshCacheByJob() throws ExecutionException, InterruptedException {
        System.out.println(cahceBuilder.get("lhc"));
        System.out.println(cahceBuilder.get("jay"));
        System.out.println(cahceBuilder.get("jasmine"));
        scheduledExecutorService.scheduleAtFixedRate(new RefreshJob(),0,1000,TimeUnit.MILLISECONDS);
        Thread.sleep(3000);
        System.out.println("again lhc:--"+cahceBuilder.get("lhc"));
    }
}
