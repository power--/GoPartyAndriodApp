package com.goparty.model;

import java.util.Date;

public class Contact {
//	private String faceUrl;
//	private String name;
//	private String signature;

	private String id; 
    private String location; 
    private String nickName; //"ChenBo", 
    private String photo; 	//"http://t1.qlogo.cn/mbloghead/ec65faaa2a107d6438c8/100", 
    private String signature; //"everything is good", 
    private Date birthdate; //": "2014-03-02T21:38:17+08:00", 
    private String gender;	//"M"
	
	public Contact(String location,
				String nickName,
				String photo,
				String signature,
				Date birthdate,
				String gender) {
//		this.faceUrl = faceUrl;
//		this.name = name;
//		this.signature = signature;
		
		this.setLocation(location); 
	    this.setNickName(nickName);
	    this.setPhoto(photo);
	    this.setSignature(signature);
	    this.setBirthdate(birthdate);
	    this.setGender(gender);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
}
