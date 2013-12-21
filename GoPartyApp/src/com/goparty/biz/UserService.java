package com.goparty.biz;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.goparty.model.User;
import com.goparty.net.JsonHttpClient;

public class UserService {
	public void Create(UUID id, String nickName, String password, String userName) {
		User user = new User(id, nickName, password, userName);
	}
	
	public void Get(UUID id) {
//		"http://localhost/cxf/rest/user/936a72da-51d0-429a-ada1-9d4a540295ff"
		String userUrl = String.format("http://localhost/cxf/rest/user/{0}", id);
		try {
			JsonHttpClient.get(userUrl, User.class);
		} catch (MalformedURLException urlEx) {
			//to-do
		} catch (JsonParseException parseEx) {		
			//to-do
		} catch (JsonMappingException mappingEx) {
			//to-do
		}catch (IOException ioEx) {
			//to-do
		}
	}			
}
