package com.goparty.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.goparty.app.R;
import com.goparty.biz.ContactService;
import com.goparty.model.ContactGroup;
import com.goparty.widget.CommonListItem;

public class ContactGroupListAdapter extends BaseAdapter {
	private boolean isSelectable;
	private LayoutInflater mInflater;
	private List<ContactGroup> contactGroupDataList;
	ContactService contactService;
	
	public ContactGroupListAdapter(Context context, List<ContactGroup> itemList) {
		this.mInflater = LayoutInflater.from(context);
		contactGroupDataList = itemList;
	}
	
	@Override
	public int getCount() {
		return contactGroupDataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			Log.v("convertView in ContactsFragment is NULL","DF2"+position);
		}
		
//		convertView = mInflater.inflate(R.layout.contact_group_list_item, null);
//		TextView groupName = (TextView) convertView.findViewById(R.id.contactGroupName);
//		ContactGroup group = contactGroupDataList.get(position); 
//		groupName.setText(group.getName());

		convertView = mInflater.inflate(R.layout.common_list_item, null);
		CommonListItem listItem = (CommonListItem) convertView;
		listItem.setSelectable(isSelectable);
		
		ContactGroup group = contactGroupDataList.get(position);
		listItem.setContactItemData("", group.getName(), "");

		return convertView;
	}
	
	public List<ContactGroup> getDataList() {
		return contactGroupDataList;
	}

	public boolean isSelectable() {
		return isSelectable;
	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}
}
