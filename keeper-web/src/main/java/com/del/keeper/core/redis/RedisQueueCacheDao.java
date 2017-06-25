package com.del.keeper.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * redis cache数据操作
 * 
 * @author xie
 *
 */
//@Repository
public class RedisQueueCacheDao<K, V> {
    protected static final Logger log = LoggerFactory.getLogger(RedisQueueCacheDao.class);

    @Resource
    protected RedisTemplate<K, V> objectRedisTemplate;

    /**
     * 压栈(左)
     *
     * @param key
     * @param value
     * @return
     */
    public Long push(K key, V value) {
        return objectRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出栈(左)
     *
     * @param key
     * @return
     */
    public V pop(K key) {
        return objectRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 入队（右）
     *
     * @param key
     * @param value
     * @return
     */
    public Long in(K key, V value) {
        return objectRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 出队（右）
     *
     * @param key
     * @return
     */
    public V out(K key) {
        return objectRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 栈/队列长
     *
     * @param key
     * @return
     */
    public Long length(K key) {
        return objectRedisTemplate.opsForList().size(key);
    }

    /**
     * 范围检索
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<V> range(K key, int start, int end) {
        return objectRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取整个列表
     * 
     * @param key
     * @return
     */
    public List<V> getAll(K key) {
        return objectRedisTemplate.opsForList().range(key, 0, length(key));
    }

    /**
     * 删除
     *
     * @param key
     * @param i
     *            为0在队列中全部删除，负数从尾到头删除个数，正数从头到尾删除个数
     * @param value
     */
    public void remove(K key, long i, V value) {
        objectRedisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 查询
     *
     * @param key
     * @param index
     * @return
     */
    public V index(K key, long index) {
        return objectRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 置值
     *
     * @param key
     * @param index
     * @param value
     */
    public void set(K key, long index, V value) {
        objectRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 裁剪
     *
     * @param key
     * @param start
     * @param end
     */
    public void trim(K key, long start, int end) {
        objectRedisTemplate.opsForList().trim(key, start, end);
    }

}
