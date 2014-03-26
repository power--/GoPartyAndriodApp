package com.goparty.app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class EventDateSelectorActivity  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_selector);
        
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_event_time_back);
		 btnNavBack.setOnClickListener(new SubmitOnClick());
		 
//		 Button btnSubmit = (Button) findViewById(R.id.btn_event_add_submit);
//		 btnSubmit.setOnClickListener(new SubmitOnClick());
    }
    
//    private class BackOnClick implements OnClickListener {
//    	@Override
//    	public void onClick(View v) {
//    			finish();
//    	}
//    }
//    
    private class SubmitOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		finish();
    	}
    }
}
