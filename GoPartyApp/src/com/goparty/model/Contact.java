package com.goparty.model;

import java.util.ArrayList;

public class Contact {
	private String id;
	private String nickName;
	private long birthdate;
	private String gender;
	private String location;
	private String signature;
	private String photo;
	private String remarkName;
	
	private ArrayList<ContactGroup> groups;
	
	public Contact() {
	}
	
	public Contact(
			String nickName,
			long birthdate,
			String gender,
			String location,
			String signature,
			String photo,
			String remarkName) {
		this.nickName = nickName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.location = location;
		this.signature = signature;
		this.photo = photo;
		this.remarkName = remarkName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public ArrayList<ContactGroup> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<ContactGroup> groups) {
		this.groups = groups;
	}
	
	
	/*
//	private String faceUrl;
//	private String name;
//	private String signature;

	private int id; 
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	*/
}
