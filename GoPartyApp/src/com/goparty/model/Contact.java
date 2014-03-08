package com.goparty.model;

public class Contact {
	private String faceUrl;
	private String name;
	private String signature;
	
	public Contact(String faceUrl, String name, String signature) {
		this.faceUrl = faceUrl;
		this.name = name;
		this.signature = signature;
	}
	
	public String getFaceUrl() {
		return faceUrl;
	}
	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
