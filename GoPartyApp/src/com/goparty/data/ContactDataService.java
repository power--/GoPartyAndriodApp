package com.goparty.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.goparty.app.common.JsonUtil;
import com.goparty.model.Contact;
import com.goparty.model.ContactGroup;
import com.goparty.model.FriendInvitResponse;
import com.goparty.model.FriendInvitation;
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
	
	public ArrayList<FriendInvitation> getFriendInvitation() {
		String jsonResult = CommonWsDataService.getJsonResult(WebServiceConst.FRIENDS_INVIT_URL);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return null;
		}
		
		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<FriendInvitation>>(){});
	}
	
	public List<Contact> search(String keyword, int take, int skip) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(String.format(WebServiceConst.USER_SEARCH_URL, keyword));
		int takeValue = take > 0 ? take : 30;
		urlBuilder.append("&");
		urlBuilder.append(WebServiceConst.URL_PARAM_LIMIT);
		urlBuilder.append("=");
		urlBuilder.append(takeValue);

		int skipValue = skip > 0 ? skip : 0;
		urlBuilder.append("&");
		urlBuilder.append(WebServiceConst.URL_PARAM_OFFSET);
		urlBuilder.append("=");
		urlBuilder.append(skipValue);

		String jsonResult = CommonWsDataService.getJsonResult(urlBuilder.toString());
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new ArrayList<Contact>();
		}

		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<Contact>>() {});
	}
	
	public FriendInvitation addAsFriendRequest(String contactId, String message) {
		String url = String.format(WebServiceConst.FRIEND_MGMT_URL, contactId);
		String jsonResult = CommonWsDataService.postAsJson(url, "message", message);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return null;
		}

		return JsonUtil.deserialize(jsonResult, FriendInvitation.class);
	}
	
	public boolean deleteFromFriendRequest(String contactId) {
		String url = String.format(WebServiceConst.FRIEND_MGMT_URL, contactId);
		return CommonWsDataService.delete(url);
	}
	
	public List<ContactGroup> getContactGroup() {
		String jsonResult =  CommonWsDataService.getJsonResult(WebServiceConst.CONTACT_GROUPS_URL);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return new ArrayList<ContactGroup>();
		}

		return JsonUtil.deserialize(jsonResult, new TypeReference<ArrayList<ContactGroup>>(){});
	}
	
	public ContactGroup createContactGroup(String name) {
		String jsonResult = CommonWsDataService.postAsJson(WebServiceConst.CONTACT_GROUPS_URL, "name", name);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return null;
		}

		return JsonUtil.deserialize(jsonResult, ContactGroup.class);
	}
	
	public ContactGroup updateContactGroup(String id, String name) {
		String url = String.format(WebServiceConst.CONTACT_GROUP_URL, id);
		String jsonResult =  CommonWsDataService.putJsonResult(url, "name", name);
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return null;
		}
		
		return JsonUtil.deserialize(jsonResult, ContactGroup.class);
	}
	
	public boolean deleteContactGroup(String id) {
		String url = String.format(WebServiceConst.CONTACT_GROUP_URL, id);
		return CommonWsDataService.delete(url);
	}
	
	public boolean acceptInvite(String inviteId, FriendInvitResponse response) {
		String url = String.format(WebServiceConst.FRIENDS_INVIT_RESPONSE_URL, inviteId);
		String jsonResult = CommonWsDataService.putJsonResult(url, response);
		
		if (jsonResult.startsWith(WebServiceConst.ERROR)) {
			return false;
		}
		
		return true;
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
