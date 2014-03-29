package com.goparty.app;

import java.util.ArrayList;

import com.goparty.app.common.ActivityConst;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class EventCreate extends Activity {
	private final int EVENT_TYPE_REQUEST_TEXT = 0;
	private final int EVENT_CONTACT_REQUEST_TEXT = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create);
        
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
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode != RESULT_OK) {
    		//if (resultCode != RESULT_OK) {}
    		String msg = String.format("%s; %s;", R.string.activity_intent_commu_not_ok, resultCode); 
    		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
        if (requestCode == EVENT_TYPE_REQUEST_TEXT) { 
            Bundle bundle = data.getExtras(); 
            ArrayList<Integer> selectedCategoryIdList = bundle.getIntegerArrayList(ActivityConst.EVENT_CATEGORY_IDS); 
           
            StringBuilder builder = new StringBuilder();
			for (int id : selectedCategoryIdList) {
				builder.append(id);
				builder.append(";");
			}
            
            Toast.makeText(this, builder, Toast.LENGTH_SHORT).show(); 
        }else if (requestCode == EVENT_CONTACT_REQUEST_TEXT) { 
             
        } 
    } 
    
    private class LayoutOnClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.layout_event_create_date:
    				Intent intent = new Intent(EventCreate.this, EventDateSelectorActivity.class);			
    	    		startActivity(intent);
    				break;
    				
    			case R.id.event_add_type_add:
    				Intent eventTypeIntent = new Intent(EventCreate.this, EventTypeSelectorActivity.class);			
    				//startActivity(eventTypeIntent);
    				startActivityForResult(eventTypeIntent, EVENT_TYPE_REQUEST_TEXT);
    	    		break;
    	    		
    			case R.id.event_add_contact:
    				Intent contactSelectorIntent = new Intent(EventCreate.this, ContactSelectorActivity.class);			
    	    		startActivity(contactSelectorIntent);
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
}
