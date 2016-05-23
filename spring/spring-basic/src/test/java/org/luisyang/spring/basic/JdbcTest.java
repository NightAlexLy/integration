package org.luisyang.spring.basic;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.spring.basic.config.CustomizedPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class JdbcTest {

	@Autowired
	private DriverManagerDataSource dataSource;
	
	@Test
	public void testJDBC() throws SQLException{
		
		System.out.println(CustomizedPropertyPlaceholderConfigurer.getContextProperty("jdbc.password"));
		
		System.out.println(dataSource);
		
		System.out.println(dataSource.getConnection().prepareStatement("select * from user").executeQuery().next());;
	}
}
