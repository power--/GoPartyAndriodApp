package com.goparty.app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class ContactAddActivity  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);
        
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_contact_add_back);
		 btnNavBack.setOnClickListener(new BackOnClick());
    }
    
    private class BackOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    			finish();
    	}
    }
}
