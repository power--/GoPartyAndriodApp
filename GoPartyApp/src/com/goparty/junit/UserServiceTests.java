package com.goparty.junit;

import com.goparty.biz.UserService;

import android.test.AndroidTestCase;
import android.util.Log;

public class UserServiceTests extends AndroidTestCase {
	 public void getCurrentProgerssTest() {
		 UserService serv = new UserService();
//		 boolean result = serv.ValidateNickName("abcabc");
//		 //Log.i("tag", result + "");
//		 assertEquals(true, result);
//		 
		 
		 String result = serv.serialize();
		 Log.i("tag", result + "");
	 }
}
