package com.del.keeper.core.redis;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 操作redis map
 * @author xie
 *
 * @param <K> key
 * @param <V> value
 */
@Repository
public class RedisHashDao<K, V> {

	@Resource
	protected RedisTemplate<K, V> objectRedisTemplate;

	public Boolean exists(K key, V hashKey) {
		return objectRedisTemplate.opsForHash().hasKey(key, hashKey);
	}

	public void put(K key, V hashKey, Object value) {
		objectRedisTemplate.opsForHash().put(key, hashKey, value);
	}

	public Boolean putIfAbsent(K key, V hashKey, Object value) {
		return objectRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	public Object getValue(K key, V hashKey) {
		return objectRedisTemplate.opsForHash().get(key, hashKey);
	}

	@SuppressWarnings("unchecked")
	public Set<V> keySet(K key) {
		return (Set<V>) objectRedisTemplate.opsForHash().keys(key);
	}

	@SuppressWarnings("unchecked")
	public Map<V, Object> entries(K key) {
		return (Map<V, Object>) objectRedisTemplate.opsForHash().entries(key);
	}

	public void delete(K key, Object... hashKeys) {
		objectRedisTemplate.opsForHash().delete(key, hashKeys);
	}
}
