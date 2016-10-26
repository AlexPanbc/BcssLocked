package test.testRedis;


import com.pbc.utils.Tools.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 功能：测试redis各种存取操作
 * Created by LiuHuiChao on 2016/10/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/applicationContext-redis.xml"})
public class RedisClassTest {

    @Resource
    RedisDao redisDao;

    /**
     * 功能：测试string的读取
     * 测试结果：已通过
     * 作者：liuhuichao
     * 时间：2016年10月3日16:20:52
     */
    @Test
    public void testRedisPutGet(){
        redisDao.set("test","testValue");
        String test=redisDao.get("test");
        System.out.println(test);
    }

    /**
     * 功能：测试redis的并发量
     */
    @Test
    public void testRedisCurcrrence(){
        

    }

}
