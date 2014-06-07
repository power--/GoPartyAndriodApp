package com.goparty.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.goparty.data.ContactDataService;
import com.goparty.model.Contact;
import com.goparty.model.ContactGroup;
import com.goparty.model.FriendInvitResponse;
import com.goparty.model.FriendInvitation;

public class ContactService {
//	private List<Contact> mockedContactsList;
	ContactDataService dataService;
	
	public ContactService() {
		//mockData();
		dataService = new ContactDataService();
	}
	
	//public ArrayList<Contact> getContacts(int take, int skip) {
	public ArrayList<Contact> getContacts() {
		return dataService.getFriendsData();
	}
	
	public List<FriendInvitation> getInvits() {
//		[{"invitationId":"9","inviter":{"id":"109","nickName":"1302793110","birthdate":1396778879000},"inviterMessage":"nice to meet you!","status":"INIT","updateTime":1398483180000}]
//		return null;
		return dataService.getFriendInvitation();
	}
	
	public List<Contact> search(String keyword, int take, int skip) {
		return dataService.search(keyword, take, skip);
	}
	
	public FriendInvitation addAsFriendRequest(String contactId, String message) {
		return dataService.addAsFriendRequest(contactId, message);
	}
	
	public boolean deleteFromFriend(String contactId) {
		return dataService.deleteFromFriendRequest(contactId);
	}
	
	public List<ContactGroup> getContactGroup() {
		return dataService.getContactGroup();
	}
	
	public ContactGroup createContactGroup(String name) {
		return dataService.createContactGroup(name);
	}
	
	public ContactGroup updateContactGroup(String id, String groupName) {
		return dataService.updateContactGroup(id, groupName);
	}
	
	public boolean deleteContactGroup(String groupId) {
		return dataService.deleteContactGroup(groupId);
	}
	
	public boolean acceptFriendInvite(String inviteId, String message, Collection<ContactGroup> groups) {
		FriendInvitResponse response = new FriendInvitResponse();
		response.setAcceptance("Y");
		response.setMessage(message);
		response.setGroups(groups);
		
		return dataService.acceptInvite(inviteId, response);
	}
	
//	public void getContacts(final ServerListener<Contact> listener, int take, int skip) {
		//ArrayList<Contact> contacts = new ArrayList<>();
		
		//listener.serverDataArrived(dataService.getFriendsData(), true);
		
//		try {
//			Thread.sleep(1000);
//		} catch (Exception ex) {
//			
//		}
//		
//		List<Contact> resultList = new ArrayList<Contact>();
//		
//		if (take < 0 || skip > mockedContactsList.size() - 1) {
//			listener.serverDataArrived(resultList, true);
//			//return resultList;
//		}
//		
//		for (int i = 0; i < take; i++) {
//			if ((i + skip) >= mockedContactsList.size()) {
//				break;
//			}
//			
//			resultList.add(mockedContactsList.get(i + skip));
//		}
//		
//		listener.serverDataArrived(resultList, false);
//		
//	}
	
	/*private void mockData() {
		mockedContactsList = new ArrayList<Contact>();
		for (int i = 0; i < 50; i++) {
			Contact contactItem = new Contact(
					"id" + i,
					"nickName" + i,
					10000000 + i * 100000,
					"M",
					"location" + i,
					"signature" + i,
					"photo" + i,
					"remarkName" + i,
					null);
			contactItem.setId(i + "");
			mockedContactsList.add(contactItem);
		}
	}*/
}
