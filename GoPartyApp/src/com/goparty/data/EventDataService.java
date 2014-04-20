package com.goparty.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.goparty.app.common.JsonUtil;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateRequest;

public class EventDataService {
	public List<EventCategory> getEventCategories() {
		String jsonResult = CommonWsDataService.getJsonResult(WebServiceConst.EVENT_CATEGORY_URL);
		
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new ArrayList<EventCategory>();
		}
		
		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<EventCategory>>(){});
	}
	
	public boolean createEvent(EventCreateRequest requestBody) {
		String res = CommonWsDataService.postAsJson(WebServiceConst.EVENT_CREATE_URL, requestBody);
		return !res.startsWith(WebServiceConst.ERROR);
	}
}
