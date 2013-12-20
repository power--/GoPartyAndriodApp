package com.goparty.app;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class EventCreate  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create);        
    }
    
    public void event_create_back(View v) {     //标题栏 返回按钮
      	this.finish();
      }  
}
