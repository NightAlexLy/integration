package org.luisyang.integration.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class IOCTest {

	
	@Autowired
	private User user;
	
	@Test
	public void testIOC(){
		
		System.out.println(user.toString());
		
	}
	
}
