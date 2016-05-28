package org.luisyang.integration.spring.data.redis;

import org.luisyang.integration.spring.data.redis.dao.UserDao;
import org.luisyang.integration.spring.data.redis.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * 	java之redis篇(spring-data-redis整合)
 * 	
 *     http://www.cnblogs.com/tankaixiong/p/3660075.html
 * 
 * 	使用Spring Data Redis操作Redis（一）
 * 
 * 	   http://www.tuicool.com/articles/3aAbMz
 * 
 * 	redis实现 spring-redis-data初学习	
 * 
 * 	   http://blog.csdn.net/lang_man_xing/article/details/38317939
 * 
 * @author ly
 */
public class SpringDataRedisTest {

	public static void main(String[] args) {

		try {
			ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
			UserDao userDAO =  (UserDao) ac.getBean("userDao");
			User user1 = new User();
			user1.setId(1);
			user1.setName("obama");
			userDAO.saveUser(user1);
			User user2 = userDAO.getUser(1);
			System.out.println(user2.getName());
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
