package org.luisyang.integration.orm.hibernate.dao.impl;

import org.hibernate.Session;
import org.luisyang.integration.orm.hibernate.dao.UserDao;
import org.luisyang.integration.orm.hibernate.domain.User;
import org.luisyang.integration.orm.hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}

}
