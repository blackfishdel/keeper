package com.del.keeper.core.redis;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.del.keeper.core.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis cache数据操作
 * 
 * @author xie
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractRedisCacheDao<V> {
    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    protected static final ObjectMapper mapper = new ObjectMapper();

    protected static final String REDIS_CODE = "UTF-8";

    @Resource
    protected StringRedisTemplate redisTemplate;

    protected Class<V> vClass;

    /**
     * 测试是否能连上redis服务器
     *
     * @return
     */
    protected String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    /**
     * 清空redis服务器
     *
     * @return
     */
    protected boolean flushDB() {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }

    /**
     * 查询该redis服务器数据个数
     *
     * @return
     */
    protected long dbSize() {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * 判断redis服务器是否存在该key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    public abstract V selectByKey(final Class<V> clazz, final String key);

    public abstract List<V> getValuesByPattern(final Class clazz, final String pattern);

    public abstract List<V> getValuesByPattern(final Class clazz, final String pattern, final String special);

    public abstract List<V> selectListByKeys(final Class<V> clazz, final String... keys);

    public abstract Boolean insert(final String key, final V value, final Long time) throws ServiceException;

    public abstract Boolean update(final String key, final V value, final Long time) throws ServiceException;

    public abstract void delete(final String key);
}
