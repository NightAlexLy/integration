package org.luisyang.integration.mq.metaq.constant;

public class MqConstants {

	/**
	 * 私有构造方法
	 */
	private MqConstants() {

	}

	/**
	 * 消息头信息：消息时间戳
	 */
	public static final String HEAD_EVENT_TS = "timeStamp";

	/**
	 * 消息头信息：消息主题
	 */
	public static final String HEAD_EVENT_TOPIC = "topic";

	/**
	 * 消息头信息：消息组
	 */
	public static final String HEAD_EVENT_GROUP = "group";

	/**
	 * 消息头信息：消息所属应用
	 */
	public static final String HEAD_EVENT_APP = "app";

	/**
	 * 消息头信息：消息的唯一id，系统自动产生
	 */
	public static final String HEAD_EVENT_ID = "id";

	/**
	 * 消息Body，生产与消费预定Key，服务于文件解析服务
	 */
	public static final String FILE_CONTENTS = "fileContents";

	public static final String METAQ_SEND_ERROR_CODE = "MQ_1001";
	public static final String METAQ_SEND_ERROR_MSG = "MeatQ客户端发送消息异常";

	public static final String METAQ_SEND_ERROR_CODE_02 = "MQ_1002";
	public static final String METAQ_SEND_ERROR_MSG_02 = "MeatQ客户端发送消息 > " + MqConstants.METAQ_PAYLOAD_MAXSIZE;

	public static final String METAQ_SEND_ERROR_CODE_03 = "MQ_1003";
	public static final String METAQ_SEND_ERROR_MSG_03 = "MeatQ客户端发送消息时,Topic主题未定义";

	public static final String METAQ_SEND_ERROR_CODE_04 = "MQ_1004";
	public static final String METAQ_SEND_ERROR_MSG_04 = "MeatQ消息发送失败,错误消息内容:";

	public static final String METAQ_RECEIVE_CODE = "MQ_2001";
	public static final String METAQ_RECEIVE_MSG = "MeatQ客户端接受消息异常";

	public static final String METAQ_RECEIVE_CODE_02 = "MQ_2002";
	public static final String METAQ_RECEIVE_MSG_02 = "MetaqCousumer的group分组未指定";

	/**
	 * 消息Payload = 1024 * 1024
	 */
	public static final int METAQ_PAYLOAD_MAXSIZE = 1024 * 1024;

}
