package org.luisyang.integration.spring;

import org.luisyang.integration.spring.exception.PerformanceException;
import org.luisyang.integration.spring.service.impl.Instrumentalist;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public static void main(String[] args) throws PerformanceException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		Instrumentalist performer = (Instrumentalist) ctx.getBean("kenny");

		
		performer.perform();

	}
}
