package com.goparty.app.common;

public class ActivityConst {
	public final static String EVENT_CATEGORY_IDS = "eventTypeIds";
	public final static String EVENT_CONTACT_IDS = "eventContactIds";
	public final static String EVENT_DATE_IDS = "eventDates";

	public final static String INTENT_ARG_OPERATION_TYPE = "OptType";
	public final static String INTENT_ARG_OPERATION_RESULT = "OptResult";
	
	public final static String INTENT_ARG_CONTACT_SEARCH_RESULT = "ArgContactSearchResult";
	public final static String INTENT_TYPE_CONTACT_SEARCH_RESULT = "TypeContactSearchResult";

	public final static String INTENT_ARG_CONTACT = "ArgContact";
	public final static String INTENT_TYPE_CONTACT = "TypeContact";
	
	public final static String INTENT_ARG_CONTACT_GROUP = "ArgContactGroup";
	
	public final static String INTENT_ARG_FRIEND_INVITE = "ArgFriendInvite";
	
	public final static String INVENT_ARG_EVENT_ID = "ArgEventId";
	public final static String INVENT_ARG_EVENT_START_DATE = "ArgEventStartDate";
	public final static String INVENT_ARG_EVENT_END_DATE = "ArgEventEndDate";
	public final static String INVENT_ARG_EVENT_TYPE = "ArgEventType";
	public final static String INVENT_ARG_CONTACT = "ArgContact";
	
	public enum OperationType { CREATE, DEL, UPDATE }
}
