package com.pbc.utils.Tools;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 功能：redis操作类
 * Created by LiuHuiChao on 2016/10/3.
 */
public class RedisDao {

    private static final Logger logger = Logger.getLogger(RedisDao.class);
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public void set(String key, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.set(key, value);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }


    public String get(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        String result = "";
        try {
            result = jedis.get(key);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;

    }

    public Long hset(String key, String field, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();

        Long result = null;
        try {
            result = jedis.hset(key, field, value);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public String hget(String key, String field) {
        ShardedJedis jedis = shardedJedisPool.getResource();

        String result = null;
        try {
            result = jedis.hget(key, field);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }


    public List<String> lrange(String key, long start, long end) {
        ShardedJedis jedis = shardedJedisPool.getResource();

        List<String> result = null;
        try {
            result = jedis.lrange(key, start, end);
            ;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public Long del(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Long result = null;
        try {
            result = jedis.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public void rpush(String key, String... value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.rpush(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }

    public void lpush(String key, String... value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }

    public void lpop(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.lpop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }

    public void rpop(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.rpop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }


    public void lrem(String key, int count, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            jedis.lrem(key, count, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return;
    }


    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        try {
            this.set(key, value);
            jedis.expire(key, liveTime);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        boolean result = false;
        try {
            result = jedis.exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取一个key的模糊匹配总数
     *
     * @param key
     * @return
     */
    public int getKeyCount(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.getShard(key).keys(key + "*");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result.size();
    }

    /**
     * 查看redis里有多少数据
     */
    @SuppressWarnings("deprecation")
    public long dbSize() {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.getShard("").keys("*");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result.size();
    }

    public long sadd(String key, String... value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        long result = 0l;
        try {
            result = jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public Boolean sismember(String key, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Boolean result = false;
        try {
            result = jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public Set smembers(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Set result = null;
        try {
            result = jedis.smembers(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public long zadd(String key, int sequence, String value) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        long result = 0l;
        try {
            result = jedis.zadd(key, sequence, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }


    public Set<String> zrange(String key, long start, long end) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Set<String> result = null;
        try {
            result = jedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public String hmset(String key, Map map) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        String result = null;
        try {
            result = jedis.hmset(key, map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public Map<String, String> hgetAll(String key) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        Map<String, String> result = null;
        try {
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            shardedJedisPool.returnBrokenResource(jedis);
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}

