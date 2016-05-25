/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: MqProducer.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.api;

import org.luisyang.integration.mq.metaq.api.exception.MetaqProducerException;

/**
 * 
 * 功能描述： MQ生产者接口
 * 
 * @author ly
 *
 */
public interface MqProducer {
	
	/**
	 * 
	 * @param topic
	 * @param eventBean
	 * @throws MetaqProducerException
	 */
	void publish(String topic, MqEventBean eventBean) throws MetaqProducerException;
	
	/**
	 * 使用该方法，topic必须在MqEventBean的head部指定
	 * eventBean.setHeadValue(MqConstants.HEAD_EVENT_TOPIC, topic);
	 * 
	 * @param eventBean
	 * @throws MetaqProducerException
	 */
	void publish(MqEventBean eventBean) throws MetaqProducerException;
	
}
