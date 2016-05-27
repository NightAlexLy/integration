package org.luisyang.integration.cache.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	// Redis服务器IP
	private static String ADDR = "192.168.91.135";

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
	private static String AUTH = "admin";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;

	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			//config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			//config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			//jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
}


/**
 * 		Redis异常：
 * 
 * 			1.jedis.exceptions.JedisDataException: ERR Client sent AUTH, but no password is set
 * 
 * 				redis通过属性requirepass 设置访问密码，但没有设置该属性时，客户端向服务端发送AUTH请求，服务端就好返回异常：ERR Client sent AUTH, but no password is set
 * 	
 * 			检查：
 * 				//jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);   //带密码验证
				jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);   //不带密码验证
 *    
 *           
 *          2.redis.clients.jedis.exceptions.JedisDataException: WRONGTYPE Operation against a key holding the wrong kind of value  
 *          
 *          	看起来没有值类型不对的样子。后来发现原来是因为redis数据库中已经存在了相同的key， 而且key对应的值类型并不是HashMap；再调用hmset时，就会抛出此错误。
				把原来的数据清掉，重新运行就没问题了。
 * 
 */
