/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: MqConsumerService.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.api;

/**
 * 
 * 功能描述： MQ消费者服务接口
 * 
 * @author ly
 *
 */
public interface MqConsumerService {

	/**
	 * 执行取得消息的相应处理
	 * 
	 * @param eventBean
	 */
	void exec(MqEventBean eventBean);
	
}
