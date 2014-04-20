package com.goparty.model;

import java.util.ArrayList;
import java.util.List;

public class EventCreateRequest {
	private String title;
	private long startTime;
	private long endTime;
	private String location;
	private String description;
	private List<EventCategory> categories;
	private List<EventCreateMembersRequest> members;
	private EventCreateOwnerRequest owner;
	private String status;	//INIT
	private String visibility;	//V_PUBLIC
	private boolean locationShareable;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EventCategory> getCategories() {
		if (categories == null) {
			categories = new ArrayList<EventCategory>();
		}
		return categories;
	}

	public void setCategories(List<EventCategory> categories) {
		this.categories = categories;
	}

	public List<EventCreateMembersRequest> getMembers() {
		if (members == null) {
			members = new ArrayList<EventCreateMembersRequest>();
		}
		return members;
	}

	public void setMembers(List<EventCreateMembersRequest> members) {
		this.members = members;
	}

	public EventCreateOwnerRequest getOwner() {
		return owner;
	}

	public void setOwner(EventCreateOwnerRequest owner) {
		this.owner = owner; 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public boolean isLocationShareable() {
		return locationShareable;
	}

	public void setLocationShareable(boolean locationShareable) {
		this.locationShareable = locationShareable;
	}
}
