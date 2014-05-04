package com.goparty.app;

import com.goparty.app.common.ActivityConst;
import com.goparty.app.common.UserContext;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;
import com.goparty.model.FriendInvitation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//new UI from "message placeholder"


public class ContactDetailsActivity extends Activity {
//	private String addAction = "add";
//	private String delAction = "del";
	public enum actionType { ADD, DEL };
	public actionType currActionType;
	
	private Contact contact;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_layout);
        
        Intent intent = getIntent();
        contact = intent.getParcelableExtra(ActivityConst.INTENT_ARG_CONTACT);
        
        initView();
        currActionType = actionType.ADD;
    }
    
    private void initView() {
    	TextView nicknameTextView = (TextView)findViewById(R.id.contact_details_nickname);
    	nicknameTextView.setText(contact.getNickName());
    	
    	TextView idTextView = (TextView)findViewById(R.id.contact_details_id);
    	idTextView.setText(contact.getId());
    	
    	TextView mobileTextView = (TextView)findViewById(R.id.contact_details_mobile);
    	mobileTextView.setText("");
    	
    	Button actionBtn = (Button)findViewById(R.id.contact_details_action_button);
        actionBtn.setOnClickListener(new ButtonOnClickListener());
        
        //
        Button delBtn = (Button)findViewById(R.id.contact_details_del_friend_button);
        delBtn.setOnClickListener(new ButtonOnClickListener());
          
        Button navBackBtn = (Button)findViewById(R.id.btn_nav_contact_details_back);
        navBackBtn.setOnClickListener(new ButtonOnClickListener());
    }

	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			ContactMgmtTask mgmtTask = new ContactMgmtTask();
			switch (v.getId()) {
			case R.id.contact_details_action_button:
				switch (currActionType) {
					case ADD:
						if (contact.getId() == UserContext.getId()) {
							Toast.makeText(getApplicationContext(), getString(R.string.cant_add_yourself_as_friend), Toast.LENGTH_LONG).show();
						}
						
						mgmtTask.execute(actionType.ADD.toString(), contact.getId(), "message placeholder");
						break;
					case DEL:
						mgmtTask.execute(actionType.DEL.toString(), contact.getId());
						break;
				}
				break;
				
			//temp
				case R.id.contact_details_del_friend_button:
					mgmtTask.execute(actionType.DEL.toString(), contact.getId());
					break;

			case R.id.btn_nav_contact_details_back:
				finish();
				break;
			}
		}
	}
    
    private class ContactMgmtTask extends AsyncTask<String, Integer, String> 
    {
    	FriendInvitation addAsFriendResult;
    	boolean delFromFriendResult;
    	String actionType;
    	
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected String doInBackground(String... params) 
        {  
            try 
            {
            	actionType = params[0];
            	ContactService contactService = new ContactService();
            	if (actionType == ContactDetailsActivity.actionType.ADD.toString()) {
            		addAsFriendResult = contactService.addAsFriendRequest(params[1], params[2]);
            	} else if (actionType == ContactDetailsActivity.actionType.DEL.toString()) {
            		delFromFriendResult = contactService.deleteFromFriend(params[1]);
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
        	String resultMsg = "";
        	if (actionType == ContactDetailsActivity.actionType.ADD.toString()) {
        		if (addAsFriendResult != null && addAsFriendResult.getId() != "") {
        			resultMsg = getString(R.string.status_add_sucess);
        		} else {
        			resultMsg = getString(R.string.status_add_failure);
    			}
        	} else if (actionType == ContactDetailsActivity.actionType.DEL.toString()) {
        		if (delFromFriendResult) {
        			resultMsg = getString(R.string.status_del_sucess);
        		} else {
        			resultMsg = getString(R.string.status_del_failure);
    			}
        	}
        	
        	if (resultMsg != "") {
        		Toast.makeText(getApplicationContext(), resultMsg, Toast.LENGTH_LONG).show();
        	}
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
//    
//    private void displayResult(String result) {
//    	Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
//    }
}
