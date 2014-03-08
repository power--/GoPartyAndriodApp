package com.goparty.app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class EventCreate  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create);
        
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_event_back);
		 btnNavBack.setOnClickListener(new BackOnClick());
		 
		 Button btnSubmit = (Button) findViewById(R.id.btn_event_add_submit);
		 btnSubmit.setOnClickListener(new SubmitOnClick());
    }
    
    private class BackOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
//    		switch(v.getId())
//    		{
//    		case R.id.btn_send:
//    			break;
//    		case R.id.btn_back:
    			finish();
//    			break;
    	}
    }
    
    private class SubmitOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		Toast.makeText(getApplicationContext(), "pending integration", Toast.LENGTH_LONG).show();
    	}
    }
}
