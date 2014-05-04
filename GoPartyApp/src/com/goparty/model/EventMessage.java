package com.goparty.model;

import java.util.Date;

public class EventMessage {
//	"[{"id":"11",
//	"message":"hi, event 31",
//	"type":"USER_M",
//	"sender":{"id":"110","nickName":"18520861769","birthdate":1398906674000,"admin":false},
//	"publishTime":1398906674000,
//	"eventId":"31"}]"

	String id;
	String eventId;
	String message;
	String type;
	User sender;
	Date publishTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
