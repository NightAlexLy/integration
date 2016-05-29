package org.luisyang.integration.container.jetty.plugin.controller;

import org.luisyang.integration.container.jetty.plugin.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@RequestMapping("/user")  
public class UserController {
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User view(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName("zhang");
		return user;
	}
	
}
