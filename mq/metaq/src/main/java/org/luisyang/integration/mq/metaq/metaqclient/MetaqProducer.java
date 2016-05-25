/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: MetaqProducer.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.metaqclient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.luisyang.integration.mq.metaq.api.MqEventBean;
import org.luisyang.integration.mq.metaq.api.MqProducer;
import org.luisyang.integration.mq.metaq.api.exception.MetaqProducerException;
import org.luisyang.integration.mq.metaq.constant.MqConstants;
import org.luisyang.integration.mq.metaq.utils.ObjectSerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;

/**
 * 
 * 功能描述： MetaQ生产者
 * 
 * @author ly
 *
 */
public class MetaqProducer implements MqProducer {
    

	/**
	 * 日志组件
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateMessageListener.class);

	private static MessageProducer producer = null;

	private String defaultTopic;

	public void setDefaultTopic(String defaultTopic) {
		this.defaultTopic = defaultTopic;
	}

	@Autowired
	MetaqTemplate metaqTemplate;

	/**
	 * 
	 */
	@PostConstruct 
	private void init() {
		System.out.println("MetaqProducer:init");
		//getProducer();
	}

	/**
	 * 
	 */
	@PreDestroy
	private void destory(){
		System.out.println("MetaqProducer:destory");
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
	
	@SuppressWarnings("static-access")
	private MessageProducer getProducer() {
		if (this.producer == null) {
			this.producer = metaqTemplate.getMessageSessionFactory().createProducer();
			System.out.println("do createProducer ");
		}

		return this.producer;
	}

	/**
	 * 
	 */
	@Override
	public void publish(String topic, MqEventBean eventBean) throws MetaqProducerException {
		eventBean.setHeadValue(MqConstants.HEAD_EVENT_TOPIC, topic);
		this.publish(eventBean);
	}

	/**
	 * 
	 */
	@Override
	public void publish(MqEventBean eventBean) throws MetaqProducerException {

		logger.info("MeatQ消息发送开始");

		boolean hasException = false;
		String errorMessage = "";
		
		MessageProducer producer = this.getProducer();
		producer.setDefaultTopic(this.defaultTopic);
		String topic = eventBean.getHeadValue(MqConstants.HEAD_EVENT_TOPIC);
		String contents = eventBean.toJSON();
		//System.out.println("this.defaultTopic:" + this.defaultTopic);
		System.out.println("topic:" + topic + "|contents:" + contents);

		if (null == topic || topic.isEmpty()) {
			throw new MetaqProducerException(MqConstants.METAQ_SEND_ERROR_CODE_03, 
					MqConstants.METAQ_SEND_ERROR_MSG_03);
		}

		try {
			byte[] data = ObjectSerializeUtils.ObjectToByte((Object) eventBean);
			if (data.length > MqConstants.METAQ_PAYLOAD_MAXSIZE) {
				throw new MetaqProducerException(MqConstants.METAQ_SEND_ERROR_CODE_02,
						MqConstants.METAQ_SEND_ERROR_MSG_02);
			}

			Message message = new Message(topic, data);
			SendResult sendResult = producer.sendMessage(message);
			if (!sendResult.isSuccess()) {
				errorMessage = sendResult.getErrorMessage();
				System.err.println("MeatQ消息发送失败,错误消息内容:"+ sendResult.getErrorMessage());
				hasException = true;

			} else {
				//消息发送所到达的分区
				System.out.println("MeatQ消息发送成功,消息发送所到达的分区： " + sendResult.getPartition());
				logger.info("MeatQ消息发送成功，消息发送所到达的分区：{}", sendResult.getPartition());
			}
			 
		} catch (MetaClientException e1) {
			e1.printStackTrace();

			hasException = true;
		} catch (InterruptedException e1) {
			e1.printStackTrace();

			hasException = true;
		}

		if (true == hasException && !"".equals(errorMessage)) {
			throw new MetaqProducerException(MqConstants.METAQ_SEND_ERROR_CODE_04,
					MqConstants.METAQ_SEND_ERROR_MSG_04 + errorMessage);
		} else if (hasException) {
			logger.info("MeatQ消息发送异常发生");
			throw new MetaqProducerException();
		}

		logger.info("MeatQ消息发送结束");
	}
}
