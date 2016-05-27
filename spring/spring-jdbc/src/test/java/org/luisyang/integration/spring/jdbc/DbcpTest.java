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
 * DBCP数据源配置
 * 
 *    http://blog.sina.com.cn/s/blog_8c38b8b7010149zt.html
 * 
 * @author ly
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-c3p0.xml")
public class DbcpTest {
	
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

