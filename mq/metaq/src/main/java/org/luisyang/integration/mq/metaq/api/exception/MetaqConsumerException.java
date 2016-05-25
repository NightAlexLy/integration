/*
 * Copyright (C), 2015, IBM GBS China.
 * FileName: MetaqConsumerException.java
 * Author:   guohuac@cn.ibm.com
 * Date:     2015/10/17
 * Description: //模块目的、功能描述
 * History:     //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.luisyang.integration.mq.metaq.api.exception;

import org.luisyang.integration.mq.metaq.constant.MqConstants;

/**
 * 
 * 功能描述： MetaQ消费者异常
 * 
 * @author ly
 *
 */
public class MetaqConsumerException extends EbizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6759908662609965243L;

	/**
	 * 
	 */
	public MetaqConsumerException() {
		super.code = MqConstants.METAQ_RECEIVE_CODE;
		super.msg = MqConstants.METAQ_RECEIVE_MSG;
	}

	/**
	 * 
	 * @param errorCode
	 * @param errorMassage
	 */
	public MetaqConsumerException(String errorCode, String errorMassage) {
		this.code = errorCode;
		this.msg = errorMassage;
	}

}
