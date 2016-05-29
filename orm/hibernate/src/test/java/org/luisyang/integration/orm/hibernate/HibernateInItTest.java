package org.luisyang.integration.orm.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.luisyang.integration.orm.hibernate.domain.User;


/**
 *  Hibernate  入门示例
 *  
 *  	1.初始化配置
 *  	2.创建会话工厂
 *  	3.创建当前会话
 *  	4.执行操操  save/update/delete／read  CRUD   是否开启事务。  begin／commit／rollback
 *      5.关闭当前会话
 *      6.关闭工厂
 *      
 *      
 *      	getCurrentSession()  获得当前会话
 *      	getCurrentTransaction()  获得当前事务
 *  		 
 * 
 * @author apple
 */
public class HibernateInItTest {

	public static void main(String[] args) {

		Configuration cfg = null;
		SessionFactory sf = null;
		Session session = null;

		User s = new User();
		s.setId(2);   //如果ID是自增的。 此处设置值也不会生效。
		s.setName("s1");
		s.setAge(1);

		/*
		 * org.hibernate.cfg.Configuration类的作用：
		 * 读取hibernate配置文件(hibernate.cfg.xml或hiberante.properties)的. new
		 * Configuration()默认是读取hibernate.properties 所以使用new
		 * Configuration().configure();来读取hibernate.cfg.xml配置文件
		 */
		cfg = new Configuration().configure();

		/*
		 * 创建SessionFactory 一个数据库对应一个SessionFactory SessionFactory是线线程安全的。
		 */
		sf = cfg.buildSessionFactory();

		try {
			// 创建session
			// 此处的session并不是web中的session
			// session只有在用时，才建立concation,session还管理缓存。
			// session用完后，必须关闭。
			// session是非线程安全，一般是一个请求一个session.
			session = sf.openSession();

			// 手动开启事务(可以在hibernate.cfg.xml配置文件中配置自动开启事务)
			session.beginTransaction();
			/*
			 * 保存数据，此处的数据是保存对象，这就是hibernate操作对象的好处，
			 * 我们不用写那么多的JDBC代码，只要利用session操作对象，至于hibernat如何存在对象，这不需要我们去关心它，
			 * 这些都有hibernate来完成。我们只要将对象创建完后，交给hibernate就可以了。
			 */
			session.save(s);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
		} finally {
			// 关闭session
			session.close();
			sf.close();
		}
	}

}
