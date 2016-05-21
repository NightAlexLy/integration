package org.luisyang.integration.spring;

import org.luisyang.integration.spring.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest2 {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
		
		User user = context.getBean(User.class);
		
		System.out.println(user.toString());
		
		context.start();
		
	}
}
