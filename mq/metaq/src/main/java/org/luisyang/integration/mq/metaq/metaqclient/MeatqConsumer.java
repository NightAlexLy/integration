/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: MeatqConsumer.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.metaqclient;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.luisyang.integration.mq.metaq.api.MqConsumer;
import org.luisyang.integration.mq.metaq.api.exception.MetaqConsumerException;
import org.luisyang.integration.mq.metaq.constant.MqConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 
 * 功能描述： MetaQ消费者
 * 
 * @author ly
 *
 */
public class MeatqConsumer implements MqConsumer {
	/**
	 * 日志组件
	 */
	private static final Logger logger = LoggerFactory.getLogger(MeatqConsumer.class);

	private static Map<String, MessageConsumer> consumerPool = new HashMap<String, MessageConsumer>();

	private String defaultGroup;

	@Autowired
	MetaqTemplate metaqTemplate;

	@Autowired
	MessageListener messageListener;

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}


	/**
	 * 
	 */
	@PostConstruct 
	private void init() {
		System.out.println("MeatqConsumer:init");
	}

	/**
	 * 
	 */
	@PreDestroy
	private void destory(){
		System.out.println("MeatqConsumer:destory");
		try {
			metaqTemplate.getMessageSessionFactory().shutdown();
			metaqTemplate.destroy();
		} catch (MetaClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param topic
	 * @param group
	 * @return
	 */
	private MessageConsumer getGroupConsumer(String topic, String group) {
		String key = topic + group;
		if (!consumerPool.containsKey(key)) {
			MessageConsumer consumer = metaqTemplate.getMessageSessionFactory()
					.createConsumer(new ConsumerConfig(group));

			consumerPool.put(key, consumer);
		}

		return consumerPool.get(key);
	}

	/**
	 * 接受MeatQ消息
	 */
	@Override
	public void receive(String topic) throws MetaqConsumerException {

		this.receive(topic, this.defaultGroup);
	}

	/**
	 * 接受MeatQ消息
	 */
	@Override
	public void receive(String topic, String group) throws MetaqConsumerException {
		if (group == null || group.isEmpty()) {
			throw new MetaqConsumerException(MqConstants.METAQ_RECEIVE_CODE_02, 
					MqConstants.METAQ_RECEIVE_MSG_02);
		}

		if (consumerPool.containsKey(topic + group)) {
			System.out.println(topic + group + "：MeatQ消息已经订阅");

			logger.info("Topic:{0},MeatQ消息已经订阅", topic);
			return;
		}

		logger.info("Topic:{0},MeatQ消息订阅开始", topic);
		try {
			MessageConsumer consumer = this.getGroupConsumer(topic, group);
			consumer.subscribe(topic, MqConstants.METAQ_PAYLOAD_MAXSIZE, messageListener);
			consumer.completeSubscribe();
		} catch (MetaClientException e) {
			e.printStackTrace();

			throw new MetaqConsumerException();
		}

		logger.info("Topic:{0},MeatQ消息订阅结束", topic);
	}

}
