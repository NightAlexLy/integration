package org.luisyang.integration.spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.spring.jdbc.dao.IUserTestDAO;
import org.luisyang.integration.spring.jdbc.domain.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class SpringDAODemo {

	@Autowired
	private IUserTestDAO userDao;
	
	@Test
	public void testDao() {

		UserTest user = new UserTest();
		user.setName("caterpillar");
		user.setAge(new Integer(30));
		
		
		userDao.insert(user); 
        user = userDao.find(new Integer(1)); 
        System.out.println("name: " + user.getName()); 
	}
}
