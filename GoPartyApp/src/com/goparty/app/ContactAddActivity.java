package com.goparty.app;

import java.util.ArrayList;

import com.goparty.app.common.ActivityConst;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;

public class ContactAddActivity extends Activity {
	EditText keywordBox;
	int take = 1000;
	int skip = 0;
	String keyword;
	
	ArrayList<Contact> searchResult;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);
        
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_contact_add_back);
		 btnNavBack.setOnClickListener(new ButtonOnClick());
		 
		 Button btnSearch = (Button) findViewById(R.id.btn_contact_add_search);
		 btnSearch.setOnClickListener(new ButtonOnClick());
    }
    
    private class ButtonOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
			case R.id.btn_nav_contact_add_back:
				finish();
				break;
			case R.id.btn_contact_add_search:
				if (keywordBox == null) {
					keywordBox = (EditText)findViewById(R.id.contact_add_keyword);
				}
				
				keyword = keywordBox.getText().toString();
				
				SearchTask searchTask = new SearchTask();
				searchTask.execute("");
				break;
			}
    	}
    }
    
    private class SearchTask extends AsyncTask<String, Integer, String> 
    {
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected String doInBackground(String... params) 
        {  
            try 
            {
            	ContactService contactService = new ContactService();
            	searchResult = (ArrayList<Contact>)contactService.search(keyword, take, skip);
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
        	goToResultView();
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
    
    private void goToResultView() {
    	Intent intent = new Intent (this, ContactCommonActivity.class);
    	intent.putExtra(ActivityConst.INTENT_TYPE_CONTACT_SEARCH_RESULT, ActivityConst.INTENT_TYPE_CONTACT_SEARCH_RESULT);
    	intent.putParcelableArrayListExtra(ActivityConst.INTENT_ARG_CONTACT_SEARCH_RESULT, searchResult);
		startActivity(intent);
    }
}
