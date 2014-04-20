package com.goparty.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.goparty.app.common.UserContext;

public class JsonHttpClient {
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final int TIMEOUT = 60000;
	//private static Map<String, String> commonRequestProperties = new HashMap<String, String>();//User-Agent;access_token
	
	public static <T> T get(String url, Class<T> valueType) throws 
		MalformedURLException, 
		IOException,
		JsonParseException,
		JsonMappingException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		
	    try {
	    	setRequestCommonProperty(conn);
	    	conn.setRequestMethod("GET");	    	
	    	int code = conn.getResponseCode();
	    	
	    	if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
	    		InputStream errorStream = conn.getErrorStream();
	    		
	    		try {
	    			String errorData = streamToString(errorStream);
	    			throw new HttpException(code, errorData);
	    		} finally {
	    			errorStream.close();
	    		}
	    	}
	    	
	    	InputStream responseStream = conn.getInputStream();
	    	try {
	    		ObjectMapper mapper = new ObjectMapper();
	    		return mapper.readValue(responseStream, valueType);
    		} finally {
    			responseStream.close();
    		}
	    } finally {
	    	if(conn!=null)
	    	  conn.disconnect();
	    }
	}
	
	/*public static String get(String url) throws MalformedURLException, IOException{
	    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    
	    try {
	    	conn.setRequestMethod("GET");
	    	//conn.setDoOutput(true);
	    	conn.setConnectTimeout(TIMEOUT);
	    	conn.setReadTimeout(TIMEOUT);
	    	
	    	int code = conn.getResponseCode();
	    	//conn.getHeaderField("chartset");
	    	
	    	if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
	    		InputStream errorStream = conn.getErrorStream();
	    		
	    		try {
	    			String errorData = streamToString(errorStream);
	    			throw new HttpException(code, errorData);
	    		} finally {
	    			errorStream.close();
	    		}
	    	}
	    	
	    	InputStream responseStream = conn.getInputStream();
	    	try {
    			String repData = streamToString(responseStream);
    			return repData;
    		} finally {
    			responseStream.close();
    		}
	    } finally {
	    	if(conn!=null)
	    	  conn.disconnect();
	    }
	    
	}*/
	
/*	public static String post(String url, byte[] content) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();		
		try {
	    		conn.setRequestMethod("POST");
	    		conn.setRequestProperty("Content-type","application/json");
	    		//conn.setDoOutput(true);
	    		conn.setConnectTimeout(TIMEOUT);
	    		conn.setReadTimeout(TIMEOUT);
	    		
	    		//conn.getOutputStream().write(content.getBytes());
	    		conn.getOutputStream().write(content);
	    		conn.getOutputStream().flush();
	    		conn.getOutputStream().close();	    
	    	
	    		int code = conn.getResponseCode();
	    		//conn.getHeaderField("chartset");
	    	
		    	if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
		    		InputStream errorStream = conn.getErrorStream();
		    		
		    		try {
		    			String errorData = streamToString(errorStream);
		    			throw new HttpException(code, errorData);
		    		} finally {
		    			errorStream.close();
		    		}
		    	}
		    	
		    	InputStream responseStream = conn.getInputStream();
		    	try {
		    			return streamToString(responseStream);
		    		} finally {
	    			responseStream.close();
	    		}
	    	} finally {
		    	if(conn!=null)
		    	  conn.disconnect();
		}
	}
	
	public static String post(String url, String content) throws IOException {
		return post(url, content.getBytes());
	}*/
	
	public static RestWsResponse post(String url, String requestBody) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		
		try {
	    		conn.setRequestMethod("POST");
	    		
	    		setRequestCommonProperty(conn);
	    		
	    		conn.getOutputStream().write(requestBody.getBytes());
	    		conn.getOutputStream().flush();
	    		conn.getOutputStream().close();	    
	    	
	    		int code = conn.getResponseCode();
	    	
		    	if (code >= HttpURLConnection.HTTP_BAD_REQUEST) {
		    		InputStream errorStream = conn.getErrorStream();
		    		
		    		try {
		    			String errorData = streamToString(errorStream);
		    			throw new HttpException(code, errorData);
		    		} finally {
		    			errorStream.close();
		    		}
		    	}
		    	
		    	InputStream responseStream = conn.getInputStream();
		    	try {
		    		ObjectMapper mapper = new ObjectMapper();
		    		
		    		return mapper.readValue(responseStream, RestWsResponse.class);
	    		} finally {
	    			responseStream.close();
	    		}
	    	} finally {
		    	if(conn!=null)
		    	  conn.disconnect();
		}
	}
		
	public static void setRequestCommonProperty(HttpURLConnection conn) {
		conn.setRequestProperty("token", UserContext.getToken());
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-type","application/json");
		
		conn.setConnectTimeout(TIMEOUT);
    	conn.setReadTimeout(TIMEOUT);
	}

	private static String streamToString(InputStream stream) throws IOException {
		StringBuffer buf = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, DEFAULT_CHARSET));
		String line;
		
		try {
			while ((line = reader.readLine()) != null) {
				buf.append(line + "\n");
				line = reader.readLine();
			}
		} finally {
				reader.close();
		}
		
		return buf.toString();
	}	
}
