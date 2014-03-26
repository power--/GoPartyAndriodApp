package com.goparty.app;

import java.util.ArrayList;
import java.util.List;

import com.goparty.adapter.ContactListAdapter;
import com.goparty.app.R;
import com.goparty.app.common.ServerListener;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsFragment extends Fragment implements ServerListener<Contact> {
	private View loadingView;
	private boolean isEnd = false;
	private boolean isLoadingRemoved = false;
	
	ListAdapter resultAdapter = null;
	private ListView list;
	private List<Contact> contactDataList = new ArrayList<Contact>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		
		Button btnNavCreate = (Button) contactsLayout.findViewById(R.id.btn_nav_contact_create);
		btnNavCreate.setOnClickListener(new CreateOnClick());

		list = (ListView)contactsLayout.findViewById(R.id.contactsListView);
        
        resultAdapter = new ContactListAdapter(getActivity().getApplicationContext(), contactDataList);
        
        ContactService dataServ = new ContactService();
        dataServ.getContacts(ContactsFragment.this, 0, 0);
        
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.list_footer, null);
        
        list.addFooterView(loadingView);
        list.setAdapter(resultAdapter);
        list.setOnItemClickListener(mOnClickListener);
        
		return contactsLayout;		
	}
	
	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) 
		{
			Toast.makeText(getActivity(), "Hi " + position + ", Item Click Event!!", Toast.LENGTH_LONG).show();
		}
	};
	
	/*
	public class PoiResultAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public PoiResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
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
			
			convertView = mInflater.inflate(R.layout.contact_item, null);
			ContactListItemGoParty item = (ContactListItemGoParty) convertView;
			
			Contact contactData = contactDataList.get(position);
			item.setContactItemData(
					contactData.getPhoto(),
					contactData.getNickName(),
					contactData.getSignature());
			
//			
//			Map<String, Object> map = filterData.get(position);
//			item.setContactItemData(
//						map.get("promo").toString(),
//						map.get("name").toString(),
//						map.get("checkin").toString()
//					);
//		
//
//			//item.setDistanceText(map.get("distance").toString());
//			
//			if(position == filterData.size() -1 && !isEnd )
//			{
//				loadingView.setVisibility(View.VISIBLE);
//				server.sendRequest(ContactsFragment.this);
//			}
	
			return convertView;
		}
	}
*/
	@Override
	public void serverDataArrived(List<Contact> list, boolean isEnd) {
		this.isEnd = isEnd;
		
		if (list != null && list.size() > 0) { 
			for (Contact item : list) {
				contactDataList.add(item);
			}
		}
		
		Message localMessage = new Message();
		if(!isEnd) {
			localMessage.what = 1;
		}
		else
		{
			localMessage.what = 2;
		}
		
		this.handler.sendMessage(localMessage);
	}

	private class CreateOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	Intent intent = new Intent (getActivity(), ContactAddActivity.class);			
    		startActivity(intent);
        }
	}

	Handler handler = new Handler() 
	{
		public void handleMessage(Message paramMessage) 
		{
			if(paramMessage.what == 1)
			{
				loadingView.setVisibility(View.GONE);
			}
			else if(paramMessage.what == 2)
			{
				list.removeFooterView(loadingView);
				isLoadingRemoved = true;
			}
			else if(paramMessage.what == 3)
			{
				list.addFooterView(loadingView);
				loadingView.setVisibility(View.VISIBLE);
				isLoadingRemoved =false;
			}
			else if(paramMessage.what == 4)
			{
				loadingView.setVisibility(View.VISIBLE);
			}
		}
	};

}
