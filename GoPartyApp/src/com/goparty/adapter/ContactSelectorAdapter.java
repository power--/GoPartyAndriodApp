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
import com.goparty.widget.ContactSelectorItem;

public class ContactSelectorAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Contact> contactDataList;
	private List<String> selectdedContactIdList;

	public ContactSelectorAdapter(Context context, List<Contact> contactDataList, List<String> selectdedContactIdList) {
		this.mInflater = LayoutInflater.from(context);
		this.contactDataList = contactDataList;
		this.selectdedContactIdList = selectdedContactIdList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contactDataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
		{
			Log.v("convertView in ContactsFragment is NULL","DF2"+position);
		}
		
		convertView = mInflater.inflate(R.layout.contact_item_selector, null);
		ContactSelectorItem item = (ContactSelectorItem) convertView;
		
		Contact contactData = contactDataList.get(position);
		item.setContactItemData(
				contactData.getPhoto(),
				contactData.getNickName(),
				selectdedContactIdList.contains(contactData.getId()));
				
		return convertView;
	}

}
