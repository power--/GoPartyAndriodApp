package com.goparty.model;

public class ContactGroup {
	private String id;
	private String name;
	private String ownerId;
	
	public ContactGroup() {
		super();
	}

	public ContactGroup(
			String name,
			String ownerId) {
		this.name = name;
		this.ownerId = ownerId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}