package com.goparty.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class EventDetailsShareFragment  extends Fragment {
	private String eventId;
	
	public static EventDetailsShareFragment getInstance(Bundle bundle) {  
		EventDetailsShareFragment eventDetailsShareFragment = new EventDetailsShareFragment();  
		eventDetailsShareFragment.setArguments(bundle);  
	    return eventDetailsShareFragment;  
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle argus = getArguments();
		eventId = argus.getString("eventId");
		
		if (eventId == null || eventId == "") {
			Toast.makeText(getActivity(), "Event is empty.", Toast.LENGTH_LONG).show();
			return inflater.inflate(R.layout.event_share_item, container, false);
		}
		
		return inflater.inflate(R.layout.event_share_item, container, false);
	}
}