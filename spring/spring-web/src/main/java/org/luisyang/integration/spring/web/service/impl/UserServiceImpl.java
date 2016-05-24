package org.luisyang.integration.spring.web.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.luisyang.integration.spring.web.model.UserModel;
import org.luisyang.integration.spring.web.service.UserService;


/**
 * 通过模拟操作Properties文件实现持久化操作
 * 
 * @author apple
 */
public class UserServiceImpl implements UserService {
	
	public static List<UserModel> models = new ArrayList<UserModel>();
	public static Properties prop = new Properties(); 
	
	static{
		
		try {
			prop.load(UserServiceImpl.class.getClassLoader().getResourceAsStream("user.properties"));
			for(Object key : prop.keySet()){
				String value = prop.getProperty(key.toString());
				String[] tempArr = value.split(",");
				if(tempArr.length == 2){
					UserModel user = new UserModel();
					user.setUsername(tempArr[0]);
					user.setRealname(tempArr[1]);
					models.add(user);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void create(UserModel user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UserModel user) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserModel get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserModel user) {
		// TODO Auto-generated method stub
//		if(user)
//			prop.remove(key);
//			prop.
	}

	@Override
	public List<UserModel> list() {
		// TODO Auto-generated method stub
		return models;
	}

}
