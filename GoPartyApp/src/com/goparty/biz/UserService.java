package com.goparty.biz;

/*import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.UUID;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import android.provider.ContactsContract.Data;

import com.goparty.data.WebServiceConst;
import com.goparty.model.LoginResponse;
import com.goparty.model.User;
import com.goparty.net.JsonHttpClient;
import com.goparty.net.RestWsGenericResponse;
*/
public class UserService {
	/*
	private final String getUserUrlPattern = "http://goparty.cloudapp.net/cxf/rest/user/{0}";
	private final String addUserUrlPattern = "http://goparty.cloudapp.net/cxf/rest/user";
	
	public boolean Create(String nickName, String password, String userName) {

		try {
			User user = new User();
			user.setNickName(nickName);
			user.setPassword(password);
			user.setUserName(userName);

			ObjectMapper mapper = new ObjectMapper();
			byte[] content = mapper.writeValueAsBytes(user);

			String responseContent = JsonHttpClient.post(addUserUrlPattern, content);
			
			return true;
		} catch (MalformedURLException urlEx) {
			// to-do
			return false;
		} catch (JsonParseException parseEx) {
			// to-do
			return false;
		} catch (JsonMappingException mappingEx) {
			// to-do
			return false;
		} catch (IOException ioEx) {
			// to-do
			return false;
		}
	}
	
	public void Get(UUID id) {
//		"http://localhost/cxf/rest/user/936a72da-51d0-429a-ada1-9d4a540295ff"
		String userUrl = String.format(getUserUrlPattern, id);
		try {
			JsonHttpClient.get(userUrl, User.class);
		} catch (MalformedURLException urlEx) {
			//to-do
		} catch (JsonParseException parseEx) {		
			//to-do
		} catch (JsonMappingException mappingEx) {
			//to-do
		} catch (IOException ioEx) {
			//to-do
		}
	}
	
	public boolean ValidateNickName(String nickName) {
		return nickName.length() >= 3;
	}
	
	public boolean ValidatePassword(String password) {
		return password.length() >= 3;
	}
	
	public boolean ValidateUserName(String userName) {
		return userName.length() >= 3;
	}

//	public String Login() {
//		try {
//			LoginResponse result = JsonHttpClient.get(WebServiceConst.LoginUrl, LoginResponse.class);
//			return result.getStatus();
//		} catch (MalformedURLException urlEx) {
//			//to-do
//		} catch (JsonParseException parseEx) {		
//			//to-do
//		} catch (JsonMappingException mappingEx) {
//			//to-do
//		} catch (IOException ioEx) {
//			//to-do
//		}
//		return "";
//	}
//	
	
	public static <T> T readJsonFromString(String jsonString, TypeReference<T> typeRef) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, typeRef);
		} catch (JsonParseException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (IOException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		}
	}
	
	public String serialize() {
		ObjectMapper mapper = new ObjectMapper();
		//String res = "{\"code\":200,\"status\":\"success\",\"message\":null,\"data\":{\"id\":\"109\",\"nickName\":\"1302793110\",\"birthdate\":1396778879000}}";
		String res = "{\"code\":200,\"status\":\"success\",\"message\":null,\"data\":\"errorerrorerrorerror\"}";
		//Type type = new TypeReference<RestWsResponse<LoginResponse>>(){}.getType();
		//return mapper.readValue(res, type.);
		RestWsGenericResponse<LoginResponse> a = readJsonFromString(res, new TypeReference<RestWsGenericResponse<LoginResponse>>(){});
		return a.getData().getId();
	}
	
//	public RestWsResponse<T> read(String json, Class<T> contentClass) { 
//		ObjectMapper mapper = new ObjectMapper();
//		JavaType type = mapper.getTypeFactory().constructParametricType(Data.class, T.class); 
//		return mapper.readValue(json, type); 
//	} 
*/}
