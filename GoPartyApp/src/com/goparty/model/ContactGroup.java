package com.goparty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactGroup implements Parcelable {
	private String id;
	private String name;
	private String ownerId;
	
	public ContactGroup() {
		super();
	}

	public ContactGroup(
			String name,
			String ownerId) {
		this.name = name;
		this.ownerId = ownerId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
        dest.writeString(id);
    	dest.writeString(name);
    	dest.writeString(ownerId);
	}
	
public static final Parcelable.Creator<ContactGroup> CREATOR = new Creator<ContactGroup>() {
        public ContactGroup createFromParcel(Parcel source) {
        	ContactGroup group = new ContactGroup();
        	group.id = source.readString();
        	group.name = source.readString();
        	group.ownerId = source.readString();

            return group;
        }

        public ContactGroup[] newArray(int size) {
            return new ContactGroup[size];
        }

    };
}
