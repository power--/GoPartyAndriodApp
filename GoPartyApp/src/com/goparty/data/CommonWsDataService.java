package com.goparty.data;

import java.io.IOException;
import java.net.MalformedURLException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.goparty.net.JsonHttpClient;
import com.goparty.net.RestWsResponse;

public class CommonWsDataService {
	public static String getJasonResult(String url) {
		try {
			RestWsResponse rep = JsonHttpClient.get(url, RestWsResponse.class);
			if (rep.getCode() == WebServiceRepsonseCode.SUCCESS) {
				return rep.getData();
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return WebServiceConst.Error;
	}
}
