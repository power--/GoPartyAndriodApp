package com.goparty.net;

public class HttpException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int code;
	private String data;
	
	public HttpException(int code, String data) {
		super(String.format("HTTP Code {0}: {1}", code, data));
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
