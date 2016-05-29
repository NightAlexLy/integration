package org.luisyang.integration.orm.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 教师类 通过注解的方式
 * 
 * @author apple
 */

@Table(name = "TEACHER")
@Entity
public class Teacher {

	private int id;
	private String name;
	private int age;
	private String test;

	@Id //主键
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="AGE")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Transient //表示改字段不持久化
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
