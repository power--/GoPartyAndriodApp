package com.goparty.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import com.goparty.app.common.ActivityConst;
import com.goparty.app.common.UserContext;
import com.goparty.biz.EventService;
import com.goparty.model.EventCategory;
import com.goparty.model.EventCreateMembersRequest;
import com.goparty.model.EventCreateOwnerRequest;
import com.goparty.model.EventCreateRequest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class EventEditActivity extends Activity {
	private final int EVENT_TYPE_REQUEST_TEXT = 0;
	private final int EVENT_CONTACT_REQUEST_TEXT = 1;
	private final int EVENT_DATE_REQUEST_TEXT = 2;
	private TextView typeCountTextView;
	private TextView contactCountTextView;
	private long startDate;
	private long endDate;
	private boolean isDatesReturned;
	//private ArrayList<EventCategory> eventCategories = new ArrayList<EventCategory>();
	private ArrayList<String> selectedCategoryIdList = new ArrayList<String>();
	private ArrayList<String> selectedEventMembers = new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create);        
        initComponentLayout();
    }
    
    private class LayoutOnClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.layout_event_create_date:
    				Intent eventDateIntent = new Intent(EventEditActivity.this, EventDateSelectorActivity.class);
    				if (isDatesReturned) {
    					eventDateIntent.putExtra(ActivityConst.INVENT_ARG_EVENT_START_DATE, String.valueOf(startDate));
    					eventDateIntent.putExtra(ActivityConst.INVENT_ARG_EVENT_END_DATE, String.valueOf(endDate));
    				}
    				startActivityForResult(eventDateIntent, EVENT_DATE_REQUEST_TEXT);
    				break;
    				
    			case R.id.event_add_type_add:
    				Intent eventTypeIntent = new Intent(EventEditActivity.this, EventTypeSelectorActivity.class);	
    				eventTypeIntent.putStringArrayListExtra(ActivityConst.INVENT_ARG_EVENT_TYPE, selectedCategoryIdList);
    				startActivityForResult(eventTypeIntent, EVENT_TYPE_REQUEST_TEXT);
    	    		break;
    	    		
    			case R.id.event_add_contact:
    				Intent contactSelectorIntent = new Intent(EventEditActivity.this, ContactSelectorActivity.class);
    				contactSelectorIntent.putStringArrayListExtra(ActivityConst.INVENT_ARG_CONTACT, selectedEventMembers);
    				startActivityForResult(contactSelectorIntent, EVENT_CONTACT_REQUEST_TEXT);
    				break;
    				
    			case R.id.btn_nav_event_back:
    				finish();
    				break;
    				
    			case R.id.btn_event_add_submit:
    				if (validateInput()) {
    					PostDataTask postTask = new PostDataTask();
    					String result;
    					try {
							result = postTask.execute(fillRequest()).get();
						} catch (InterruptedException e) {
							result = "error: " + e.getMessage();
						} catch (ExecutionException e) {
							result = "error: " + e.getMessage();
						}
    					Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    				}
    				break;
    		}
    	}
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_CANCELED) {
    		return;
    	}
    	
    	if (resultCode != RESULT_OK) {
    		String msg = String.format("%s; %s;", getString(R.string.activity_intent_commu_not_ok), resultCode);
    		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	if (requestCode == EVENT_DATE_REQUEST_TEXT) {
    		onDatesSelectionResult(data);
        } else if (requestCode == EVENT_TYPE_REQUEST_TEXT) { 
    		onTypesSelectionResult(data);
        } else if (requestCode == EVENT_CONTACT_REQUEST_TEXT) { 
        	onContactsSelectionResult(data);
        }
    }
    
    private void onDatesSelectionResult(Intent data) {
    	Bundle bundle = data.getExtras(); 
        long[] selectedDateList = bundle.getLongArray(ActivityConst.EVENT_DATE_IDS);
        if (selectedDateList != null) {
        	isDatesReturned = true;
        	startDate = selectedDateList[0];
        	endDate = selectedDateList[1];
        	((TextView)findViewById(R.id.layout_event_create_startdate)).setText(formatDate(startDate));
            ((TextView)findViewById(R.id.layout_event_create_enddate)).setText(formatDate(endDate));
        }
    }
    
    private void onTypesSelectionResult(Intent data) {
    	Bundle bundle = data.getExtras(); 
        selectedCategoryIdList = bundle.getStringArrayList(ActivityConst.EVENT_CATEGORY_IDS); 
		updateCountTextView(typeCountTextView, selectedCategoryIdList.size());
//
//		StringBuilder builder = new StringBuilder();
//        for (String id : selectedCategoryIdList) {
//        	EventCategory category = new EventCategory();
//        	category.setId(Integer.parseInt(id));
//        	eventCategories.add(category);
//        	
//        	builder.append(id);
//			builder.append(";");
//		}
//		
//        Toast.makeText(this, builder, Toast.LENGTH_SHORT).show(); 
    }
    
    private void onContactsSelectionResult(Intent data) {
    	Bundle bundle = data.getExtras(); 
    	selectedEventMembers = bundle.getStringArrayList(ActivityConst.EVENT_CONTACT_IDS);
        updateCountTextView(contactCountTextView, selectedEventMembers.size());
        
//        StringBuilder builder = new StringBuilder();
//		for (String id : selectedContactIdList) {
//			builder.append(id);
//			builder.append(";");
//			
//			eventMembers.add(id);
//		}
//        Toast.makeText(this, builder, Toast.LENGTH_SHORT).show();
    }
    
    private void updateCountTextView(TextView view, int count) {
    	if (count < 1) {
    		view.setVisibility(View.INVISIBLE);
    	} else {
    		view.setText(count + "");
    		view.setVisibility(View.VISIBLE);
    	}
    }
       
    private void initComponentLayout() {
		 LayoutOnClickListener onclickEventListener = new LayoutOnClickListener();
		        
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_event_back);
		 btnNavBack.setOnClickListener(onclickEventListener);
		 
		 Button btnSubmit = (Button) findViewById(R.id.btn_event_add_submit);
		 btnSubmit.setOnClickListener(onclickEventListener);
		 
		 View layoutDate = findViewById(R.id.layout_event_create_date);
		 layoutDate.setOnClickListener(onclickEventListener);
		 
		 ImageView eventTypeAddBtn = (ImageView)findViewById(R.id.event_add_type_add);
		 eventTypeAddBtn.setOnClickListener(onclickEventListener);
		 
		 ImageView eventContactAddBtn = (ImageView)findViewById(R.id.event_add_contact);
		 eventContactAddBtn.setOnClickListener(onclickEventListener);
		 
		 typeCountTextView = (TextView)findViewById(R.id.type_count_textview);
		 contactCountTextView = (TextView)findViewById(R.id.contact_count_textview);
    }

    private boolean validateInput() {
		if (((EditText)findViewById(R.id.event_add_name)).getText().toString() == "") {
			Toast.makeText(getApplicationContext(), "Name is empty.", Toast.LENGTH_LONG).show();
			return false;
		} 
		
		if (startDate <= 0 || endDate <= 0){
			Toast.makeText(getApplicationContext(), "Time is empty.", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if (selectedCategoryIdList.size() == 0) {
			Toast.makeText(getApplicationContext(), "Category is empty.", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if (selectedEventMembers.size() == 0) {
			Toast.makeText(getApplicationContext(), "Member is empty.", Toast.LENGTH_LONG).show();
			return false;
		}
			
		return true;
	}
    
    private EventCreateRequest fillRequest() {
    	EventCreateRequest req = new EventCreateRequest();
    	req.setTitle(((EditText)findViewById(R.id.event_add_name)).getText().toString());
		
    	req.setStartTime(startDate);
		req.setEndTime(endDate);
		
		req.setLocation(((EditText)findViewById(R.id.event_add_address)).getText().toString());
		req.setDescription(((EditText)findViewById(R.id.event_add_details)).getText().toString());
		
		for (String cateId : selectedCategoryIdList) {
			EventCategory eventCate = new EventCategory();
			eventCate.setId(cateId);
			req.getCategories().add(eventCate);			
		}
		
		for (String id : selectedEventMembers) {
			EventCreateMembersRequest mem = new EventCreateMembersRequest();
			mem.setId(id);
			//to get value from ui
			mem.setAdmin(false);
			req.getMembers().add(mem);
		}
		
		EventCreateMembersRequest mem = new EventCreateMembersRequest();
		mem.setId(UserContext.getId());
		mem.setAdmin(true);
		req.getMembers().add(mem);
		
		EventCreateOwnerRequest owner = new EventCreateOwnerRequest();
		owner.setId(UserContext.getId());
		req.setOwner(owner);
		
		req.setStatus("INIT");
		
		//to get value from ui.
		req.setVisibility("V_PUBLIC");
		//to get value from ui.
		req.setLocationShareable(true);
		
		return req;
    }
    
    private class PostDataTask extends AsyncTask<EventCreateRequest, Integer, String> 
    {
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected String doInBackground(EventCreateRequest... params) 
        {  
            try 
            {
            	EventService eventService = new EventService();
            	boolean result = eventService.createEvent(params[0]);
            	if (result) {
            		return "ok";
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
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
    
	private String formatDate(long date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
    	return dateFormat.format(new Date(date));
	}
}
