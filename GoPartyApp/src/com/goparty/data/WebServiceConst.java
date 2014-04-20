package com.goparty.data;

public class WebServiceConst {
	public static final String Error = "error";
	
	public static final String LoginUrl = "http://goparty.cloudapp.net/cxf/rest/login";
	public static final String EventListUrl = "/myevents?after=<timestamp>&before=<timestamp>&admins=<userId|userId|...userId>&categories=<categoryId|categoryId|...categoryId>&search=<key|key...|key>&offset=<offset>&limits=<limits>";
	public static final String ContactListUrl = "/users?search=<key|key...|key>&offset=<offset>&limits=<limits>";
	
	public static final String FriendsListUrl = "http://goparty.cloudapp.net/cxf/rest/friends?offset=0&limit=5";
	public static final String eventCategoryUrl = "http://goparty.cloudapp.net/cxf/rest/eventcategories";
}

