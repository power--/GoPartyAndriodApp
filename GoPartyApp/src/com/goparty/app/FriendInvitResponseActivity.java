package com.goparty.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.goparty.adapter.ContactGroupListAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.biz.ContactService;
import com.goparty.model.ContactGroup;
import com.goparty.model.FriendInvitation;

public class FriendInvitResponseActivity extends Activity {
	private FriendInvitation invite;
	private ListView contactGroupListView;
	private ArrayList<ContactGroup> contactGroupDataList = new ArrayList<ContactGroup>();
	private ContactGroupListAdapter groupListAdapter;	
	private ContactService contactService = new ContactService();
	private HashMap<String, ContactGroup> selectedGroupsMap = new HashMap<String, ContactGroup>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_invit_response_layout);
		
		Intent intent = getIntent();
        invite = intent.getParcelableExtra(ActivityConst.INTENT_ARG_FRIEND_INVITE);

        if (invite == null || invite.getInviter() == null) {
        	return;
        }
        
		initViews();
	}
	
	private void initViews() {
		//to-do: need to create a contact details info component.
		TextView name = (TextView) findViewById(R.id.contactName);
		name.setText(invite.getInviter().getNickName());
		
		TextView signature = (TextView) findViewById(R.id.contactSignature);
		signature.setText(invite.getInviterMessage());
		
		TextView titleTextView = (TextView) findViewById(R.id.btn_common_header_title);
		titleTextView.setText(getString(R.string.reply_invite));
		
		ButtonOnClickListener listener = new ButtonOnClickListener();
		Button backButton = (Button) findViewById(R.id.btn_common_header_back);
		backButton.setOnClickListener(listener);
		
		Button submitButton = (Button) findViewById(R.id.btn_common_header_submit);
		submitButton.setOnClickListener(listener);
		
		contactGroupListView = (ListView) findViewById(R.id.friend_invite_response_groups);
		groupListAdapter = new ContactGroupListAdapter(this, contactGroupDataList);
		groupListAdapter.setSelectable(true);
		contactGroupListView.setAdapter(groupListAdapter);
		contactGroupListView.setOnItemClickListener(new ItemClickListener());
		
		ContactGroupQueryTask groupQueryTask = new ContactGroupQueryTask();
		groupQueryTask.execute("");
	}
	
	private class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> adapterView,
				View view,
				int position,
				long rowId) {
			ContactGroup clickedItem = contactGroupDataList.get(position);
			
	    	  ImageView checkedIcon = (ImageView)view.findViewById(R.id.common_list_item_add);
	    	  
	    	  if (selectedGroupsMap.containsKey(clickedItem.getId())) {
	    		  	selectedGroupsMap.remove(clickedItem.getId());
					//((CommonListItem) view).setSelected(false);
	    		  	//checkedIcon.setVisibility(View.VISIBLE);
	    		  	checkedIcon.setBackgroundResource(R.drawable.goparty_v2_btn_add_bg);
				} else {
					selectedGroupsMap.put(clickedItem.getId(), clickedItem);
					//((CommonListItem) view).setSelected(true);
					checkedIcon.setBackgroundResource(R.drawable.goparty_v2_btn_checked_bg);
				}
			
			//			if (((CommonListItem) view).isSelected()) {
//				((CommonListItem) view).setSelected(false);
//			} else {
//				((CommonListItem) view).setSelected(true);
//			}
//			
//			if (selectedGroupsMap.containsKey(clickedItem.getId())) {
//				selectedGroupsMap.remove(clickedItem.getId());
//				((CommonListItem) view).setSelected(false);
//			} else {
//				selectedGroupsMap.put(clickedItem.getId(), clickedItem);
//				((CommonListItem) view).setSelected(true);
//			}
		}
	}
	
	private class ButtonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_common_header_back:
					setResult(RESULT_CANCELED);
					finish();
					break;
				case R.id.btn_common_header_submit:
					OperationTask optTask = new OperationTask();
					optTask.execute();
					break;
					
			}
		}
	}
	
	private boolean acceptInvite() {
		EditText msgText = (EditText) findViewById(R.id.friend_invite_response_msg);
		ContactService contactService = new ContactService();
		return contactService.acceptFriendInvite(invite.getInvitationId(), msgText.getText().toString(), selectedGroupsMap.values());
	}

	private class ContactGroupQueryTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				List<ContactGroup> resultList = contactService.getContactGroup();
				if (resultList != null && resultList.size() > 0) {
					contactGroupDataList.clear();
					contactGroupDataList.addAll(resultList);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return "error: " + e.getMessage();
			}

			return "ok";
		}

		@Override
		protected void onProgressUpdate(Integer... progresses) {
		}

		@Override
		protected void onPostExecute(String result) {
			groupListAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onCancelled() {
		}
	}
	
	private class OperationTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				return acceptInvite() + "";	
			} catch (Exception e) {
				e.printStackTrace();
				return "error: " + e.getMessage();
			}

			//return "ok";
		}

		@Override
		protected void onProgressUpdate(Integer... progresses) {
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onCancelled() {
		}
	}
}
