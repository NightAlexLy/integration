package org.luisyang.integration.spring.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.spring.jdbc.dao.IStudentDao;
import org.luisyang.integration.spring.jdbc.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *    
 *    Spring：JdbcTemplate使用指南
 * 
 * 		http://www.cnblogs.com/chenying99/archive/2012/08/06/2625936.html
 * 
 * @author ly
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-jdbcTemplate.xml")
public class JdbcTemplateTest {
	
	@Autowired
	private IStudentDao studentDao;

	@Test
	public void testDao() {

		List<Student> students = studentDao.findAll();
		for(Student student : students){
			System.out.println(student.toString());
		}
		
		Student student = new Student();
		student.setName("zhangsan");
		student.setAge(100);
		
		studentDao.insert(student);
		
		studentDao.delete(12313);
	}
}

/**
 *   错误记录：
 *   	1.org.springframework.jdbc.IncorrectResultSetColumnCountException: Incorrect column count: expected 1, actual 3
 *   
 *    类型匹配不正确
 * 
 */
