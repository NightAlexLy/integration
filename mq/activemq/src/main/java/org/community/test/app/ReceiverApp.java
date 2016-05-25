package org.community.test.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 测试监听消息
 * 
 * @author ly
 */
public class ReceiverApp {
    public static void main( String[] args )
    {
        new ClassPathXmlApplicationContext("spring-activemq.xml", "spring-receiver.xml");
    }
}