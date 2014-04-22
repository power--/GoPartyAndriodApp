package com.goparty.app;

import java.util.ArrayList;

import com.goparty.app.common.ActivityConst;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class EventCreateActivity extends Activity {
	private final int EVENT_TYPE_REQUEST_TEXT = 0;
	private final int EVENT_CONTACT_REQUEST_TEXT = 1;
	private final int EVENT_DATE_REQUEST_TEXT = 2;
	private TextView typeCountTextView;
	private TextView contactCountTextView;
	
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
    				Intent eventDateIntent = new Intent(EventCreateActivity.this, EventDateSelectorActivity.class);			
    				startActivityForResult(eventDateIntent, EVENT_DATE_REQUEST_TEXT);
    				break;
    				
    			case R.id.event_add_type_add:
    				Intent eventTypeIntent = new Intent(EventCreateActivity.this, EventTypeSelectorActivity.class);			
    				//startActivity(eventTypeIntent);
    				startActivityForResult(eventTypeIntent, EVENT_TYPE_REQUEST_TEXT);
    	    		break;
    	    		
    			case R.id.event_add_contact:
    				Intent contactSelectorIntent = new Intent(EventCreateActivity.this, ContactSelectorActivity.class);			
    	    		//startActivity(contactSelectorIntent);
    				startActivityForResult(contactSelectorIntent, EVENT_CONTACT_REQUEST_TEXT);
    				break;
    				
    			case R.id.btn_nav_event_back:
    				finish();
    				break;
    				
    			case R.id.btn_event_add_submit:
    				Toast.makeText(getApplicationContext(), "btn_event_add_submit clicked", Toast.LENGTH_LONG).show();
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
    		//if (resultCode != RESULT_OK) {}
    		String msg = String.format("%s; %s;", getString(R.string.activity_intent_commu_not_ok), resultCode);
    		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
        if (requestCode == EVENT_TYPE_REQUEST_TEXT) { 
            Bundle bundle = data.getExtras(); 
            ArrayList<String> selectedCategoryIdList = bundle.getStringArrayList(ActivityConst.EVENT_CATEGORY_IDS); 
           
            StringBuilder builder = new StringBuilder();
			for (String id : selectedCategoryIdList) {
				builder.append(id);
				builder.append(";");
			}
			
			updateCountTextView(typeCountTextView, selectedCategoryIdList.size());
			
            Toast.makeText(this, builder, Toast.LENGTH_SHORT).show(); 
        } else if (requestCode == EVENT_CONTACT_REQUEST_TEXT) { 
        	Bundle bundle = data.getExtras(); 
            ArrayList<Integer> selectedContactIdList = bundle.getIntegerArrayList(ActivityConst.EVENT_CONTACT_IDS);
            
            StringBuilder builder = new StringBuilder();
			for (int id : selectedContactIdList) {
				builder.append(id);
				builder.append(";");
			}
            
			updateCountTextView(contactCountTextView, selectedContactIdList.size());
			
            Toast.makeText(this, builder, Toast.LENGTH_SHORT).show();
        } else if (requestCode == EVENT_DATE_REQUEST_TEXT) {
        	Bundle bundle = data.getExtras(); 
            ArrayList<String> selectedDateList = bundle.getStringArrayList(ActivityConst.EVENT_DATE_IDS);
            
            StringBuilder builder = new StringBuilder();
			for (String date : selectedDateList) {
				builder.append(date);
				builder.append(";");
			}
			Toast.makeText(this, builder, Toast.LENGTH_SHORT).show();
			updateDatesView();
        }
    }
    
    private void updateCountTextView(TextView view, int count) {
    	if (count < 1) {
    		view.setVisibility(View.INVISIBLE);
    	} else {
    		view.setText(count + "");
    		view.setVisibility(View.VISIBLE);
    	}
    }
    
    private void updateDatesView() {
    	
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
}
