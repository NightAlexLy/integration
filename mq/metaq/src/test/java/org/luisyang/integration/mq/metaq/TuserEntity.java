package org.luisyang.integration.mq.metaq;

import java.io.Serializable;
//import javax.persistence.*;

/**
 * 表Entity
 * 
 * @author ly
 *
 */
//@Entity(name = "t_user")
public class TuserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3006249768637497830L;

	private Long id;
	private int age;
	private String des;
	private String name;
	private Long userId;

/*	public TuserEntity() {
	}*/

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//@Column(name="age")
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	///@Column(name="des", nullable = false)
	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

//	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Column(name="user_id") 
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}