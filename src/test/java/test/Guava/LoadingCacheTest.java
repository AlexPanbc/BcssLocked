package test.Guava;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/2
 * \* Time: 下午9:52
 * \* Description:loadingcache测试
 * \
 */
public class LoadingCacheTest {

    private LoadingCache<String,String> cahceBuilder= CacheBuilder.newBuilder()
            .maximumSize(1)
            .refreshAfterWrite(2, TimeUnit.MILLISECONDS)
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
     * 简单loadingCache使用
     *      使用过期
     */
    @Test
    public void simpleUseTest() throws ExecutionException, InterruptedException {
        cahceBuilder.get("lhc1");
        cahceBuilder.get("momoda2");
        Thread.sleep(6000);
        cahceBuilder.get("momoda2");//缓存项只有在被检索时才会真正刷新，即只有刷新间隔时间到了你再去get(key)才会重新去执行Loading否则就算刷新间隔时间到了也不会执行loading操作
        cahceBuilder.get("jay3");
    }

}
