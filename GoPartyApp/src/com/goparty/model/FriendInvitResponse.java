package com.goparty.model;

import java.util.Collection;

public class FriendInvitResponse {
//	{ 
//	    "acceptance": "Y", 
//	    "groups": [ 
//	    { 
//	        "id": 1, 
//	    }, 
//	    { 
//	        "id": 3, 
//	    }], 
//	    "inviteeMessage": "afad" 
//	}
	private String acceptance;
	private Collection<ContactGroup> groups;
	private String inviteeMessage;
	
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	public Collection<ContactGroup> getGroups() {
		return groups;
	}
	public void setGroups(Collection<ContactGroup> groups) {
		this.groups = groups;
	}
	public String getInviteeMessage() {
		return inviteeMessage;
	}
	public void setInviteeMessage(String inviteeMessage) {
		this.inviteeMessage = inviteeMessage;
	}
}
