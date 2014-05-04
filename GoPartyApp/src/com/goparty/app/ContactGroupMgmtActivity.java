package com.goparty.app;


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
import android.widget.Button;
import android.widget.EditText;


public class ContactGroupMgmtActivity extends Activity {
	ContactGroup groupData;
	ActivityConst.OperationType optType;
	ContactService contactService;
	
	EditText nameEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_group_mgmt_layout);

		extraData();
		initContactGroupViews();	
		initButtons();
	}
	
	private void extraData() {
		Intent intent = getIntent();
		//String optTypeString = intent.getStringExtra(ActivityConst.INTENT_ARG_OPERATION_TYPE);
		//optType = Enum.valueOf(ActivityConst.OperationType.class, optTypeString);
		groupData = intent.getParcelableExtra(ActivityConst.INTENT_ARG_CONTACT_GROUP);
	}

	private void initContactGroupViews() {
		nameEditText = (EditText) findViewById(R.id.contact_group_mgmt_name);
		
		if (groupData == null || groupData.getName() == "") {
			optType = OperationType.CREATE;
		} else {
			optType = OperationType.UPDATE;
			nameEditText.setText(groupData.getName());
		}
	}
	
	private void initButtons() {
		ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();
		
		Button btnDel = (Button) findViewById(R.id.contact_group_del_btn);
		if (optType == OperationType.CREATE) {
			btnDel.setVisibility(View.INVISIBLE);
		} else {
			btnDel.setOnClickListener(buttonOnClickListener);
		}
		
		Button btnBack = (Button) findViewById(R.id.btn_common_header_back);
		btnBack.setOnClickListener(buttonOnClickListener);
		
		Button btnSubmit = (Button) findViewById(R.id.btn_common_header_submit);
		btnSubmit.setOnClickListener(buttonOnClickListener);
	}
	
	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			OperationTask task = new OperationTask();
			switch (v.getId()) {
				case R.id.contact_group_del_btn:
					optType = OperationType.DEL;
					task.execute("");
					break;
				case R.id.btn_common_header_back:
					setResult(RESULT_CANCELED);
					finish();
				case R.id.btn_common_header_submit:
					task.execute("");
					break;					
	    	}
		}
	}
	
	private class OperationTask extends AsyncTask<String, Integer, String> 
    {
		boolean optResult = false;
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected String doInBackground(String... params) 
        {  
            try {
            	switch (optType) {
				case CREATE:
					optResult = createGroup();
					break;
				case UPDATE:
					optResult = updateGroup();
					break;
				case DEL:
					optResult = delGroup();
					break;
				default:
					break;
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
        	navBack(optResult);
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
	
	boolean delGroup() {
		if (contactService == null) {
			contactService = new ContactService();
		}
		
//		boolean result = contactService.deleteContactGroup(id);
//		String message = result ? String.valueOf(R.string.status_del_sucess) :String.valueOf(R.string.status_del_failure); 
//		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
		return contactService.deleteContactGroup(groupData.getId());
	}
	
	boolean createGroup() {
		if (contactService == null) {
			contactService = new ContactService();
		}
		
		groupData = contactService.createContactGroup(nameEditText.getText().toString());
		
		return groupData != null;
	}
	
	boolean updateGroup() {
		if (contactService == null) {
			contactService = new ContactService();
		}

		groupData = contactService.updateContactGroup(groupData.getId(), nameEditText.getText().toString());
		
		return groupData != null;
	}
	
	void navBack(boolean optResult) {
		Bundle bundle = new Bundle(); 
		bundle.putString(ActivityConst.INTENT_ARG_OPERATION_TYPE, optType.toString());
		bundle.putBoolean(ActivityConst.INTENT_ARG_OPERATION_RESULT, optResult);
		
		if (optResult) {
			bundle.putParcelable(ActivityConst.INTENT_ARG_CONTACT_GROUP, groupData); 
		}
		
		setResult(RESULT_OK, getIntent().putExtras(bundle));
		finish();
	}
}
