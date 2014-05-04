package com.goparty.model;

import java.util.Date;

public class User {
	private String id;
	private String nickName;
    private String password;
    private String userName;
    private Date birthdate;
    private boolean admin;
    private String participance;
    
    public User() {
    	
    }
    
    public User(String id, String nickName, String password, String userName) {
    	this.id = id;
    	this.nickName = nickName;
    	this.password = password;
    	this.userName = userName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getParticipance() {
		return participance;
	}

	public void setParticipance(String participance) {
		this.participance = participance;
	}
}
