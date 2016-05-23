package org.luisyang.spring.basic;

import org.luisyang.spring.basic.exception.PerformanceException;
import org.luisyang.spring.basic.service.impl.Instrumentalist;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public static void main(String[] args) throws PerformanceException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		Instrumentalist performer = (Instrumentalist) ctx.getBean("kenny");
		
		performer.perform();

	}
}
