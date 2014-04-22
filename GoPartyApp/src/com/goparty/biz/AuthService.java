package com.goparty.biz;

import java.io.IOException;
import java.net.MalformedURLException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.goparty.model.AuthInfo;
import com.goparty.model.User;
import com.goparty.net.JsonHttpClient;

public class AuthService {
	private final String loginUrl = "http://goparty.cloudapp.net/cxf/rest/login";
	private final String logoutUrl = "http://goparty.cloudapp.net/cxf/rest/logout";
	
	public AuthInfo Login(String theUserName, String thePassword) {

		AuthInfo aResult = new AuthInfo();
		aResult.setLoginName(theUserName);
		aResult.setPassword(thePassword);
		
		try {
			
			String aRequestContent = "{\"mobile\":\"137985855637\"}";
			String responseContent = JsonHttpClient.post(loginUrl, aRequestContent);
			
			return aResult;
		} catch (MalformedURLException urlEx) {
			// to-do
			return null;
		} catch (JsonParseException parseEx) {
			// to-do
			return null;
		} catch (JsonMappingException mappingEx) {
			// to-do
			return null;
		} catch (IOException ioEx) {
			// to-do
			return null;
		}
	}
}
