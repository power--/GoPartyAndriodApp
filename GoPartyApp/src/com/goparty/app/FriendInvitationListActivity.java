package com.goparty.app;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.goparty.adapter.FriendInvitationListAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.biz.ContactService;
import com.goparty.model.FriendInvitation;

public class FriendInvitationListActivity extends Activity {
		private final int FRIEND_INVITE_REQUEST_TEXT = 0;
		private FriendInvitationListAdapter listAdapter;
		private ArrayList<FriendInvitation> friendInviteDataList = new ArrayList<FriendInvitation>();
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.contact_friend_invite);

	        ListView listView = (ListView) findViewById(R.id.frinedInviteListView);  
	        listAdapter = new FriendInvitationListAdapter(this, friendInviteDataList);
	        listView.setAdapter(listAdapter);
	        listView.setOnItemClickListener(new ItemClickListener());
	  
	        LayoutOnClickListener onclickEventListener = new LayoutOnClickListener();
			Button btnNavBack = (Button) findViewById(R.id.btn_common_header_back);
			btnNavBack.setOnClickListener(onclickEventListener);

			Button btnSubmitBack = (Button) findViewById(R.id.btn_common_header_submit);
			btnSubmitBack.setVisibility(View.INVISIBLE);

			TextView titleView = (TextView)findViewById(R.id.btn_common_header_title);
			titleView.setText(getString(R.string.new_contact));
			
	        DataQuery dataQuery = new DataQuery();
	        String dataQueryResultString = "";
	        try {
	        	dataQueryResultString = dataQuery.execute("").get();
			} catch (InterruptedException e) {
				dataQueryResultString = e.getMessage();
			} catch (ExecutionException e) {
				dataQueryResultString = e.getMessage();
			}
	        
	        if (dataQueryResultString.startsWith("ok")) {
	        	 if (friendInviteDataList.size() == 0) {
	        		 Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_LONG).show();
	        	 }
	        } else {
	        	Toast.makeText(this, dataQueryResultString, Toast.LENGTH_LONG).show();
	        }
	    }
	    
	    @Override 
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == RESULT_CANCELED) {
	    		return;
	    	}
	    	
	    	if (resultCode != RESULT_OK) {
	    		String msg = String.format("%s; %s;", getString(R.string.activity_intent_commu_not_ok), resultCode);
	    		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	    		return;
	    	}
	    	
	        if (requestCode == FRIEND_INVITE_REQUEST_TEXT) { 
//	            Bundle bundle = data.getExtras(); 
//	            String optTypeString = bundle.getString(ActivityConst.INTENT_ARG_OPERATION_TYPE);
//	            OperationType optType = Enum.valueOf(OperationType.class, optTypeString);
//	            boolean optResult = bundle.getBoolean(ActivityConst.INTENT_ARG_OPERATION_RESULT);
//	            ContactGroup contactGroupData = null;
//	            if (optResult) {
//	            	contactGroupData = bundle.getParcelable(ActivityConst.INTENT_ARG_CONTACT_GROUP);
//	            }
//	            
//	            handleResult(optType, optResult, contactGroupData);
	        	
	        	Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
	        }
		}
	      
	    private class  ItemClickListener implements OnItemClickListener  
	    {  
	    	public void onItemClick(AdapterView<?> adapterView, 
	                View view,  
	                int position,  
	                long rowId) {  
	    		navToResponseView(friendInviteDataList.get(position));
	    	}
	    }
	    	    
	    private class LayoutOnClickListener implements OnClickListener {
	    	@Override
	    	public void onClick(View v) {
	    		switch (v.getId()) {
	    			/*case R.id.btn_event_type_submit:
	    				Bundle bundle = new Bundle(); 
	    		        bundle.putStringArrayList(ActivityConst.EVENT_CATEGORY_IDS, selectedCategoryIdList); 
	    		         
	    		        setResult(RESULT_OK, getIntent().putExtras(bundle));
	    		        finish();
	    				break;
	    				*/
	    			case R.id.btn_common_header_back:
	    				//setResult(RESULT_CANCELED);
	    				finish();
	    				break;
	    		}
	    		
	    	}
	    }
	    
	    private void navToResponseView(FriendInvitation invite) {
	    	Intent intent = new Intent(this, FriendInvitResponseActivity.class);
	        intent.putExtra(ActivityConst.INTENT_ARG_FRIEND_INVITE, invite);
	        startActivityForResult(intent, FRIEND_INVITE_REQUEST_TEXT);
	    }
	    
	    private class DataQuery extends AsyncTask<String, Integer, String> 
	    {
	        @Override  
	        protected void onPreExecute() 
	        {  
	        }  
	          
	        @Override  
	        protected String doInBackground(String... params) 
	        {  
	            try 
	            {
	            	ContactService contactService = new ContactService();
	            	ArrayList<FriendInvitation> friendInvits = (ArrayList<FriendInvitation>)contactService.getInvits();
	            	if (friendInvits == null) {
	            		return "error";
	            	} else if (friendInvits.size() > 0) {
	            		friendInviteDataList.clear();
	            		friendInviteDataList.addAll(friendInvits);
	        		}
	            } 
	            catch (Exception e)
	            {
	                e.printStackTrace();
	                return "error: " + e.getMessage();
	            }
	            
	            return "ok";
	        }  
	          
	        @Override  
	        protected void onProgressUpdate(Integer... progresses) 
	        {  
	        }  
	          
	        @Override  
	        protected void onPostExecute(String result) 
	        {  
	        	//simpleAdapter.count += contactDataList.size();
	        	listAdapter.notifyDataSetChanged();
	        }  
	          
	        @Override  
	        protected void onCancelled() 
	        {  
	        }  
	    }
}