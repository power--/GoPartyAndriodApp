package com.goparty.model;

import java.util.Date;
import java.util.List;

public class Event {
    private String id;
    private String title;
    private Location location;
    private Date startTime;
    private Date endTime;
    private List<User> attendees;
    private User owner;
    private String description;
    private EventCategory eventCategory;
    private EventStatus eventStatus;
    private VisibilityCategory visiblityCategory;
    
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
   public Location getLocation() {
           return location;
   }
   public void setLocation(Location location) {
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
   public List<User> getAttendees() {
           return attendees;
   }
   public void setAttendees(List<User> attendees) {
           this.attendees = attendees;
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
   public EventCategory getEventCategory() {
           return eventCategory;
   }
   public void setEventCategory(EventCategory eventCategory) {
           this.eventCategory = eventCategory;
   }
   public EventStatus getEventStatus() {
           return eventStatus;
   }
   public void setEventStatus(EventStatus eventStatus) {
           this.eventStatus = eventStatus;
   }
   public VisibilityCategory getVisiblityCategory() {
           return visiblityCategory;
   }
   public void setVisiblityCategory(VisibilityCategory visiblityCategory) {
           this.visiblityCategory = visiblityCategory;
   }
   
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