package com.goparty.app.common;

public class WebServiceConst {
	public static final String LoginUrl = "http://goparty.cloudapp.net/cxf/rest/login";
	public final String EventListUrl = "/myevents?after=<timestamp>&before=<timestamp>&admins=<userId|userId|...userId>&categories=<categoryId|categoryId|...categoryId>&search=<key|key...|key>&offset=<offset>&limits=<limits>";
	public final String ContactListUrl = "/users?search=<key|key...|key>&offset=<offset>&limits=<limits>";
}

