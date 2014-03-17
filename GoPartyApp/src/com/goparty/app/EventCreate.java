package com.goparty.app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class EventCreate extends Activity {
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
    	    		startActivity(eventTypeIntent);
    				
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
