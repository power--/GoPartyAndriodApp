package com.goparty.junit;

import java.util.List;

import junit.framework.Assert;


import android.test.AndroidTestCase;

import com.goparty.biz.EventService;
import com.goparty.model.EventCategory;

public class EventServiceTests extends AndroidTestCase {
	public void testGetEventCategories() {
		EventService serv = new EventService();
		List<EventCategory> categories = serv.getAllEeventCategory();
		
		Assert.assertNotSame(0, categories.size());
	}
}
