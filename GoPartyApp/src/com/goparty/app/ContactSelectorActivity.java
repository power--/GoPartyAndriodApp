package com.goparty.app;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.goparty.adapter.ContactSelectorAdapter;
import com.goparty.adapter.FaceGridViewAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;
import com.goparty.widget.ContactSelectorItem;

public class ContactSelectorActivity extends Activity {
	private final int GRIDVIEW_COLUMN_WIDTH = 80;
	private final int GRIDVIEW_COLUMN_SPACING = 4;
	
//	private View loadingView;
//	private boolean isEnd = false;
//	private boolean isLoadingRemoved = false;
	
	private Button btnSubmit;
	
	private ListView contactListView;
	private ContactSelectorAdapter resultAdapter;
	
	private GridView faceGridView;
	private FaceGridViewAdapter faceGridViewAdapter;
	
	private ArrayList<Contact> contactDataList = new ArrayList<Contact>();
	private ArrayList<Contact> faceDataList = new ArrayList<Contact>();
	private ArrayList<Integer> selectedContactIdsList = new ArrayList<Integer>();
	
	@Override
    public void onCreate(Bundle contactSelectorInstanceState) {
        super.onCreate(contactSelectorInstanceState);
        initLayout();
		
        initContactListView();
        initSelectionGridView();
	}
	
	private void initLayout() {
		setContentView(R.layout.contact_selector_layout);
		
		ButtonOnClickListener buttonOnclickedListener = new ButtonOnClickListener();
        Button btnNavBack = (Button) findViewById(R.id.btn_nav_contact_add_back);
		btnNavBack.setOnClickListener(buttonOnclickedListener);
		
		btnSubmit = (Button) findViewById(R.id.btn_event_contact_submit);
		btnSubmit.setOnClickListener(buttonOnclickedListener);
		
		String queryRes = "";
		DataQuery dataQuery = new DataQuery();
		try {
			queryRes = dataQuery.execute("").get();
		} catch (InterruptedException e) {
			queryRes = e.getMessage();
		} catch (ExecutionException e) {
			queryRes = e.getMessage();
		}
		
		HandleDataQueryResult(queryRes);
	}
	
	private void initContactListView() {
		contactListView = (ListView)findViewById(R.id.contactsListSelectorView);
        resultAdapter = new ContactSelectorAdapter(this, contactDataList);
        contactListView.setAdapter(resultAdapter);
        
        //loadingView = LayoutInflater.from(this).inflate(R.layout.list_footer, null);
        //contactListView.addFooterView(loadingView);
        
        contactListView.setOnItemClickListener(contactlistItemOnClickListener);
	}
	
	private void initSelectionGridView() {
		faceGridView = (GridView) findViewById(R.id.faceGridView);
                
        faceGridView.setColumnWidth(GRIDVIEW_COLUMN_WIDTH);
		faceGridView.setHorizontalSpacing(GRIDVIEW_COLUMN_SPACING);
		faceGridView.setStretchMode(GridView.NO_STRETCH);
        resetFaceGridViewParam();
        faceGridViewAdapter = new FaceGridViewAdapter(this, faceDataList);
        faceGridView.setAdapter(faceGridViewAdapter);
	}
	
	private AdapterView.OnItemClickListener contactlistItemOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) 
		{
			Contact clickedItemData = contactDataList.get(position);
			
			//Toast.makeText(ContactSelectorActivity.this, "Hi " + position + ", Item Click Event!!", Toast.LENGTH_SHORT).show();
			ContactSelectorItem clickedItem = (ContactSelectorItem)v;
			boolean isSelected = clickedItem.setSelected();
			
			if (isSelected) {
				faceDataList.add(clickedItemData);
				selectedContactIdsList.add(Integer.parseInt(clickedItemData.getId()));
			} else {
				faceDataList.remove(clickedItemData);
				selectedContactIdsList.remove(Integer.parseInt(clickedItemData.getId()));
			}
			
			resetFaceGridViewParam();
			faceGridViewAdapter.notifyDataSetChanged();	
			
			resetSubitButtonText();
		}
	};
	
	private void resetFaceGridViewParam() {
		faceGridView.setNumColumns(faceDataList.size());
		faceGridView.setLayoutParams(
				new LayoutParams(faceDataList.size() * (GRIDVIEW_COLUMN_WIDTH + GRIDVIEW_COLUMN_SPACING),
						GRIDVIEW_COLUMN_WIDTH));
	}
	
	private void resetSubitButtonText() {
		btnSubmit.setText(String.format("%s(%s)", getString(R.string.confirm), faceDataList.size()));
	}
	
	private class ButtonOnClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.btn_nav_contact_add_back:
    				setResult(RESULT_CANCELED);
    				finish();
    				break;
    			case R.id.btn_event_contact_submit:
    				Bundle bundle = new Bundle(); 
    		        bundle.putIntegerArrayList(ActivityConst.EVENT_CONTACT_IDS, selectedContactIdsList); 
    		         
    		        setResult(RESULT_OK, getIntent().putExtras(bundle));
    		        finish();
    				break;
    		}
    	}
    }
	
	/*Handler handler = new Handler() 
	{
		public void handleMessage(Message paramMessage) 
		{
			if(paramMessage.what == 1)
			{
				loadingView.setVisibility(View.GONE);
			}
			else if(paramMessage.what == 2)
			{
				contactListView.removeFooterView(loadingView);
				isLoadingRemoved = true;
			}
			else if(paramMessage.what == 3)
			{
				contactListView.addFooterView(loadingView);
				loadingView.setVisibility(View.VISIBLE);
				isLoadingRemoved =false;
			}
			else if(paramMessage.what == 4)
			{
				loadingView.setVisibility(View.VISIBLE);
			}
		}
	};*/
	
	private class DataQuery extends AsyncTask<String, Integer, String> 
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
            	ArrayList<Contact> arrayList = new ContactService().getContacts();
            	if (arrayList != null && arrayList.size() > 0) { 
        			for (Contact item : arrayList) {
        				contactDataList.add(item);
        			}
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
        	resultAdapter.notifyDataSetChanged();
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
	
	private void HandleDataQueryResult(String result) {
    	Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
