package org.luisyang.integration.orm.ibatis.dao;

import java.util.List;

import org.luisyang.integration.orm.ibatis.domain.User;

/**
 * @author apple
 *
 */
public interface UserDao {

	public boolean addUser(User user);
	
	public boolean deleteUser(int id);
	
	public boolean updateUser(User user);
	
	public List<User> selectAllUser();
	
	public List<User> selectUserByName(String name);
	
	public User selectUserById(int id);
}
