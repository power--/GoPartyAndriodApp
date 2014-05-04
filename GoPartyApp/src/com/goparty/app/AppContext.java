package com.goparty.app;

import com.goparty.biz.AuthService;
import com.goparty.biz.UserService;
import com.goparty.model.AuthInfo;
import com.goparty.model.User;

public class AppContext {
	
	private AuthInfo authInfo;
	
	private User currentUser;
	
	private AuthService authService = new AuthService();
	
	private AppContext()
	{
		
	}
	
	public static AppContext CurrentContext = new AppContext();
	
	public Boolean Login(AuthInfo theAuthInfo)
	{
		authService.Login(theAuthInfo.getLoginName(), theAuthInfo.getPassword());
		return true;
	}
	
	public void Logout()
	{
		authInfo = null;
		currentUser = null;
	}

	public AuthInfo getAuthInfo() {
		return authInfo;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
