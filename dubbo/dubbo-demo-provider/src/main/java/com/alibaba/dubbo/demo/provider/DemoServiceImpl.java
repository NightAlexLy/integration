/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.demo.provider;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.rpc.RpcContext;

public class DemoServiceImpl implements DemoService {
	private static final Log log = LogFactory.getLog(DemoService.class);
	
    public String sayHello(String name) {
    	 /*
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] service start invoking...");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        System.out.println(c.get(Calendar.SECOND));
        c.add(Calendar.SECOND, 5);
        System.out.println(c.get(Calendar.SECOND));
        Calendar c2 = Calendar.getInstance();
        while(true) {
        	c2.setTime(new Date());
        	if(c.before(c2)) {
        		System.out.println("1111111111");
        		break;
        	}
        }
        */
    	log.info("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response form provider1: " + RpcContext.getContext().getLocalAddress();
    }

    public String say(String msg) {
        
        log.info("Say" + msg);
        return "Say" + msg;
    }
    
    
    
    
    
    
}