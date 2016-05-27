package org.luisyang.integration.spring.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.luisyang.integration.spring.jdbc.dao.IUserTestDAO;
import org.luisyang.integration.spring.jdbc.domain.UserTest;

public class UserTestDAOImpl extends BaseDAO implements IUserTestDAO {
	
	public void insert(UserTest user) {
		String name = user.getName();
		int age = user.getAge().intValue();

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO user_test (name,age) " + "VALUES('" + name + "'," + age + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public UserTest find(Integer id) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM user_test WHERE id=" + id.intValue());
			if (result.next()) {
				Integer i = new Integer(result.getInt(1));
				String name = result.getString(2);
				Integer age = new Integer(result.getInt(3));

				UserTest user = new UserTest();
				user.setId(i);
				user.setName(name);
				user.setAge(age);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
