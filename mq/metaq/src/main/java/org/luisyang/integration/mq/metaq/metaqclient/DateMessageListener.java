/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: DateMessageListener.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.metaqclient;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.luisyang.integration.mq.metaq.api.MqConsumerService;
import org.luisyang.integration.mq.metaq.api.MqEventBean;
import org.luisyang.integration.mq.metaq.constant.MqConstants;
import org.luisyang.integration.mq.metaq.utils.ObjectSerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;

/**
 * 
 * 功能描述： MetaQ消费者Listener
 * 
 * @author ly
 *
 */
public class DateMessageListener implements MessageListener {

	@Autowired
	MqConsumerService consumerService;

	private int threadPool = 30;
	private /*static*//*final*/ ExecutorService executor = Executors.newFixedThreadPool(threadPool);

	public void setThreadPool(int threadPool) {
		this.threadPool = threadPool;
	}
	
	@SuppressWarnings("unused")
	private void init(){
		System.out.println("threadPool is " + this.threadPool);
		this.executor = Executors.newFixedThreadPool(threadPool);
	}
	
	/**
	 * 
	 */
	@Override
	public void recieveMessages(Message message) throws InterruptedException {
		
		System.out.println("DateMessageListener::recieveMessages>>>>>");
		
		byte[] data = message.getData();
		Long id = message.getId();
		
		MqEventBean eb = (MqEventBean) ObjectSerializeUtils.ByteToObject(data);
		eb.setHeadValue(MqConstants.HEAD_EVENT_ID, String.valueOf(id));
		
		consumerService.exec(eb);
		
		System.out.println("DateMessageListener::recieveMessages<<<<<");
	}

	/**
	 * 
	 */
	@Override
	public Executor getExecutor() {
		//return null;
		return this.executor;
	}

}
