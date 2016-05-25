package org.luisyang.integration.mq.metaq.api;

import org.luisyang.integration.mq.metaq.api.exception.MetaqConsumerException;

/**
 * 功能描述： MQ消费者接口
 * 
 * @author ly
 */
public interface MqConsumer {
	/**
	 * 
	 * @param topic
	 * @return
	 * @throws MetaqConsumerException
	 */
	void receive(String topic) throws MetaqConsumerException;

	/**
	 * 
	 * @param topic
	 * @param group
	 * @throws MetaqConsumerException
	 */
	void receive(String topic, String group) throws MetaqConsumerException;
}

/*消费者分组(Group)

消费者可以是多个消费者共同消费一个topic下的消息，每个消费者消费部分消息。
这些消费者就组成一个分组，拥有同一个分组名称,通常也称为消费者集群*/