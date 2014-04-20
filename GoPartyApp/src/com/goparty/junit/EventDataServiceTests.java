package com.goparty.junit;

import java.util.Date;

import com.goparty.app.common.JsonUtil;
import com.goparty.data.EventDataService;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateRequest;
import com.goparty.model.EventCreateMembersRequest;
import com.goparty.model.EventCreateOwnerRequest;

import android.test.AndroidTestCase;

public class EventDataServiceTests extends AndroidTestCase {
	public void createEventTest() {
		EventCreateRequest requestBody = new EventCreateRequest();
		
/*		"title":"Integration test1",
		"startTime":"2014-04-20T01:41:01.540Z",
		"endTime":"2014-04-20T01:41:01.540Z",
		"location":"Baoan",
		"description":"test events api by code ",
		"categories":[{"id":2}],
		"members":[{"id":"109", "admin": false},{"id":"110", "admin": true}],
		"owner":{"id":110},
		"status":"INIT",
		"visibility":"V_PUBLIC",
		"locationShareable":false*/

		requestBody.setTitle("Integration test4");
//		requestBody.setStartTime(new Date());
//		requestBody.setEndTime(new Date());
		requestBody.setStartTime(1397993660572L);
		requestBody.setEndTime(1397993660572L);
		requestBody.setLocation("Xixiang");
		requestBody.setDescription("trigger by UT");
		
		EventCategory cate = new EventCategory();
		cate.setId(1);
		requestBody.getCategories().add(cate);
		
		EventCreateMembersRequest mem1 = new EventCreateMembersRequest();
		mem1.setId(109);
		mem1.setAdmin(false);
		requestBody.getMembers().add(mem1);
		
		EventCreateMembersRequest mem2 = new EventCreateMembersRequest();
		mem2.setId(110);
		mem2.setAdmin(true);
		requestBody.getMembers().add(mem2);
		
		EventCreateOwnerRequest owner = new EventCreateOwnerRequest();
		owner.setId(110);
		requestBody.setOwner(owner);
		
		requestBody.setStatus("INIT");
		requestBody.setVisibility("V_PUBLIC");
		requestBody.setLocationShareable(true);
		
		EventDataService serv = new EventDataService();
		
		String temp = JsonUtil.serialize(requestBody);

		boolean result = serv.createEvent(requestBody);
		assertSame(true, result);
	}
}
