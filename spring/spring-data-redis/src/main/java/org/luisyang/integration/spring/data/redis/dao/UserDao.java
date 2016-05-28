package org.luisyang.integration.spring.data.redis.dao;

import org.luisyang.integration.spring.data.redis.domain.User;

public interface UserDao {
	
	public void saveUser(final User user);

	public User getUser(final long id);
}
