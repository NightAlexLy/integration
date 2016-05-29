package org.luisyang.integration.orm.hibernate;

import org.luisyang.integration.orm.hibernate.dao.UserDao;
import org.luisyang.integration.orm.hibernate.dao.impl.UserDaoImpl;
import org.luisyang.integration.orm.hibernate.domain.User;
import org.luisyang.integration.orm.hibernate.util.HibernateUtil;

import junit.framework.TestCase;

public class UserTest extends TestCase{

	//private static SessionFactory sessionFactory = null;
	private static UserDao userDao = null;
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		//super.setUp();
		//sessionFactory = HibernateUtil.getSessionFactory();µ
		userDao = new UserDaoImpl();
	}
	
	public void testSave(){
		User user = new User();
		user.setName("test001");
		user.setAge(12);
		userDao.save(user);
	}
	
	
	public void testDelete(){
		
		User user = new User();
		user.setId(10);
		
		userDao.delete(user);
				
	}
	
	public void testUpdate(){
		
		User user = new User();
		user.setId(1);
		user.setName("zhangsan001");
		user.setAge(23);
		
		userDao.update(user);
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		//super.tearDown();
		//sessionFactory.close();
		HibernateUtil.getSessionFactory().close();
	}
	
	
}
