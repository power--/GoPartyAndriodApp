package com.goparty.app;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.goparty.adapter.ContactListAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.model.Contact;

public class ContactCommonActivity extends Activity {
	ContactListAdapter listAdapter;
	private List<Contact> contactDataList;
	private String requestType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_friend_invite);

		Intent intent = getIntent();
		requestType = intent.getStringExtra(ActivityConst.INTENT_TYPE_CONTACT_SEARCH_RESULT);
		contactDataList = intent.getParcelableArrayListExtra(ActivityConst.INTENT_ARG_CONTACT_SEARCH_RESULT);

		ListView listView = (ListView) findViewById(R.id.frinedInviteListView);
		listAdapter = new ContactListAdapter(this, contactDataList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new ItemClickListener());

		LayoutOnClickListener onclickEventListener = new LayoutOnClickListener();
		Button btnNavBack = (Button) findViewById(R.id.btn_common_header_back);
		btnNavBack.setOnClickListener(onclickEventListener);

		// Toast.makeText(this, dataQueryResultString,
		// Toast.LENGTH_LONG).show();
	}

	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> adapterView,// The AdapterView
															// where the click
															// happened
				View view,// The view within the AdapterView that was clicked
				int position,// The position of the view in the adapter
				long rowId// The row id of the item that was clicked
		) {
			//LinearLayout clickedItem = (LinearLayout) view;
//			TextView textViewId = (TextView) clickedItem.findViewById(R.id.contact_details_id);
			
			goToDetailsView(contactDataList.get(position));
		}
	}

	private class LayoutOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_common_header_back:
					// setResult(RESULT_CANCELED);
					finish();
					break;
			}
		}
	}
	
	private void goToDetailsView(Contact contact) {
		Intent intent = new Intent(this, ContactDetailsActivity.class);
		intent.putExtra(ActivityConst.INTENT_ARG_CONTACT, contact);
		startActivity(intent);
	}
}
