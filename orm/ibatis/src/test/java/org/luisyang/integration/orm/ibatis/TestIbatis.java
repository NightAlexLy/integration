package org.luisyang.integration.orm.ibatis;

import java.util.List;

import org.luisyang.integration.orm.ibatis.dao.UserDao;
import org.luisyang.integration.orm.ibatis.dao.impl.UserDaoImpl;
import org.luisyang.integration.orm.ibatis.domain.User;

public class TestIbatis {

	public static void main(String[] args) {

		UserDao userDao = new UserDaoImpl();
		System.out.println("测试插入");
		User user = new User();
		user.setName("李四");
		user.setAge(123);
		System.out.println(userDao.addUser(user));
		System.out.println("测试根据id查询");
		System.out.println(userDao.selectUserById(1));
		System.out.println("测试模糊查询");
		List<User> mohuLists = userDao.selectUserByName("李");
		for (User student : mohuLists) {
			System.out.println(student);
		}
		System.out.println("测试查询所有");
		List<User> students = userDao.selectAllUser();
		for (User student : students) {
			System.out.println(student);
		}
		System.out.println("根据id删除学生信息");
		System.out.println(userDao.deleteUser(3));
		System.out.println("测试更新学生信息");
		User updateStudent = new User();
		updateStudent.setId(4);
		updateStudent.setName("李四1");
		updateStudent.setAge(1234555);
		System.out.println(userDao.updateUser(updateStudent));

	}
}
