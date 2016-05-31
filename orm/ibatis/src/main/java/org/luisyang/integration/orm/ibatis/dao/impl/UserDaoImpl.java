package org.luisyang.integration.orm.ibatis.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.luisyang.integration.orm.ibatis.dao.UserDao;
import org.luisyang.integration.orm.ibatis.domain.User;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class UserDaoImpl implements UserDao {

	private static SqlMapClient client = null;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object = client.insert("addUser", user);
			System.out.println("添加学生信息的返回值：" + object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Object object = null;
		try {
			object = client.delete("deleteUserById", id);
			System.out.println("删除学生信息的返回值：" + object + "，这里返回的是影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Object object = false;
		try {
			object = client.update("updateStudent", user);
			System.out.println("更新学生信息的返回值：" + object + "，返回影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		List<User> users = null;
		try {
			users = client.queryForList("selectAllUser");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}

	@Override
	public List<User> selectUserByName(String name) {
		// TODO Auto-generated method stub
		List<User> users = null;
		try {
			users = client.queryForList("selectUserByName", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}

	@Override
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = (User) client.queryForObject("selectUserById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

}
