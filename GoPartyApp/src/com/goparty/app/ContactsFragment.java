package com.goparty.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.goparty.adapter.AreaAdapter;
import com.goparty.adapter.CateAdapter;
import com.goparty.adapter.SortAdapter;
import com.goparty.app.R;
import com.goparty.app.common.ServerListener;
import com.goparty.app.common.ServerProxy;
import com.goparty.app.common.ServerProxyMockContact;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;
import com.goparty.model.ContactItemData;
import com.goparty.widget.ContactListItem;
import com.goparty.widget.ContactListItemGoParty;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsFragment extends Fragment implements ServerListener, OnClickListener {
//public class ContactsFragment extends Fragment {
	private List<Map<String, Object>> mData;
	private List<Map<String, Object>> filterData;
	private View loadingView;
	private ListView list;
	private boolean isEnd = false;
	private ServerProxy server;
	ListAdapter areaAdapter = null;
	ListAdapter resultAdapter = null;
	CateAdapter cateAdapter = null;
	SortAdapter sortAdapter = null;
	
	private boolean isLoadingRemoved = false;
	
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.contacts_temp_layout);
        //View contactsLayout = inflater.inflate(R.layout.contacts_temp_layout, container, false);
		View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		
		Button btnNavCreate = (Button) contactsLayout.findViewById(R.id.btn_nav_contact_create);
		btnNavCreate.setOnClickListener(new CreateOnClick());
		
        mData = ContactItemData.getData();
        filterData = mData;
        
        //list = (ListView)contactsLayout.findViewById(R.id.resultlist);
        list = (ListView)contactsLayout.findViewById(R.id.contactsListView);
        
        //list.setOnItemClickListener(mOnClickListener);
        resultAdapter = new PoiResultAdapter(getActivity().getApplicationContext());
        
        server = new ServerProxy();
        server.sendRequest(ContactsFragment.this);
        
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.list_footer, null);
        
        list.addFooterView(loadingView);
        //loadingView.setVisibility(View.GONE);
        
        list.setAdapter(resultAdapter);
        list.setOnItemClickListener(mOnClickListener);
	

		return contactsLayout;		
	}
	
	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
//Rongji: important, go to details view.
			
			//			Intent intent = new Intent();
//			intent.setClass(ContactTempActivity.this, DetailActivity.class);
//			startActivity(intent);
			//ResultActivity.this.finish();
			
			//Toast.makeText(getActivity().getApplicationContext(), "Hi, Item Click Event!!", Toast.LENGTH_LONG).show();
		}
	};
	
	public class PoiResultAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public PoiResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return filterData.size();
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
			Log.v("is NULL","DF2"+position);
		}
		
		Log.v("ListViewLog","DF"+position);
		
		//convertView = mInflater.inflate(R.layout.contact_item, null);
		
//		ContactListItem item = (ContactListItem) convertView;
//
//		Map<String, Object> map = filterData.get(position);
//
//		item.setPoiData(map.get("name").toString(), map.get("price")
//				.toString(), map.get("addr").toString(), ((Integer) map
//				.get("star")).intValue(), ((Boolean) map.get("tuan"))
//				.booleanValue(), ((Boolean) map.get("card")).booleanValue(), 
//				((Boolean) map.get("promo")).booleanValue(), 
//				((Boolean) map.get("checkin")).booleanValue());
//		
		
		convertView = mInflater.inflate(R.layout.contact_item_goparty, null);
		ContactListItemGoParty item = (ContactListItemGoParty) convertView;
		
		Map<String, Object> map = filterData.get(position);
		item.setContactItemData(
					map.get("promo").toString(),
					map.get("name").toString(),
					map.get("checkin").toString()
				);
		

		//item.setDistanceText(map.get("distance").toString());
		
		if(position == filterData.size() -1 && !isEnd )
		{
			loadingView.setVisibility(View.VISIBLE);
			server.sendRequest(ContactsFragment.this);
		}

		return convertView;
	}

	}

	@Override
	public void serverDataArrived(List list, boolean isEnd) {
	this.isEnd = isEnd;
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			mData.add((Map<String, Object>) iter.next());
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

	@Override
	public void onClick(View v) 
	{
//		switch(v.getId())
//		{
//			case R.id.id_area:
//			{
//				showDialogPopup(R.id.id_area);
//				
//				break;
//			}
//			case R.id.id_type:
//			{
//				showDialogPopup(R.id.id_type);
//				break;
//			}
//			case R.id.id_sort:
//			{
//				showDialogPopup(R.id.id_sort);
//				break;
//			}
//		}
		
	}

//	protected void showDialogPopup(int viewId)
//	{
//AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
//		
//		switch(viewId)
//		{
//			case R.id.id_area:
//			{
//				
//				if(areaAdapter == null)
//				{
//					areaAdapter = new AreaAdapter(getActivity());
//				}
//				
//				localBuilder.setAdapter(areaAdapter, new areaPopupListener(areaAdapter)); 
//
//				
//				
//				break;
//			}
//			
//			case R.id.id_type:
//			{
//				if(cateAdapter == null)
//				{
//					cateAdapter = new CateAdapter(getActivity());
//				}
//				localBuilder.setAdapter(cateAdapter, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) 
//					{				
//						
//					}
//				});
//				break;
//			}
//			
//			case R.id.id_sort:
//			{
//				if(sortAdapter == null)
//				{
//					sortAdapter = new SortAdapter(getActivity());
//				}
//				localBuilder.setAdapter(sortAdapter, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) 
//					{				
//						
//					}
//				});
//				break;
//			}
//			
//		}
//		
//		AlertDialog localAlertDialog = localBuilder.create();
//		localAlertDialog.show();
//	}

//class areaPopupListener implements DialogInterface.OnClickListener
//{
//	AreaAdapter mAdapter;
//	
//	public areaPopupListener(ListAdapter adapter)
//	{
//		mAdapter = (AreaAdapter)adapter;
//	}
//	
//	@Override
//	public void onClick(DialogInterface dialog, int which) 
//	{
//		if(mAdapter.isTopLevel())
//		{
//			((AreaAdapter)mAdapter).setTypeIndex(which);
//			final String strSelect = ((AreaAdapter)mAdapter).getSelect();
//			
//			filterData = new ArrayList<Map<String, Object>>();
//			((PoiResultAdapter)resultAdapter).notifyDataSetChanged();
//			
//			if(isLoadingRemoved)
//			{
//				list.addFooterView(loadingView);
//				loadingView.setVisibility(View.VISIBLE);
//				isLoadingRemoved =false;
//			}
//			else
//			{
//				loadingView.setVisibility(View.VISIBLE);
//			}
//			
//			new Thread()
//			{
//				public void run()
//				{
//					
//					try
//					{
//						Thread.sleep(1000);
//					}
//					catch(Exception e)
//					{
//						
//					}
//
//					Iterator iter = mData.iterator();
//
//					while(iter.hasNext())
//					{
//						
//						
//						Map<String, Object> map = (Map<String, Object>)iter.next();
//						String strArea = map.get("area").toString();
//						if(strArea.equalsIgnoreCase(strSelect))
//						{
//							filterData.add((Map<String, Object>) iter.next());
//						}
//					}
//					
//					
//					Message msg = new Message();
//					msg.what = 2;
//					handler.sendMessage(msg);
//				
//				}
//			}.start();
//
//		}
//		else 
//		{
//			if(which == 0)
//			{
//				((AreaAdapter)mAdapter).setTypeIndex(which);
//				filterData.clear();
//				mData.clear();
//				filterData = mData;
//				if(isLoadingRemoved)
//				{
//					list.addFooterView(loadingView);
//					loadingView.setVisibility(View.VISIBLE);
//					isLoadingRemoved =false;
//				}
//				else
//				{
//					loadingView.setVisibility(View.VISIBLE);
//				}
//				server = new ServerProxy();
//		        server.sendRequest(ContactsFragment.this);
//			}
//		}
//		
//	}
//	
	private class CreateOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	Intent intent = new Intent (getActivity(), ContactAddActivity.class);			
    		startActivity(intent);
        }
	}
/*
	private Button sayHiButton;
	
	private List<Map<String, Object>> mData;
	private List<Map<String, Object>> filterData;
	private View loadingView;
	//private ListView list;
	private boolean isEnd = false;
//	private ServerProxy server;
	private ServerProxyMockContact server;
	ListAdapter areaAdapter = null;
	ListAdapter resultAdapter = null;
	CateAdapter cateAdapter = null;
	SortAdapter sortAdapter = null;
	
	private boolean isLoadingRemoved = false;
	
	private ListView contactsList;
	
	private ContactService contactService = new ContactService();
	private List<Contact> contactsDataList = new ArrayList<Contact>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		
		
		sayHiButton = (Button) contactsLayout.findViewById(R.id.btn_nav_contact_create);
		sayHiButton.setOnClickListener(new OnClickListener()
	    {
	        @Override
	        public void onClick(View view)
	        {
	            //Log.d("Test", "onClickListener ist gestartet");
	            //Toast.makeText(getActivity().getApplicationContext(), "Hi, Button Click Event", Toast.LENGTH_LONG).show();
	            //saveInString();

	        	Intent intent = new Intent (getActivity(), ContactTempActivity.class);			
	    		startActivity(intent);
	        }
	    });		
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.contacts_temp_layout);
        
        mData = ContactItemData.getData();
        filterData = mData;
        
        server = new ServerProxyMockContact();
        server.sendRequest(this);
        
		
		contactService.getContacts(ContactsFragment.this, 5, 0);
		
        contactsList = (ListView)contactsLayout.findViewById(R.id.contactsListView);
        //resultAdapter = new ContactItemResultAdapter(this);
        resultAdapter = new ContactItemResultAdapter(getActivity().getApplicationContext());
        list = (ListView)findViewById(R.id.resultlist);
        //list.setOnItemClickListener(mOnClickListener);
        resultAdapter = new PoiResultAdapter(this);
        
        View btnArea = findViewById(R.id.id_area);
        btnArea.setOnClickListener(this);
        
        View btnType = findViewById(R.id.id_type);
        btnType.setOnClickListener(this);
        
        View btnSort = findViewById(R.id.id_sort);
        btnSort.setOnClickListener(this);
        
        
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.list_footer, null);
        
        //contactsList.addFooterView(loadingView);
        //loadingView.setVisibility(View.GONE);
        
        contactsList.setAdapter(resultAdapter);
        
        //rongji commmented below
        contactsList.setOnItemClickListener(mOnClickListener);

        return contactsLayout;
	}
	
	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
			//Rongji: important, go to details view.
			
			//Intent intent = new Intent();
			//intent.setClass(ContactTempActivity.this, DetailActivity.class);
			//startActivity(intent);
			//ResultActivity.this.finish();
			
			Toast.makeText(getActivity().getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
		}
	};
	
	public class ContactItemResultAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public ContactItemResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			//return filterData.size();
			return contactsDataList.size();
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
				Log.v("is NULL","DF2"+position);
			}
			
			Log.v("ListViewLog","DF"+position);
			
			//convertView = mInflater.inflate(R.layout.contact_item, null);
			convertView = mInflater.inflate(R.layout.contact_item_goparty, null);
			
			ContactListItemGoParty item = (ContactListItemGoParty) convertView;
	
			Map<String, Object> map = filterData.get(position);
			item.setContactItemData(
						map.get("faceUrl").toString(),
						map.get("name").toString(),
						map.get("signature").toString()
					);
			
			
			//item.setDistanceText(map.get("distance").toString());
			
			if(position == filterData.size() -1 && !isEnd )
			{
				loadingView.setVisibility(View.VISIBLE);
				server.sendRequest(ContactsFragment.this);
			}
			
			
			//if (position < contactsDataList.size()) {
				Contact contactItem = contactsDataList.get(position);
				item.setContactItemData(
					contactItem.getFaceUrl(),
					contactItem.getName(),
					contactItem.getSignature()
				);
			//}
			
			if(position == contactsDataList.size() - 1 && !isEnd)
			{
				//loadingView.setVisibility(View.VISIBLE);
				//server.sendRequest(ContactsFragment.this);
				contactService.getContacts(ContactsFragment.this, 5, contactsDataList.size());
			}
	
			return convertView;
	}
}
	
	@Override
	public void onClick(View v) 
	{
		Toast.makeText(getActivity().getApplicationContext(), "Hi, Click Event 111", Toast.LENGTH_LONG).show();
		
		switch(v.getId())
		{
			case R.id.id_area:
			{
				showDialogPopup(R.id.id_area);
				
				break;
			}
			case R.id.id_type:
			{
				showDialogPopup(R.id.id_type);
				break;
			}
			case R.id.id_sort:
			{
				showDialogPopup(R.id.id_sort);
				break;
			}
			default:
				Toast.makeText(getActivity().getApplicationContext(), "Hi, Click Event 111", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public void serverDataArrived(List list, boolean isEnd) {
		this.isEnd = isEnd;
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			mData.add((Map<String, Object>) iter.next());
		}
		Message localMessage = new Message();
		if(!isEnd)
		{
			localMessage.what = 1;
		}
		else
		{
			localMessage.what = 2;
		}
		
		this.handler.sendMessage(localMessage);
		
		this.isEnd = isEnd;
		Iterator<Contact> iter = list.iterator();
		while (iter.hasNext()) {
			contactsDataList.add(iter.next());
		}
		Message localMessage = new Message();
		if(!isEnd)
		{
			localMessage.what = 1;
		}
		else
		{
			localMessage.what = 2;
		}
		
		this.handler.sendMessage(localMessage);
	}
	
	Handler handler = new Handler() 
	{
		public void handleMessage(Message paramMessage) 
		{
			if(paramMessage.what == 1)
			{
				//loadingView.setVisibility(View.GONE);
			}
			else if(paramMessage.what == 2)
			{
				//contactsList.removeFooterView(loadingView);
				isLoadingRemoved = true;
			}
			else if(paramMessage.what == 3)
			{
				//contactsList.addFooterView(loadingView);
				loadingView.setVisibility(View.VISIBLE);
				isLoadingRemoved =false;
			}
			else if(paramMessage.what == 4)
			{
				loadingView.setVisibility(View.VISIBLE);
			}
		}
	};
*/

}
