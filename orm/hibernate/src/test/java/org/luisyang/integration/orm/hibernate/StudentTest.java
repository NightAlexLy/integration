package org.luisyang.integration.orm.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.luisyang.integration.orm.hibernate.domain.Student;

public class StudentTest {

	
	public static void main(String[] args) {

		Student student = new Student();
		student.setName("zhangsan");
		student.setAge(123);
		// 注此处并不是使用org.hibernate.cfg.Configuration来创建Configuration
		// 而使用org.hibernate.cfg.AnnotationConfiguration来创建Configuration，这样就可以使用Annotation功能
		Configuration cfg = new AnnotationConfiguration();

		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();

		session.close();
		sf.close();

	}

}
