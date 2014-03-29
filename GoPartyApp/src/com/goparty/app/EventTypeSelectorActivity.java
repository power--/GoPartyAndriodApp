package com.goparty.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.goparty.app.common.ActivityConst;
import com.goparty.biz.EventService;
import com.goparty.model.EventCategory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class EventTypeSelectorActivity extends Activity {
	private List<EventCategory> categoryList;
	private ArrayList<Integer> selectedCategoryIdList = new ArrayList<Integer>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_selector);
        
        boolean loadDataResult = loadData();
        
        if (!loadDataResult) {
        	return;
        }
        
        GridView gridview = (GridView) findViewById(R.id.gridview_event_types);  
        
        ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
        for(EventCategory category : categoryList) {
        	
        	HashMap<String, Object> map = new HashMap<String, Object>();  
        	map.put("ItemImage", R.drawable.goparty_event_type_default);//添加图像资源的ID  
        	//map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText
        	
        	listImageItem.add(map);
        }  
        
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                                                  listImageItem,
                                                  R.layout.event_type_item, 
                                                  new String[] {"ItemImage"},   
                                                  new int[] {R.id.event_type_item_image});  

        gridview.setAdapter(saImageItems);  
  
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
//	      HashMap<String, Object> item=(HashMap<String, Object>) adapterView.getItemAtPosition(position);  
//	      //显示所选Item的ItemText  
//	      setTitle((String)item.get("ItemText"));
    	  RelativeLayout clickedItem = (RelativeLayout)view;
    	  View checkIcon = clickedItem.findViewById(R.id.event_type_item_check_icon);
    	  if (checkIcon.getVisibility() == View.INVISIBLE) {
    		  checkIcon.setVisibility(View.VISIBLE);
    		  addSelectedItem(position);
    	  } else {
    		  checkIcon.setVisibility(View.INVISIBLE);
    		  removeSelectedItem(position);
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
//    				StringBuilder builder = new StringBuilder();
//    				for (int id : selectedCategoryIdList) {
//    					builder.append(id);
//    					builder.append(";");
//    				}
//    				
    				//Toast.makeText(getApplicationContext(), builder.toString(), Toast.LENGTH_LONG).show();
    				
    				Bundle bundle = new Bundle(); 
    		        bundle.putIntegerArrayList(ActivityConst.EVENT_CATEGORY_IDS, selectedCategoryIdList); 
    		         
    		        setResult(RESULT_OK, getIntent().putExtras(bundle));
    		        finish(); 
    				
    				break;
//    				
    			case R.id.btn_nav_event_type_back:
    				finish();
    				break;
    		}
    		
    	}
    }
    
    private boolean loadData() {
    	EventService serv = new EventService();
    	categoryList = serv.getAllEeventCategory();
    	
    	return categoryList != null;
    }
    
    private void removeSelectedItem(int position) {
    	EventCategory category = categoryList.get(position);
    	selectedCategoryIdList.remove((Integer)category.getId());
    }
    
    private void addSelectedItem(int position) {
    	EventCategory category = categoryList.get(position);
    	selectedCategoryIdList.add(category.getId());
    }
}
