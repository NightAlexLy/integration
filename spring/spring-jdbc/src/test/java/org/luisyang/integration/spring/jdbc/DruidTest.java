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
 * Alibaba Druid数据源测试
 * 
 *    http://blog.csdn.net/chenleixing/article/details/45165753
 *    http://blog.csdn.net/chenleixing/article/details/45003753
 *    http://blog.csdn.net/white__cat/article/details/38067589
 * 
 * @author ly
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-Druid.xml")
public class DruidTest {
	
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
