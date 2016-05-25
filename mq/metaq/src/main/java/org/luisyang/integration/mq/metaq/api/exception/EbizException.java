package org.luisyang.integration.mq.metaq.api.exception;

/**
 * 功能描述：普通业务异常类，不需要触发Transaction时抛出该异常类
 * 
 * @author ly
 * 
 */

public abstract class EbizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2650068159392538066L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
