package org.luisyang.integration.spring.web.service;

import java.util.List;

import org.luisyang.integration.spring.web.model.UserModel;

/**
 * 用户服务Service
 *  
 * @author apple
 */
public interface UserService {

	void create(UserModel user);

	void update(UserModel user);

	UserModel get(String username);

	void delete(UserModel user);

	List<UserModel> list();

}
