package org.luisyang.integration.cache.redis.sharding;

import org.luisyang.integration.cache.redis.ShardedJedisSentinelPool;

import junit.framework.TestCase;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class ShardedJedisSentinelPoolSpringTest extends TestCase {

	public void testX() throws Exception {

		/*ApplicationContext ac = new ClassPathXmlApplicationContext("redis.xml");
		ShardedJedisSentinelPool pool = (ShardedJedisSentinelPool) ac.getBean("shardedJedisPool");

		ShardedJedis j = null;
		for (int i = 0; i < 100; i++) {
			try {
				j = pool.getResource();
			    j.set("KEY: " + i, "" + i);
			    System.out.print(i);
			    System.out.print(" ");
			    Thread.sleep(1000);
			    pool.returnResource(j);
			} catch (JedisConnectionException e) {
				System.out.print("x");
				i--;
				Thread.sleep(1000);
			}
		}
		
		System.out.println("");
		
		for (int i = 0; i < 100; i++) {
			try {
				j = pool.getResource();
				assertEquals(j.get("KEY: " + i), "" + i);
				System.out.print(".");
				Thread.sleep(500);
				pool.returnResource(j);
			} catch (JedisConnectionException e) {
				System.out.print("x");
				i--;
				Thread.sleep(1000);
			}
		}
	  
		pool.destroy();*/
  	}
}