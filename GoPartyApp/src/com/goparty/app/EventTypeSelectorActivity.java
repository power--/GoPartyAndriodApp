package com.goparty.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.goparty.adapter.EventCategoryListAdapter;
import com.goparty.app.common.ActivityConst;
import com.goparty.biz.EventService;
import com.goparty.model.EventCategory;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EventTypeSelectorActivity extends Activity {
	//private List<EventCategory> categoryList = new ArrayList<EventCategory>();
	private ArrayList<EventCategory> gridViewItemDataList = new ArrayList<EventCategory>();
	private ArrayList<String> selectedCategoryIdList = new ArrayList<String>();
	private EventService eventService = new EventService();
	EventCategoryListAdapter listAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_selector);
        
        ArrayList<String> selectedCategoryIdList = getIntent().getStringArrayListExtra(ActivityConst.INVENT_ARG_EVENT_TYPE);
        if (selectedCategoryIdList != null && selectedCategoryIdList.size() > 0) {
        	this.selectedCategoryIdList = selectedCategoryIdList;
        }

        GridView gridview = (GridView) findViewById(R.id.gridview_event_types);  
                
        listAdapter = new EventCategoryListAdapter(this, gridViewItemDataList, selectedCategoryIdList);
        gridview.setAdapter(listAdapter);  
  
        DataQuery dataQuery = new DataQuery();
        
        String dataQueryResultString = "";
        try {
        	dataQueryResultString = dataQuery.execute("").get();
		} catch (InterruptedException e) {
			dataQueryResultString = e.getMessage();
		} catch (ExecutionException e) {
			dataQueryResultString = e.getMessage();
		}
        
        HandleDataQueryResult(dataQueryResultString);
        
        gridview.setOnItemClickListener(new ItemClickListener());  
        
        LayoutOnClickListener onclickEventListener = new LayoutOnClickListener();
		Button btnNavBack = (Button) findViewById(R.id.btn_nav_event_type_back);
		btnNavBack.setOnClickListener(onclickEventListener);
		 
		Button btnSubmit = (Button) findViewById(R.id.btn_event_type_submit);
		btnSubmit.setOnClickListener(onclickEventListener);
    }
      
    class  ItemClickListener implements OnItemClickListener  
    {  
    	public void onItemClick(AdapterView<?> adapterView,//The AdapterView where the click happened   
                View view,//The view within the AdapterView that was clicked  
                int position,//The position of the view in the adapter  
                long rowId//The row id of the item that was clicked
                ) {  
    	  RelativeLayout clickedItem = (RelativeLayout)view;
    	  View checkIcon = clickedItem.findViewById(R.id.event_type_item_check_icon);
    	  TextView textViewId = (TextView)clickedItem.findViewById(R.id.event_type_item_id);
    	  String categoryId = textViewId.getText().toString();
    	  if (checkIcon.getVisibility() == View.INVISIBLE) {
    		  checkIcon.setVisibility(View.VISIBLE);
    		  addSelectedItem(categoryId);
    	  } else {
    		  checkIcon.setVisibility(View.INVISIBLE);
    		  removeSelectedItem(categoryId);
    	  }
    	}
    }
    
//    private ImageView getCheckedIcon() {
//    	if (checkIconImageView != null) {
//    		return checkIconImageView;
//    	}
//    		
//    	Drawable drawable= getResources().getDrawable(R.drawable.goparty_event_type_checked);
//    	ImageView imageView = new ImageView(this);
//    	imageView.setImageDrawable(drawable);
//    	
//    	return checkIconImageView;
//    }
    
    private class LayoutOnClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.btn_event_type_submit:
    				Bundle bundle = new Bundle(); 
    		        bundle.putStringArrayList(ActivityConst.EVENT_CATEGORY_IDS, selectedCategoryIdList); 
    		         
    		        setResult(RESULT_OK, getIntent().putExtras(bundle));
    		        finish();
    				break;
    			case R.id.btn_nav_event_type_back:
    				setResult(RESULT_CANCELED);
    				finish();
    				break;
    		}
    		
    	}
    }
    
    private void addSelectedItem(String categoryId) {
    	if (!selectedCategoryIdList.contains(categoryId)) {
    		selectedCategoryIdList.add(categoryId);
    	}
    }
    
    private void removeSelectedItem(String categoryId) {
    	selectedCategoryIdList.remove(categoryId);
    }
    
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
            	List<EventCategory> cateList = eventService.getAllEeventCategory();
            	if (cateList != null && cateList.size() > 0) {
    	        	gridViewItemDataList.clear();
    	        	gridViewItemDataList.addAll(cateList);
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
        	listAdapter.notifyDataSetChanged();
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
