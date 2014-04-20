package com.goparty.biz;

import java.util.List;

import com.goparty.data.EventDataService;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateRequest;

public class EventService {
	EventDataService eventDataService = new EventDataService();
	
	public List<EventCategory> getAllEeventCategory() {
		return eventDataService.getEventCategories();
	}
	
	public boolean createEvent(EventCreateRequest requestBody) {
		return eventDataService.createEvent(requestBody);
	}
}
