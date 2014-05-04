package com.goparty.model;

import java.util.Date;
import java.util.List;

public class EventDetails {
	private String id;
    private String title;
    private Date startTime;
    private Date endTime;
	private String location;
    private String description;
    private List<User> members;
    private User owner;
    private List<EventCategory> categories;
    private String status;
    private String visibility;
    private boolean locationShareable;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
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
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<EventCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<EventCategory> categories) {
		this.categories = categories;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isLocationShareable() {
		return locationShareable;
	}
	public void setLocationShareable(boolean locationShareable) {
		this.locationShareable = locationShareable;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
