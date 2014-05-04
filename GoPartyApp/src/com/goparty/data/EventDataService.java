package com.goparty.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.goparty.app.common.JsonUtil;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateRequest;
import com.goparty.model.EventDetails;
import com.goparty.model.EventMessage;

public class EventDataService {
	public List<EventCategory> getEventCategories() {
		String jsonResult = CommonWsDataService.getJsonResult(WebServiceConst.EVENT_CATEGORY_URL);
		
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new ArrayList<EventCategory>();
		}
		
		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<EventCategory>>(){});
	}
	
	public boolean createEvent(EventCreateRequest requestBody) {
		String res = CommonWsDataService.postAsJson(WebServiceConst.EVENT_URL, requestBody);
		return !res.startsWith(WebServiceConst.ERROR);
	}
	
	public EventDetails getEventDetails(String eventId) {
		String url = WebServiceConst.EVENT_URL + "/" + eventId;
		String jsonResult = CommonWsDataService.getJsonResult(url);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return null;
		}
		
		return JsonUtil.deserialize(jsonResult, EventDetails.class);
	}
	
	public EventMessage postEventMessage(String eventId, String message) {
		String url = String.format(WebServiceConst.EVENT_MESSAGE_URL, eventId);
		String jsonResult = CommonWsDataService.postAsJson(url, "message", message);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new EventMessage();
		}
		
		return JsonUtil.deserialize(jsonResult, EventMessage.class);
	}
	
	public List<EventMessage> getEventMessage(String eventId) {
		String url = String.format(WebServiceConst.EVENT_MESSAGE_URL,  eventId);
		String jsonResult = CommonWsDataService.getJsonResult(url);
		
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			new ArrayList<EventMessage>();
		}
		
		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<EventMessage>>(){});
	}
}
