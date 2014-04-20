package com.goparty.adapter;

import java.util.List;

import com.goparty.app.R;
import com.goparty.model.Contact;
import com.goparty.widget.ContactListItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContactListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Contact> contactDataList;
	
	public int count = 0;

	public ContactListAdapter(Context context, List<Contact> contactDataList) {
		this.mInflater = LayoutInflater.from(context);
		this.contactDataList = contactDataList;
		
		if (contactDataList != null) {
			count = contactDataList.size();
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return contactDataList.size();
		return count;
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
		
		convertView = mInflater.inflate(R.layout.contact_item, null);
		ContactListItem item = (ContactListItem) convertView;
		
		Contact contactData = contactDataList.get(position);
		item.setContactItemData(
				contactData.getPhoto(),
				contactData.getNickName(),
				contactData.getSignature());
		
//		
//		Map<String, Object> map = filterData.get(position);
//		item.setContactItemData(
//					map.get("promo").toString(),
//					map.get("name").toString(),
//					map.get("checkin").toString()
//				);
//	
//
//		//item.setDistanceText(map.get("distance").toString());
//		
//		if(position == filterData.size() -1 && !isEnd )
//		{
//			loadingView.setVisibility(View.VISIBLE);
//			server.sendRequest(ContactsFragment.this);
//		}

		return convertView;
	}

}
