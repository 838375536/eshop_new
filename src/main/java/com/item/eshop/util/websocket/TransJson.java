package com.item.eshop.util.websocket;

public class TransJson {
	
	private Integer code;
	
	private Object msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public TransJson(Integer code, Object msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public TransJson() {
		super();
	}
	


}
