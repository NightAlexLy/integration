package org.luisyang.integration.orm.hibernate.dao;

import org.luisyang.integration.orm.hibernate.domain.User;

public interface UserDao {
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);
	
}
