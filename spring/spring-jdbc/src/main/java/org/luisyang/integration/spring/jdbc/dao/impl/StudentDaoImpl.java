package org.luisyang.integration.spring.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.luisyang.integration.spring.jdbc.dao.IStudentDao;
import org.luisyang.integration.spring.jdbc.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StudentDaoImpl implements IStudentDao{

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from Student", new RowMapper<Student>(){

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				return student;
			}
			
		});
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from Student where id = ?", id);
	}

	@Override
	public int insert(Student student) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(" insert into Student(name,age) values(?,?) ", student.getName(),student.getAge()) ;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
