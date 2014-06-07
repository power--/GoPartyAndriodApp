package com.goparty.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FriendInvitation implements Parcelable {
	//[{"invitationId":"7",
	//"inviter":{"id":"110","nickName":"18520861769","birthdate":1398566197000},
	//"inviterMessage":"nice to meet you 109",
	//"status":"INIT",
	//"updateTime":1396836835000},
	
	//{"invitationId":"8","inviter":{"id":"110","nickName":"18520861769","birthdate":1398566197000},"inviterMessage":"nice to meet you!","status":"INIT","updateTime":1398483160000},{"invitationId":"10","inviter":{"id":"110","nickName":"18520861769","birthdate":1398566197000},"inviterMessage":"nice to meet you!","status":"INIT","updateTime":1398483912000}]
	
	//add friend request response
	//{"id":"12","inviterId":"110","inviterMessage":"message placeholder","inviteeId":"108","acceptance":"N","status":"INIT","updateTime":1398990095911}
	private String id;
	private String inviterId;
	private String inviteeId;
	private String acceptance;
	
	String invitationId;
	Contact inviter;
	String message;
	String status;
	long updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInviterId() {
		return inviterId;
	}
	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}
	public String getInviteeId() {
		return inviteeId;
	}
	public void setInviteeId(String inviteeId) {
		this.inviteeId = inviteeId;
	}
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}
	
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public Contact getInviter() {
		return inviter;
	}
	public void setInviter(Contact inviter) {
		this.inviter = inviter;
	}
	public String getInviterMessage() {
		return message;
	}
	public void setInviterMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int arg1) {
//{"invitationId":"9",
// "inviter":{"id":"109","nickName":"1302793110","birthdate":1396778879000},
// "inviterMessage":"nice to meet you!","status":"INIT","updateTime":1398483180000}]"
		dest.writeString(invitationId);
    	dest.writeParcelable(inviter, 0);//0 = parcelableFlags??
    	dest.writeString(message);
    	dest.writeLong(updateTime);
	}
	
	public static final Parcelable.Creator<FriendInvitation> CREATOR = new Creator<FriendInvitation>() {
        public FriendInvitation createFromParcel(Parcel source) {
        	ClassLoader classLoader = Contact.class.getClassLoader();
//        	ClassLoader classLoader = this.getClass().getClassLoader();
        	FriendInvitation invite = new FriendInvitation();
        	invite.invitationId = source.readString();
        	invite.inviter = source.readParcelable(classLoader);
        	invite.message = source.readString();
        	invite.updateTime = source.readLong();
        	
            return invite;
        }

        public FriendInvitation[] newArray(int size) {
            // TODO Auto-generated method stub
            return new FriendInvitation[size];
        }

    };
}
