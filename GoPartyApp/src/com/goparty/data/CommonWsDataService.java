package com.goparty.data;

import java.io.IOException;
import java.net.MalformedURLException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.goparty.app.common.JsonUtil;
import com.goparty.net.JsonHttpClient;
import com.goparty.net.RestWsResponse;

public class CommonWsDataService {
	public static String getJsonResult(String url) {
		try {
			RestWsResponse rep = JsonHttpClient.get(url, RestWsResponse.class);
			if (rep.getCode() == WebServiceRepsonseCode.SUCCESS) {
				return rep.getData();
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			return WebServiceConst.JSON_ERROR;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return WebServiceConst.JSON_ERROR;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return WebServiceConst.HTTP_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			return WebServiceConst.HTTP_ERROR;
		}
		
		return WebServiceConst.ERROR;
	}
	
	public static String postAsJson(String url, Object requestBody) {
		String jsonifiedPostObject = JsonUtil.serialize(requestBody);
		if (jsonifiedPostObject.startsWith(WebServiceConst.ERROR) ||
			jsonifiedPostObject.startsWith(WebServiceConst.JSON_ERROR)) {
			return WebServiceConst.JSON_ERROR;
		}
		
		RestWsResponse rep;
		try {
			rep = JsonHttpClient.post(url, jsonifiedPostObject);
			if (rep.getCode() == WebServiceRepsonseCode.SUCCESS) {
				return rep.getData();
			}
		} catch (IOException e) {
			return WebServiceConst.HTTP_ERROR;
		}
		
		return WebServiceConst.ERROR;
	}
}
