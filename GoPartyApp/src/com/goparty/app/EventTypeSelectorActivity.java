package com.goparty.app;

import java.util.ArrayList;
import java.util.HashMap;

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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_selector);
        
        GridView gridview = (GridView) findViewById(R.id.gridview_event_types);  
        
        ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<10;i++)  
        {
        	
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
//		 
//		 View layoutDate = findViewById(R.id.layout_event_create_date);
//		 layoutDate.setOnClickListener(onclickEventListener);
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
    	  } else {
    		  checkIcon.setVisibility(View.INVISIBLE);
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
//    				Intent intent = new Intent(EventTypeSelector.this, EventDateSelectorActivity.class);			
//    	    		startActivity(intent);
    				break;
//    				
    			case R.id.btn_nav_event_type_back:
    				finish();
    				break;
//    				
//    			case R.id.btn_event_add_submit:
//    				Toast.makeText(getApplicationContext(), "btn_event_add_submit clicked", Toast.LENGTH_LONG).show();
//    				break;
    		}
    		
    	}
    }
}
