package org.luisyang.integration.mq.metaq.api;

import java.io.Serializable;
import java.util.HashMap;

import org.luisyang.integration.mq.metaq.constant.MqConstants;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author ly
 *
 */
public class MqEventBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1636659929848706920L;

	/**
	 * 消息head
	 */
	private HashMap<String, String> head = new HashMap<String, String>();
	/**
	 * 消息body
	 */
	private HashMap<String, Object> body = new HashMap<String, Object>();

	/**
	 * EventBean
	 */
	public MqEventBean() {

		// String ts = (new java.text.SimpleDateFormat("yyyy/MM/dd
		// HH:mm:ss.SSS")).format(System.currentTimeMillis());
		// String ts = (new java.text.SimpleDateFormat("yyyy/MM/dd
		// HH:mm:ss.SSSZ")).format(System.nanoTime());
		// System.nanoTime()
		// String ts=String.valueOf(System.currentTimeMillis());
		String ts = String.valueOf(System.nanoTime());
		setHeadValue(MqConstants.HEAD_EVENT_TS, ts);
		//System.out.println("ts:" + ts);

	}

	/**
	 * getHead
	 * 
	 * @return
	 */
/*	public Hashtable<String, String> getHead() {
		return head;
	}*/

	/**
	 * setHead
	 * 
	 * @param head
	 *            head
	 */
/*	public void setHead(Hashtable<String, String> head) {
		this.head = head;
	}*/

	/**
	 * getBody
	 * 
	 * @return
	 */
/*	public Hashtable<String, Object> getBody() {
		return body;
	}*/

	/**
	 * setBody
	 * 
	 * @param body
	 *            body
	 */
/*	public void setBody(Hashtable<String, Object> body) {
		this.body = body;
	}*/

	/**
	 * setHeadValue
	 * 
	 * @param k
	 *            key
	 * @param v
	 *            value
	 */
	public void setHeadValue(String k, String v) {
		head.put(k, v);
	}

	/**
	 * setBodyValue
	 * 
	 * @param k
	 *            key
	 * @param v
	 *            value
	 */
	public void setBodyValue(String k, Object v) {
		body.put(k, v);
	}

	/**
	 * getHeadValue
	 * 
	 * @param k
	 *            key
	 * @return
	 */
	public String getHeadValue(String k) {
		return head.get(k);
	}

	/**
	 * getBodyValue
	 * 
	 * @param k
	 *            key
	 * @return
	 */
	public Object getBodyValue(String k) {
		return body.get(k);
	}

	/**
	 * toJSON
	 * 
	 * @return
	 */
	public String toJSON() {
		return JSON.toJSONString(this);
	}

	/**
	 * EventBean
	 * 
	 * @param s
	 *            s
	 * @return
	 */
	/*
	 * public static MqEventBean fromJSON(String s) { return JSON.parseObject(s,
	 * MqEventBean.class); }
	 */
}
