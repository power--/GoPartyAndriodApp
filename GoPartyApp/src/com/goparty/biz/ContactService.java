package com.goparty.biz;

import java.util.ArrayList;
import java.util.List;

import com.goparty.app.common.ServerListener;
import com.goparty.model.Contact;

public class ContactService {
	private List<Contact> mockedContactsList;
	
	public ContactService() {
		mockedContactsList = new ArrayList<Contact>();
		for (int i = 0; i < 50; i++) {
			mockedContactsList.add(new Contact(i + ".png", "name " + i, "signature" + i));
		}
	}
	
	public void getContacts(final ServerListener listener, int take, int skip) {
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {
			
		}
		
		List<Contact> resultList = new ArrayList<Contact>();
		
		if (take < 0 || skip > mockedContactsList.size() - 1) {
			listener.serverDataArrived(resultList, true);
			//return resultList;
		}
		
		for (int i = 0; i < take; i++) {
			if ((i + skip) >= mockedContactsList.size()) {
				break;
			}
			
			resultList.add(mockedContactsList.get(i + skip));
		}
		
		listener.serverDataArrived(resultList, false);
		
	}
}
