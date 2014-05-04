package com.goparty.app;

import com.goparty.app.R;

import android.R.raw;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View eventsLayout = inflater.inflate(R.layout.events_layout, container, false);
			
		Button btnNavCreate = (Button) eventsLayout.findViewById(R.id.btn_nav_event_create);
		btnNavCreate.setOnClickListener(new CreateOnClick());
		
		Button btnDetailsDummy = (Button) eventsLayout.findViewById(R.id.btn_event_details_dummy);
		btnDetailsDummy.setOnClickListener(new CreateOnClick());
		
		Button btnFriendInvite = (Button) eventsLayout.findViewById(R.id.btn_event_details_friend_invite);
		btnFriendInvite.setOnClickListener(new CreateOnClick());
		
		return eventsLayout;		
	}
	
	private class CreateOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	switch(v.getId()) {
	        	case R.id.btn_nav_event_create:
	        		Intent intent = new Intent (getActivity(), EventCreateActivity.class);			
	        		startActivity(intent);
	        		break;
	        	case R.id.btn_event_details_dummy:
	        		Intent intent2 = new Intent (getActivity(), EventDetailsActivity.class);			
	        		startActivity(intent2);
	        		break;
	        	case R.id.btn_event_details_friend_invite:
	        		Intent friendInviteIntent = new Intent(getActivity(), FriendInvitationListActivity.class);
	        		startActivity(friendInviteIntent);
        	}
        	
        }
	}
}
