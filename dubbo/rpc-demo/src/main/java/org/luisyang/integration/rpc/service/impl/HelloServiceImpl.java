package org.luisyang.integration.rpc.service.impl;

import org.luisyang.integration.rpc.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String hello(String str) {

		return "hello  " +  str;
	}

}
