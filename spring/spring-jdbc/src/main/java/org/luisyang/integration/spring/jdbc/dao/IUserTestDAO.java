package org.luisyang.integration.spring.jdbc.dao;

import org.luisyang.integration.spring.jdbc.domain.UserTest;

public interface IUserTestDAO {

	public void insert(UserTest user);

	public UserTest find(Integer id);

}