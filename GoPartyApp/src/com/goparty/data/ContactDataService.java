package com.goparty.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.goparty.app.common.JsonUtil;
import com.goparty.model.Contact;
import com.goparty.net.JsonHttpClient;
import com.goparty.net.RestWsResponse;

public class ContactDataService {
	public String getFriendsJsonData() {
		try {
			RestWsResponse rep = JsonHttpClient.get(WebServiceConst.FriendsListUrl, RestWsResponse.class);
			if (rep.getCode() == WebServiceRepsonseCode.SUCCESS) {
				return rep.getData();
			}
		} catch (JsonParseException e) {
			return WebServiceConst.JSON_ERROR + ": " + e.getMessage();
		} catch (JsonMappingException e) {
			return WebServiceConst.JSON_ERROR + ": " + e.getMessage();
		} catch (MalformedURLException e) {
			return WebServiceConst.HTTP_ERROR + ": " + e.getMessage();
		} catch (IOException e) {
			return WebServiceConst.ERROR + ": " + e.getMessage();
		}
		
		return WebServiceConst.ERROR;
	}
	
	public ArrayList<Contact> getFriendsData() {
		String jsonResult = getFriendsJsonData();
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new ArrayList<Contact>();
		}
//		jsonResult = "[{\"signature\":\"signature1\",\"gender\":\"gender1\",\"groups\":[{\"id\":\"id1\",\"name\":\"name1\",\"ownerId\":\"owner1\"}],\"id\":\"id1\",\"location\":\"loc1\",\"nickName\":\"nickname1\",\"photo\":\"photo1\",\"remarkName\":\"remark1\",\"birthdate\":1111}]";
		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<Contact>>(){});
	}
	
	/*public String setFrends() {
		String result = "";
		
		ArrayList<ContactGroup> groups = new ArrayList<ContactGroup>();
		
		groups.add(new ContactGroup("name1", "owner1"));
//		groups.add(new ContactGroup("id2", "name2", "owner2"));
//		groups.add(new ContactGroup("id3", "name3", "owner3"));
		
		Contact contact = new Contact(
				"id1",
				null,
				1111,
				null,
				"loc1",
				"signature1",
				"photo1",
				"remark1",
				groups);
		
		ArrayList<Contact> c = new ArrayList<Contact>();
		c.add(contact);
		
		result = JsonSerializer.serialize(c);
		
		return result;
	}*/
}
