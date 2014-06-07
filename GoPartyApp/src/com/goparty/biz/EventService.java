package com.goparty.biz;

import java.util.List;

import com.goparty.data.EventDataService;
import com.goparty.model.Event;
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
	
	public List<Event> getMyEvents() {
		return getMyEvents(1000, 0);
	}
	
	public List<Event> getMyEvents(int take, int skip) {
		return eventDataService.getMyEvents(take, skip);
	}
	
//	public List<EventInvite> getUnresponsedInvite(int skip, int take) {
//		
//	}
}
