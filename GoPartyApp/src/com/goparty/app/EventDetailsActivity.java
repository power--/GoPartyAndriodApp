package com.goparty.app;

import com.goparty.app.common.ActivityConst;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsActivity extends FragmentActivity {
	private FragmentManager fragmentManager;
	
	private EventDetailsMainFragment mainFragment;
	private EventDetailsChatFragment chatFragment;
	private EventDetailsShareFragment shareFragment;
	
	private String eventId;
//
//	private View mainLayout;
//	private View chatLayout;
//	private View shareLayout;
//
//	private ImageView mainImage;
//	private ImageView chatImage;
//	private ImageView shareImage;

	private TextView mainText;
	private TextView chatText;
	private TextView shareText;

//	private ImageView mainBgImage;
//	private ImageView chatBgImage;
//	private ImageView shareBgImage;
	
	Drawable navDrawable;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.event_details_layout);
		navDrawable = getResources().getDrawable(R.drawable.goparty_event_details_nav_bg);
		navDrawable.setBounds(0, 0, navDrawable.getMinimumWidth(), navDrawable.getMinimumHeight());
		
		Intent intent = getIntent();
		eventId = intent.getStringExtra(ActivityConst.INVENT_ARG_EVENT_ID);
		
		if (eventId == null || eventId.equals("")) {
			Toast.makeText(getApplicationContext(), getString(R.string.no_data), Toast.LENGTH_LONG).show();
			return;
		}
		
		initViews();
		fragmentManager = getFragmentManager();
		setTabSelection(0);
	}
	
	private void initViews() {
		NavMenuOnClickListener listener = new NavMenuOnClickListener();
		mainText = (TextView) findViewById(R.id.nav_main_text);
		mainText.setOnClickListener(listener);
		
		chatText = (TextView) findViewById(R.id.nav_chat_text);
		chatText.setOnClickListener(listener);
		
		shareText = (TextView) findViewById(R.id.nav_share_text);
		shareText.setOnClickListener(listener);
		
		Button returnBtn = (Button) findViewById(R.id.btn_nav_event_details_back);
		returnBtn.setOnClickListener(listener);
	}

	private class NavMenuOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.nav_main_text:
				setTabSelection(0);
				break;
			case R.id.nav_chat_text:
				setTabSelection(1);
				break;
			case R.id.nav_share_text:
				setTabSelection(2);
				break;
			case R.id.btn_nav_event_details_back:
				finish();
			default:
				break;
			}
		}
	}
	
	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		
		Bundle nBundle = new Bundle();  
		nBundle.putString("eventId", eventId);  
		
		switch (index) {
			case 0:
				mainText.setCompoundDrawables(null, null, null, navDrawable);
				if (mainFragment == null) {
					mainFragment = EventDetailsMainFragment.getInstance(nBundle);
					transaction.add(R.id.event_details_panel, mainFragment);
				} else {
					transaction.show(mainFragment);
				}
				break;
			case 1:
				chatText.setCompoundDrawables(null, null, null, navDrawable);
				if (chatFragment == null) {
					chatFragment = EventDetailsChatFragment.getInstance(nBundle); 
					transaction.add(R.id.event_details_panel, chatFragment);
				} else {
					transaction.show(chatFragment);
				}
				break;
			case 2:
				shareText.setCompoundDrawables(null, null, null, navDrawable);
				if (shareFragment == null) {
					shareFragment = EventDetailsShareFragment.getInstance(nBundle);
					transaction.add(R.id.event_details_panel, shareFragment);
				} else {
					transaction.show(shareFragment);
				}
				break;
		}
		
		transaction.commit();
	}

	private void clearSelection() {
		mainText.setCompoundDrawables(null, null, null, null);
		chatText.setCompoundDrawables(null, null, null, null);
		shareText.setCompoundDrawables(null, null, null, null);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mainFragment != null) {
			transaction.hide(mainFragment);
		}
		if (chatFragment != null) {
			transaction.hide(chatFragment);
		}
		if (shareFragment != null) {
			transaction.hide(shareFragment);
		}
	}
}