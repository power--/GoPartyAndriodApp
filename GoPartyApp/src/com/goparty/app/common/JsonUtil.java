package com.goparty.app.common;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.goparty.data.WebServiceConst;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T deserialize(String jsonifiedData, Class<T> valueType) {
		try {
			return mapper.readValue(jsonifiedData, valueType);
		} catch (JsonParseException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (IOException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} 
	}
	
	public static <T> T deserialize(String jsonifiedData, TypeReference<T> typeRef) {
		try {
			return mapper.readValue(jsonifiedData, typeRef);
		} catch (JsonParseException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		} catch (IOException e) {
			throw new RuntimeException("Deserialize from JSON failed.", e);
		}
	}
	
	public static String serialize(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			return WebServiceConst.JSON_ERROR;
		} catch (JsonMappingException e) {
			return WebServiceConst.JSON_ERROR;
		} catch (IOException e) {
			return WebServiceConst.JSON_ERROR;
		}
	}
}
