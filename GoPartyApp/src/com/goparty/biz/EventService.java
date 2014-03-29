package com.goparty.biz;

import java.util.ArrayList;
import java.util.List;

import com.goparty.model.EventCategory;

public class EventService {

	public List<EventCategory> getAllEeventCategory() {
		ArrayList<EventCategory> resultList = new ArrayList<EventCategory>();
		
		for (int i = 0; i < 8; i++) {
			EventCategory category = new EventCategory();
			category.setId(i);
			category.setName("Category" + i);
			category.setImagePath("imagePath" + i);
			resultList.add(category);
		}
		
		return resultList;
	}
}
