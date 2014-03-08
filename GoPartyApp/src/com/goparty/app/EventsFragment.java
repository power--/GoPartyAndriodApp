package com.goparty.app;

import com.goparty.app.R;

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

		return eventsLayout;		
	}
	
	private class CreateOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	Intent intent = new Intent (getActivity(), EventCreate.class);			
    		startActivity(intent);
        }
	}
}
