package org.luisyang.spring.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.spring.basic.config.CustomizedPropertyPlaceholderConfigurer;
import org.luisyang.spring.basic.domain.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class PropertiesTest {

	@Autowired
	private Properties properties;
	
	@Test
	public void testProperties(){
		
		System.out.println(CustomizedPropertyPlaceholderConfigurer.getContextProperty("a"));
		System.out.println(properties.toString());
	}
	
}
