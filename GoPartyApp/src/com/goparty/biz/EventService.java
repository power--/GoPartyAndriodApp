package com.goparty.biz;

import java.util.List;

import com.goparty.data.EventDataService;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateRequest;
import com.goparty.model.EventDetails;
import com.goparty.model.EventMessage;

public class EventService {
	EventDataService eventDataService = new EventDataService();
	
	public List<EventCategory> getAllEeventCategory() {
		return eventDataService.getEventCategories();
	}
	
	public boolean createEvent(EventCreateRequest requestBody) {
		return eventDataService.createEvent(requestBody);
	}
	
	public EventDetails getEventDetails(String eventId) {
		return eventDataService.getEventDetails(eventId);
	}
	
	public EventMessage postEventMessage(String eventId, String message) {
		return eventDataService.postEventMessage(eventId, message);
	}
	
	public List<EventMessage> getMessages(String eventId) {
		return eventDataService.getEventMessage(eventId);
	}
}
