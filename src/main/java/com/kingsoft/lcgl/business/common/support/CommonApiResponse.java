package com.kingsoft.lcgl.business.common.support;

import java.io.Serializable;



public class CommonApiResponse implements Serializable {
	private  int code = 0;
	private  String msg;
	private  String data;



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
