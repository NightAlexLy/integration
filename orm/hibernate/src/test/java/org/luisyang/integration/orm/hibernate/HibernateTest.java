package org.luisyang.integration.orm.hibernate;

import org.luisyang.integration.orm.hibernate.dao.UserDao;
import org.luisyang.integration.orm.hibernate.dao.impl.UserDaoImpl;
import org.luisyang.integration.orm.hibernate.domain.User;
import org.luisyang.integration.orm.hibernate.util.HibernateUtil;

/**
 *  
 *  Hibernate测试
 * 
 * 		http://blog.csdn.net/tanyit/article/details/6987279
 *  
 * @author apple
 */
public class HibernateTest {

	
	public static void main(String[] args) {
		
		User user = new User();
		user.setName("test001");
		user.setAge(12);
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		int id = user.getId();
		System.out.println("user id ::" + id);
		
		
		user = new User();
		user.setAge(1234);
		user.setName("test02");
		user.setId(1);
		
		userDao.update(user);
		
		
		user = new User();
		user.setId(id);
		
		userDao.delete(user);
		
		
		HibernateUtil.getSessionFactory().close();	
		
	}
	
	
}
