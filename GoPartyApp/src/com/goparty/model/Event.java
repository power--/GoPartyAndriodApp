package com.goparty.model;

import java.util.Date;
import java.util.List;

public class Event {
    private String id;
    private String title;
    private User owner;
    //private Location location;
    private String location;
    private Date startTime;
    private Date endTime;
    private List<EventCategory> categories;
    private List<User> members;
    private String description;
    
    //private EventStatus eventStatus;
    private String status;
    //private VisibilityCategory visiblityCategory;
    private String visibility;
    
    public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	private boolean locationShareable;
    
    
   public boolean isLocationShareable() {
		return locationShareable;
	}
	public void setLocationShareable(boolean locationShareable) {
		this.locationShareable = locationShareable;
	}
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
   public String getLocation() {
           return location;
   }
   public void setLocation(String location) {
           this.location = location;
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
   
//   @XmlElementWrapper
   public List<User> getMembers() {
           return members;
   }
   public void setAttendees(List<User> members) {
           this.members = members;
   }
   public User getOwner() {
           return owner;
   }
   public void setOwner(User owner) {
           this.owner = owner;
   }
   public String getDescription() {
           return description;
   }
   public void setDescription(String description) {
           this.description = description;
   }
   public List<EventCategory> getCategories() {
           return categories;
   }
   public void setEventCategory(List<EventCategory> categories) {
           this.categories = categories;
   }
   public String getStatus() {
           return status;
   }
   public void setStatus(String status) {
           this.status = status;
   }
//   public VisibilityCategory getVisiblityCategory() {
//           return visiblityCategory;
//   }
//   public void setVisiblityCategory(VisibilityCategory visiblityCategory) {
//           this.visiblityCategory = visiblityCategory;
//   }
   
//   @Override
//   public String toString() {
//           return "Event [id=" + id + ", title=" + title + ", location="
//                           + location + ", startTime=" + startTime + ", endTime="
//                           + endTime + ", attendees=" + attendees + ", owner=" + owner
//                           + ", description=" + description + ", eventCategory="
//                           + eventCategory + ", eventStatus=" + eventStatus
//                           + ", visiblityCategory=" + visiblityCategory + "]";
//   }
}