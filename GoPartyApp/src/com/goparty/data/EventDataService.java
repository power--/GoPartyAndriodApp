package com.goparty.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.goparty.app.common.JsonSerializer;
import com.goparty.model.EventCategory;

public class EventDataService {
	public List<EventCategory> getEventCategories() {
		String jsonResult = CommonWsDataService.getJasonResult(WebServiceConst.eventCategoryUrl);
		
		if (jsonResult == WebServiceConst.Error) {
			return new ArrayList<EventCategory>();
		}
		
		return JsonSerializer.deserialize(jsonResult, new TypeReference<ArrayList<EventCategory>>(){});
	}
}
