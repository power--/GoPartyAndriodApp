package com.goparty.data;

public class WebServiceConst {
	public static final String HTTP_ERROR = "error_http";
	public static final String JSON_ERROR = "error_json";
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	
	
	public static final String LoginUrl = "http://goparty.cloudapp.net/cxf/rest/login";
	public static final String EventListUrl = "/myevents?after=<timestamp>&before=<timestamp>&admins=<userId|userId|...userId>&categories=<categoryId|categoryId|...categoryId>&search=<key|key...|key>&offset=<offset>&limits=<limits>";
	public static final String ContactListUrl = "/users?search=<key|key...|key>&offset=<offset>&limits=<limits>";
	
	public static final String FriendsListUrl = "http://goparty.cloudapp.net/cxf/rest/friends?offset=0&limit=5";
	public static final String EVENT_CATEGORY_URL = "http://goparty.cloudapp.net/cxf/rest/eventcategories";
	public static final String EVENT_CREATE_URL = "http://goparty.cloudapp.net/cxf/rest/events";
}

