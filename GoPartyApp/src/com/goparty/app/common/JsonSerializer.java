package com.goparty.app.common;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonSerializer {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
