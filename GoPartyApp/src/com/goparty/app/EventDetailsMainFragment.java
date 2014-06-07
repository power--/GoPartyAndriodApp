package com.goparty.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import com.goparty.biz.EventService;
import com.goparty.model.EventCategory;
import com.goparty.model.EventDetails;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsMainFragment extends Fragment {
	EventService eventService = new EventService();
	EventDetails eventDetails;
	View mainView;
	String eventId;
	
	public static EventDetailsMainFragment getInstance(Bundle bundle) {  
		EventDetailsMainFragment eventDetailsMainFragment = new EventDetailsMainFragment();  
		eventDetailsMainFragment.setArguments(bundle);  
	    return eventDetailsMainFragment;  
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainView = inflater.inflate(R.layout.event_details_main, container, false);
		
		Bundle argus = getArguments();
		eventId = argus.getString("eventId");
		
		if (eventId == null || eventId == "") {
			Toast.makeText(getActivity(), "Event is empty.", Toast.LENGTH_LONG).show();
			return mainView;
		}
		
		initViews();
		queryEventDetailsData();
		
		return mainView;
	}
	
	private void initViews() {
		Button btnEdit = (Button)mainView.findViewById(R.id.btn_event_details_edit);
		btnEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), EventEditActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void queryEventDetailsData() {
		DataQueryTask detailsQuery = new DataQueryTask();
		boolean result = false;
		try {
			result = detailsQuery.execute(eventId).get();
		} catch (InterruptedException e) {
			Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
		} catch (ExecutionException e) {
			Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
		}
		
		if (!result) {
			Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
		}
	}
	
	private class DataQueryTask extends AsyncTask<String, Integer, Boolean> 
    {
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected Boolean doInBackground(String... params) 
        {  
            try 
            {
            	eventDetails = eventService.getEventDetails(params[0]);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            
            return true;
        }  
          
        @Override  
        protected void onProgressUpdate(Integer... progresses) 
        {  
        }  
          
        @Override  
        protected void onPostExecute(Boolean result) 
        {
        	if (result) {
        		bindData();
        	}
        	//TO-DO: we should add a loading mask and keep it with promote when error happens.
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
	
	private void bindData() {
		if (eventDetails == null) {
			return;
		}
		
		TextView name = (TextView)mainView.findViewById(R.id.event_details_name);
		name.setText(eventDetails.getTitle());
		TextView loc = (TextView)mainView.findViewById(R.id.event_details_address);
		loc.setText(eventDetails.getLocation());
		
		TextView category = (TextView)mainView.findViewById(R.id.event_details_type);
		StringBuilder catsBuilder = new StringBuilder();
		List<EventCategory> catsList = eventDetails.getCategories();
		
		if (catsList != null) {
			for (int i = 0; i < catsList.size(); i++) {
				catsBuilder.append(catsList.get(i).getName());
				if (i != catsList.size() -1) {
					catsBuilder.append(" | ");
				}
			}
		}
		
		category.setText(catsBuilder.toString());
		
		TextView start = (TextView)mainView.findViewById(R.id.event_details_starttime);
		start.setText(format(eventDetails.getStartTime()));
		TextView end = (TextView)mainView.findViewById(R.id.event_details_endtime);
		end.setText(format(eventDetails.getEndTime()));
		
		TextView desc = (TextView)mainView.findViewById(R.id.event_details_details);
		desc.setText(eventDetails.getDescription());
	}
	
	private String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
    	return dateFormat.format(date);
	}
}