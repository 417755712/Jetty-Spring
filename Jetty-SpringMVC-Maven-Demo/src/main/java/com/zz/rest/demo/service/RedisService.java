package com.zz.rest.demo.service;

import com.zz.rest.demo.constant.Constant;
import com.zz.rest.demo.utility.LogUtility;
import com.zz.rest.demo.utility.StringUtility;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单链接的redis连接池,可拓展为不同ip:port 多链接型连接池
 * 
 * @author zz
 *
 */
public class RedisService {

	// jedis 连接池
	private JedisPool jedisPool;

	/**
	 * 初始化连接池,交给Spring管理
	 * 
	 * @param config
	 * @param ip
	 * @param port
	 */
	public RedisService(JedisPoolConfig config, String ip, int port, String password) {
		try {
			// 连接默认超时时间2s
			if (StringUtility.isNullOrEmpty(password))
				jedisPool = new JedisPool(config, ip, port);
			else
				jedisPool = new JedisPool(config, ip, port, 2000, password);
			ping();
			LogUtility.businessLog().info("==================");
			LogUtility.businessLog().info("REDIS INIT SUCCESS");
			LogUtility.businessLog().info("==================");
		} catch (Exception e) {
			LogUtility.businessLog().error("Redis Init Error:" + e.getMessage());
		}
	}

	public String ping() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.ping();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void set(String key, String value) {
		Jedis jedis = null;
		key = Constant.REDIS_PREFIX + key;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void setex(String key, int seconds, String value) {
		Jedis jedis = null;
		key = Constant.REDIS_PREFIX + key;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, value);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		key = Constant.REDIS_PREFIX + key;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void del(String key) {
		Jedis jedis = null;
		key = Constant.REDIS_PREFIX + key;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public void expire(String key, int seconds) {
		Jedis jedis = null;
		key = Constant.REDIS_PREFIX + key;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, seconds);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
}
