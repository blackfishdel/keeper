package com.del.keeper.core.redis;

import com.del.keeper.core.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis cache数据操作
 * 
 * @author xie
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class RedisCacheDao<V> extends AbstractRedisCacheDao {

    /**
     * 通过key查询value
     *
     * @param key
     * @return
     */
    @Override
    public V selectByKey(final Class clazz, final String key) {
        if (!this.exists(key)) {
            return null;
        }
        return redisTemplate.execute(new RedisCallback<V>() {
            public V doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] bKey = serializer.serialize(key);
                    String Value = new String(connection.get(bKey), REDIS_CODE);
                    return (V) mapper.readValue(Value, clazz);
                } catch (IOException e) {
                    log.warn("jackson反序列化失败！", e);
                }
                return null;
            }
        });
    }

    /**
     * 模糊查询
     * 
     * @param key
     * @return
     */
    @Override
    public List<V> getValuesByPattern(final Class clazz, final String pattern) {
        Set<String> s = redisTemplate.keys(pattern + "*");
        List<V> list = new ArrayList<V>();
        for (String key : s) {
            list.add(selectByKey(clazz, key));
        }
        return list;
    }

    /**
     * 模糊查询，有特殊字符检测
     * 
     * @param key
     * @return
     */
    @Override
    public List<V> getValuesByPattern(final Class clazz, final String pattern, final String special) {
        Set<String> s = redisTemplate.keys(pattern + "*");
        List<V> list = new ArrayList<V>();
        for (String key : s) {
            if (!key.contains(special))
                list.add(selectByKey(clazz, key));
        }
        return list;
    }

    /**
     * 批量查询
     *
     * @param keys
     * @return
     */
    @Override
    public List<V> selectListByKeys(final Class clazz, final String... keys) {
        if (keys == null || keys.length == 0) {
            return null;
        }
        List<V> list = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            V v = selectByKey(clazz, keys[i]);
            list.add(v);
        }
        return list;
    }

    /**
     * 新增
     *
     * @param key
     * @param value
     * @param time
     *            超时时间（秒）
     * @return
     * @throws ServiceException
     */
    @Override
    public Boolean insert(final String key, final Object value, final Long time) throws ServiceException {
        if (this.exists(key)) {
            throw new ServiceException("重复插入数据！");
        }
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] bKey = serializer.serialize(key);
                    byte[] bValue = serializer.serialize(mapper.writeValueAsString(value));
                    Expiration expiration = null;
                    if (time != null) {
                        expiration = Expiration.seconds(time);// 设置超时删除时间(单位是秒)
                    }
                    connection.set(bKey, bValue, expiration, null);
                } catch (JsonProcessingException e) {
                    log.warn("jackson序列化失败！", e);
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 更新
     *
     * @param key
     * @param value
     * @param time
     *            超时时间（秒）
     * @return
     * @throws ServiceException
     */
    @Override
    public Boolean update(final String key, final Object value, final Long time) throws ServiceException {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] bKey = serializer.serialize(key);
                    byte[] bValue = serializer.serialize(mapper.writeValueAsString(value));
                    Expiration expiration = null;
                    if (time != null) {
                        expiration = Expiration.seconds(time);// 设置超时删除时间(单位是秒)
                    }
                    connection.set(bKey, bValue, expiration, null);
                } catch (JsonProcessingException e) {
                    log.warn("jackson序列化失败！", e);
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 删除
     *
     * @param key
     */
    @Override
    public void delete(final String key) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] bKey = serializer.serialize(key);
                connection.del(bKey);
                return true;
            }
        });
    }
}
