package com.goparty.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.goparty.app.R;
import com.goparty.model.Contact;
import com.goparty.model.FriendInvitation;
import com.goparty.widget.ContactListItem;

public class FriendInvitationListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<FriendInvitation> dataList;

	public FriendInvitationListAdapter(Context context,
			List<FriendInvitation> dataList) {
		this.mInflater = LayoutInflater.from(context);
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		if (dataList == null) {
			return 0;
		}
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			Log.v("convertView in ContactsFragment is NULL", "DF2" + position);
		}

		convertView = mInflater.inflate(R.layout.contact_item, null);
		ContactListItem item = (ContactListItem) convertView;

		FriendInvitation invitationData = dataList.get(position);
		Contact itemData = invitationData.getInviter();
		item.setContactItemData(itemData.getPhoto(), itemData.getNickName(),
				invitationData.getInviterMessage(), invitationData.getStatus());

		return convertView;
	}
}
