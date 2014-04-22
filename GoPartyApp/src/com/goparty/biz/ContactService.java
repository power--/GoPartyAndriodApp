package com.goparty.biz;

import java.util.ArrayList;

import com.goparty.app.common.ServerListener;
import com.goparty.data.ContactDataService;
import com.goparty.model.Contact;

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
	
	public void getContacts(final ServerListener<Contact> listener, int take, int skip) {
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
	}
	
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
