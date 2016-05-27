package org.luisyang.integration.spring.jdbc.dao;

import java.util.List;

import org.luisyang.integration.spring.jdbc.domain.Student;

public interface IStudentDao {

	public List<Student> findAll();

	public int delete(int id);

	public int insert(Student student);
}
