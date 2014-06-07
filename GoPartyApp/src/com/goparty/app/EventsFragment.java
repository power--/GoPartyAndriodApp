package com.goparty.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.goparty.app.R;
import com.goparty.app.common.ActivityConst;
import com.goparty.adapter.EventListAdapter;
import com.goparty.biz.EventService;
import com.goparty.model.Event;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class EventsFragment extends Fragment {
	View eventsLayout;
	EventListAdapter myEventsListViewAdapter;
	ArrayList<Event> myEventsDataList = new ArrayList<Event>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		eventsLayout = inflater.inflate(R.layout.events_layout, container, false);
			
		initLayouts();
		
		return eventsLayout;		
	}
	
	private void initLayouts() {
		CreateOnClick createOnClick = new CreateOnClick();
		Button btnNavCreate = (Button) eventsLayout.findViewById(R.id.btn_nav_event_create);
		btnNavCreate.setOnClickListener(createOnClick);
		
		Button btnEventInvite = (Button) eventsLayout.findViewById(R.id.btn_event_invite);
		btnEventInvite.setOnClickListener(createOnClick);
		
		ListView myEventListView = (ListView) eventsLayout.findViewById(R.id.myEventsListView);
		myEventsListViewAdapter = new EventListAdapter(this.getActivity(), myEventsDataList);
        myEventListView.setAdapter(myEventsListViewAdapter);
        myEventListView.setOnItemClickListener(mOnClickListener);
        
        MyEventsGroupQueryTask myEventsGroupQueryTask = new MyEventsGroupQueryTask();
        String dataQueryResultString = "";
        try {
        	dataQueryResultString = myEventsGroupQueryTask.execute("").get();
		} catch (InterruptedException e) {
			dataQueryResultString = e.getMessage();
		} catch (ExecutionException e) {
			dataQueryResultString = e.getMessage();
		}
        
        if (!dataQueryResultString.startsWith("ok")) {
        	Toast.makeText(this.getActivity(), dataQueryResultString, Toast.LENGTH_LONG).show();
        }
	}
	
	private class CreateOnClick implements OnClickListener {
        @Override
        public void onClick(View v) {
        	switch(v.getId()) {
	        	case R.id.btn_nav_event_create:
	        		Intent intent = new Intent (getActivity(), EventEditActivity.class);			
	        		startActivity(intent);
	        		break;
	        	case R.id.btn_event_invite:
	        		Intent eventInviteIntent = new Intent(getActivity(), EventInvitationListActivity.class);
	        		startActivity(eventInviteIntent);
        	}
        }
	}
	
	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) 
		{
			Intent intent = new Intent (getActivity(), EventDetailsActivity.class);
			intent.putExtra(ActivityConst.INVENT_ARG_EVENT_ID, myEventsDataList.get(position).getId());
			startActivity(intent);
		}
	};
	
	private class MyEventsGroupQueryTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				EventService eventService = new EventService();
				List<Event> resultList = eventService.getMyEvents();
				if (resultList != null && resultList.size() > 0) {
					//myEventsListViewAdapter.setData(resultList);
					myEventsDataList.clear();
					myEventsDataList.addAll(resultList);
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
			myEventsListViewAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onCancelled() {
		}
	}
}
