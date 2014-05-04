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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ContactsFragment extends Fragment implements ServerListener<Contact> {
	private View loadingView;
	private boolean isEnd = false;
	private boolean isLoadingRemoved = false;
	
	ContactListAdapter resultAdapter = null;
	private ListView list;
	private List<Contact> contactDataList = new ArrayList<Contact>();
	
	private ContactService contactService = new ContactService();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
		initViews(contactsLayout);
				
		list = (ListView)contactsLayout.findViewById(R.id.contactsListView);
        resultAdapter = new ContactListAdapter(getActivity().getApplicationContext(), contactDataList);
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.list_footer, null);        
        list.setAdapter(resultAdapter);
        list.setOnItemClickListener(mOnClickListener);
        
        DataQuery dataQuery = new DataQuery();
        dataQuery.execute("");
        
		return contactsLayout;		
	}
	
	private void initViews(View parentLayout) {
		Button btnNavCreate = (Button) parentLayout.findViewById(R.id.btn_nav_contact_create);
		btnNavCreate.setOnClickListener(new ViewOnClick());
		
		View layoutGroupMgmt = parentLayout.findViewById(R.id.layout_group_mgmt);
		layoutGroupMgmt.setOnClickListener(new ViewOnClick());

		View layoutFriendInvite = parentLayout.findViewById(R.id.layout_new_friend);
		layoutFriendInvite.setOnClickListener(new ViewOnClick());

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

	private class ViewOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	switch (v.getId()) {
			case R.id.btn_nav_contact_create:
				Intent intent = new Intent (getActivity(), ContactAddActivity.class);			
				startActivity(intent);
				break;
			case R.id.layout_new_friend:
				//to-do: start for result and update contact list after accept;
				Intent intentInvite = new Intent (getActivity(), FriendInvitationListActivity.class);			
				startActivity(intentInvite);
				break;
			case R.id.layout_group_mgmt:
				Intent intentGroup = new Intent (getActivity(), ContactGroupsActivity.class);			
				startActivity(intentGroup);
				break;
			}
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
	
	private class DataQuery extends AsyncTask<String, Integer, String> 
    {
        //onPreExecute方法在execute()后执行
        @Override  
        protected void onPreExecute() 
        {  
            //Log.i(TAG, "onPreExecute() enter");  
            //mShowLogTextView.setText("onPreExecute。。。begin downLoad");  
        }  
          
        //doInBackground方法内部执行后台任务,不能在里面更新UI，否则有异常。
        @Override  
        protected String doInBackground(String... params) 
        {  
            //Log.i(TAG, "doInBackground(String... params) enter");  
            
        	
//            URL imageUrl=null;
            try 
            {
//                imageUrl=new URL(params[0]);
            	ArrayList<Contact> arrayList = contactService.getContacts();
            	if (arrayList != null && arrayList.size() > 0) { 
        			for (Contact item : arrayList) {
        				contactDataList.add(item);
        			}
        		}
        		
            	contactDataList = arrayList;
            	
            } 
//            catch (MalformedURLException e)
            catch (Exception e)
            {
                e.printStackTrace();
//                Log.e(TAG, e.getMessage());
            }
//            try
//            {
//                //使用HttpURLConnection打开连接
//                HttpURLConnection urlConn=(HttpURLConnection)imageUrl.openConnection();
//                urlConn.setDoInput(true);
//                urlConn.connect();
//                //将得到的数据转化成InputStream
//                InputStream is=urlConn.getInputStream();
//                //将InputStream转换成Bitmap
//                mDownLoadBtBitmap=BitmapFactory.decodeStream(is);
//                is.close();
//                //不能在这里更新UI,否则有异常******
//                //mNetImageView.setImageBitmap(bitmap);
//            }catch(IOException e)
//            {
//                Log.e(TAG,e.getMessage());
//            }
            
            return "ok";
        }  
          
        //onProgressUpdate方法用于更新进度信息  
        @Override  
        protected void onProgressUpdate(Integer... progresses) 
        {  
//            Log.i(TAG, "onProgressUpdate(Integer... progresses) enter");  
// 
//            mShowLogTextView.setText("onProgressUpdate Downloading...");  
        }  
          
        //onPostExecute用于doInBackground执行完后，更新界面UI。
        //result是doInBackground返回的结果
        @Override  
        protected void onPostExecute(String result) 
        {  
//            Log.i(TAG, "onPostExecute(Result result) called");  
//            mShowLogTextView.setText("Down load finish result="+result);  
//              
//            mNetImageView.setImageBitmap(mDownLoadBtBitmap);
        	resultAdapter.count += contactDataList.size();
        	resultAdapter.notifyDataSetChanged();
        }  
          
        //onCancelled方法用于取消Task执行，更新UI
        @Override  
        protected void onCancelled() 
        {  
//            Log.i(TAG, "onCancelled() called");  
//            mShowLogTextView.setText("onCancelled");  
        }  
    }
}
