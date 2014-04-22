package com.goparty.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventDetailsShareFragment  extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		
		//return inflater.inflate(R.layout.event_details_share, container, false);
		return inflater.inflate(R.layout.event_share_item, container, false);
	}
}