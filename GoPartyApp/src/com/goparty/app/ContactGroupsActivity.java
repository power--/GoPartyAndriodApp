package com.goparty.app;

import java.util.ArrayList;
import java.util.List;

import com.goparty.adapter.ContactGroupListAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.app.common.ActivityConst.OperationType;
import com.goparty.biz.ContactService;
import com.goparty.model.ContactGroup;

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

public class ContactGroupsActivity extends Activity {
	private final int CONTACT_GROUP_MGMT_REQUEST_TEXT = 0;
	
	ContactService contactService = new ContactService();
	
    ContactGroupListAdapter groupListAdapter;
	private List<ContactGroup> contactGroupDataList = new ArrayList<ContactGroup>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_groups_layout);

		ListView listView = (ListView) findViewById(R.id.contact_groups_listview);
		groupListAdapter = new ContactGroupListAdapter(this, contactGroupDataList);
		listView.setAdapter(groupListAdapter);
		listView.setOnItemClickListener(new ItemClickListener());
		
		initViews();
		
		DataQueryTask task = new DataQueryTask();
		task.execute("");
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
    	
        if (requestCode == CONTACT_GROUP_MGMT_REQUEST_TEXT) { 
            Bundle bundle = data.getExtras(); 
            String optTypeString = bundle.getString(ActivityConst.INTENT_ARG_OPERATION_TYPE);
            OperationType optType = Enum.valueOf(OperationType.class, optTypeString);
            boolean optResult = bundle.getBoolean(ActivityConst.INTENT_ARG_OPERATION_RESULT);
            ContactGroup contactGroupData = null;
            if (optResult) {
            	contactGroupData = bundle.getParcelable(ActivityConst.INTENT_ARG_CONTACT_GROUP);
            }
            
            handleResult(optType, optResult, contactGroupData);
        }
	}

	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> adapterView,
				View view,
				int position,
				long rowId) {
			goToDetailsView(contactGroupDataList.get(position));
		}
	}
	
	private void initViews() {
		TextView titleView = (TextView) findViewById(R.id.btn_common_header_title);
		titleView.setText(getString(R.string.group));
		
		ButtonOnClickListener onclickEventListener = new ButtonOnClickListener();
		
		Button btnNavBack = (Button) findViewById(R.id.btn_common_header_back);
		btnNavBack.setOnClickListener(onclickEventListener);
		
		Button btnSubmit = (Button) findViewById(R.id.btn_common_header_submit);
		btnSubmit.setVisibility(View.INVISIBLE);
		
		Button btnAdd = (Button) findViewById(R.id.contacts_List_add);
		btnAdd.setOnClickListener(onclickEventListener);
	}

	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_common_header_back:
					finish();
					break;
				case R.id.contacts_List_add:
					goToDetailsView();
					break;
					
			}
		}
	}
	
	private void goToDetailsView(ContactGroup group) {
		Intent intent = new Intent(this, ContactGroupMgmtActivity.class);
		//intent.putExtra(ActivityConst.INTENT_ARG_OPERATION_TYPE, ActivityConst.OperationType.UPDATE.toString());
		intent.putExtra(ActivityConst.INTENT_ARG_CONTACT_GROUP, group);
		
		startActivityForResult(intent, CONTACT_GROUP_MGMT_REQUEST_TEXT);
	}
	
	private void goToDetailsView() {
		Intent intent = new Intent(this, ContactGroupMgmtActivity.class);
		//intent.putExtra(ActivityConst.INTENT_ARG_OPERATION_TYPE, ActivityConst.OperationType.CREATE.toString());
		startActivityForResult(intent, CONTACT_GROUP_MGMT_REQUEST_TEXT);
	}
	
	private void handleResult(OperationType optType, boolean optResult, ContactGroup contactGroupData) {
		if (!optResult) {
			Toast.makeText(this, getString(R.string.status_operation_success), Toast.LENGTH_LONG).show();
			return;
		}
		
		switch (optType) {
			case CREATE:
				contactGroupDataList.add(contactGroupData);
				groupListAdapter.notifyDataSetChanged();
			break;
			
			case UPDATE:
				for (ContactGroup item : contactGroupDataList) {
					if (item.getId().equalsIgnoreCase(contactGroupData.getId())) {
						item.setName(contactGroupData.getName());
						groupListAdapter.notifyDataSetChanged();
					}
				}
				break;
				
			case DEL:
				int location = -1;
				for (int index = 0; index < contactGroupDataList.size(); index++) {
					if (contactGroupDataList.get(index).getId().equalsIgnoreCase(contactGroupData.getId())) {
						location = index;
					}
				}
				
				if (location > -1) {
					contactGroupDataList.remove(location);
					groupListAdapter.notifyDataSetChanged();
				}
				break;
				
			default:
				break;
		}
		
		Toast.makeText(this, getString(R.string.status_operation_success), Toast.LENGTH_LONG).show();
    }
	
	private class DataQueryTask extends AsyncTask<String, Integer, String> 
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
            	if (contactService == null) {
            		contactService = new ContactService();
            	}

            	List<ContactGroup> groups = contactService.getContactGroup();
            	contactGroupDataList.clear();
            	contactGroupDataList.addAll(groups);
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
        	groupListAdapter.notifyDataSetChanged();
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
}
