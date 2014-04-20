package com.goparty.junit;

import java.util.ArrayList;

import com.goparty.data.ContactDataService;
import com.goparty.model.Contact;

import android.test.AndroidTestCase;
import android.util.Log;

public class ContactDataServiceTests extends AndroidTestCase {
	public void getFriendsJasonTest() {
		ContactDataService serv = new ContactDataService();

		 String result = serv.getFriendsJsonData();
		 Log.i("tag", result + "");
	}
	
	public void getFriendsListTest() {
		ContactDataService serv = new ContactDataService();

		 ArrayList<Contact> result = serv.getFriendsData();
		 assertNotSame(0, result.size());
	}
	
	/*	public void setFriendsList() {
		ContactDataService serv = new ContactDataService();

		String result = serv.setFrends();
		Log.i("tag", result);
	}*/
}
