package com.moon.ssm.exception;

/**
 * 自定义异常
 * @author MoonZero
 */
public class SsmException extends Exception {
	private static final long serialVersionUID = 1L;

	// 异常信息
	private String msg;

	public SsmException() {
		super();
	}

	public SsmException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
