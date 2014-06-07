package com.goparty.data;

public class WebServiceConst {
	public static final String HTTP_ERROR = "error_http";
	public static final String JSON_ERROR = "error_json";
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	

	public static final String URL_PARAM_OFFSET = "offset";
    public static final String URL_PARAM_LIMIT = "limit";
    	
//	public static final String LoginUrl = "http://goparty.cloudapp.net/cxf/rest/login";
//	
//	public static final String ContactListUrl = "/users?search=<key|key...|key>&offset=<offset>&limits=<limits>";
	
	public static final String FriendsListUrl = "http://goparty.cloudapp.net/cxf/rest/friends";//?offset=0&limit=5
	public static final String FRIENDS_INVIT_URL = "http://goparty.cloudapp.net/cxf/rest/friends/unrespondedInvitations";
	public static final String FRIENDS_INVIT_RESPONSE_URL = "http://goparty.cloudapp.net/cxf/rest/friends/unrespondedInvitations/%s";
	public static final String FRIEND_MGMT_URL = "http://goparty.cloudapp.net/cxf/rest/friends/%s";
	public static final String USER_SEARCH_URL = "http://goparty.cloudapp.net/cxf/rest/users?search=%s";
	public static final String CONTACT_GROUPS_URL = "http://goparty.cloudapp.net/cxf/rest/friends/groups";
	public static final String CONTACT_GROUP_URL = "http://goparty.cloudapp.net/cxf/rest/friends/groups/%s";
	
	public static final String EventListUrl = "/myevents?after=<timestamp>&before=<timestamp>&admins=<userId|userId|...userId>&categories=<categoryId|categoryId|...categoryId>&search=<key|key...|key>&offset=<offset>&limits=<limits>";
	public static final String EVENT_URL = "http://goparty.cloudapp.net/cxf/rest/events";
	public static final String EVENT_URL_FORMAT = "http://goparty.cloudapp.net/cxf/rest/events?offst=%s&limit=%s";
	public static final String EVENT_CATEGORY_URL = "http://goparty.cloudapp.net/cxf/rest/eventcategories";
	public static final String EVENT_MESSAGE_URL = "http://goparty.cloudapp.net/cxf/rest/events/%s/messages";
	public static final String EVENT_INVITE_URL = "http://goparty.cloudapp.net/cxf/rest/events/unrespondedInvitations?offset=%s&limits=%s";
	public static final String EVENT_INVIT_RESPONSE_URL = "http://goparty.cloudapp.net/cxf/rest/events/unrespondedInvitations/%s";
}

