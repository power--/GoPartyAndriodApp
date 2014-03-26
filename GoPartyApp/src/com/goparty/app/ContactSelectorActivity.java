package com.goparty.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.goparty.adapter.ContactSelectorAdapter;
import com.goparty.adapter.FaceGridViewAdapter;
import com.goparty.app.common.ServerListener;
import com.goparty.biz.ContactService;
import com.goparty.model.Contact;
import com.goparty.widget.ContactSelectorItem;

public class ContactSelectorActivity extends Activity implements ServerListener<Contact> {
	private View loadingView;
	private boolean isEnd = false;
	private boolean isLoadingRemoved = false;
	
	private Button btnSubmit;
	
	private ListAdapter resultAdapter = null;
	private ListView list;
	private List<Contact> contactDataList = new ArrayList<Contact>();
	
	private GridView faceGridView;
	private FaceGridViewAdapter faceGridViewAdapter;
	private List<Contact> faceDataList = new ArrayList<Contact>();
	
	private final int gridviewColumnWidth = 80;
	private final int gridviewColumnSpacing = 4;
	
	@Override
    public void onCreate(Bundle contactSelectorInstanceState) {
        super.onCreate(contactSelectorInstanceState);
        setContentView(R.layout.contact_selector_layout);
        
        Button btnNavBack = (Button) findViewById(R.id.btn_nav_contact_add_back);
		btnNavBack.setOnClickListener(new BackOnClick());
		
		btnSubmit = (Button) findViewById(R.id.btn_event_contact_submit);
//		btnSubmit.setOnClickListener(new BackOnClick());
		
        ContactService dataServ = new ContactService();
        dataServ.getContacts(ContactSelectorActivity.this, 0, 0);
        
        list = (ListView)findViewById(R.id.contactsListSelectorView);
        resultAdapter = new ContactSelectorAdapter(this, contactDataList);
        list.setAdapter(resultAdapter);
        
        loadingView = LayoutInflater.from(this).inflate(R.layout.list_footer, null);
        list.addFooterView(loadingView);
        
        list.setOnItemClickListener(listItemOnClickListener);
        
        faceGridView = (GridView) findViewById(R.id.faceGridView);
        
        for(int i=0;i<0;i++)  
        {
        faceDataList.add(new Contact("location" + i, "nickName" + i,
				"photo" + i, "signature" + i,	new Date(), "M"));
        }
        
        faceGridView.setColumnWidth(gridviewColumnWidth);
		faceGridView.setHorizontalSpacing(gridviewColumnSpacing);
		faceGridView.setStretchMode(GridView.NO_STRETCH);
        resetFaceGridView();
        faceGridViewAdapter = new FaceGridViewAdapter(this, faceDataList);
        faceGridView.setAdapter(faceGridViewAdapter);
        
        /*ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
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

        faceGridView.setAdapter(saImageItems);  
*/
	}

	@Override
	public void serverDataArrived(List<Contact> list, boolean isEnd) {
		this.isEnd = isEnd;
		
		if (list != null && list.size() > 0) { 
			for (Contact item : list) {
				contactDataList.add(item);
			}
		}
		
		Message localMessage = new Message();
		if(!isEnd) {
			localMessage.what = 1;
		}
		else
		{
			localMessage.what = 2;
		}
		
		this.handler.sendMessage(localMessage);
	}
	
	private AdapterView.OnItemClickListener listItemOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,long id) 
		{
			//Toast.makeText(ContactSelectorActivity.this, "Hi " + position + ", Item Click Event!!", Toast.LENGTH_SHORT).show();
			ContactSelectorItem clickedItem = (ContactSelectorItem)v;
			boolean isSelected = clickedItem.setSelected();
			
			if (isSelected) {
				faceDataList.add(new Contact("location1", "nickName1",
						"photo1", "signature1",	new Date(), "M"));
			} else if (faceDataList.size() > 0) {
				faceDataList.remove(0);
			}
			
			resetFaceGridView();
			faceGridViewAdapter.notifyDataSetChanged();	
			
			resetSubitButtonText();
		}
	};
	
	private class BackOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    			finish();
    	}
    }
	
	private void resetFaceGridView() {
		faceGridView.setNumColumns(faceDataList.size());
		faceGridView.setLayoutParams(
				new LayoutParams(faceDataList.size() * (gridviewColumnWidth + gridviewColumnSpacing),
						gridviewColumnWidth));
	}
	
	private void resetSubitButtonText() {
		btnSubmit.setText(String.format("%s(%s)", getString(R.string.confirm), faceDataList.size()));
	}
	
	Handler handler = new Handler() 
	{
		public void handleMessage(Message paramMessage) 
		{
			if(paramMessage.what == 1)
			{
				loadingView.setVisibility(View.GONE);
			}
			else if(paramMessage.what == 2)
			{
				list.removeFooterView(loadingView);
				isLoadingRemoved = true;
			}
			else if(paramMessage.what == 3)
			{
				list.addFooterView(loadingView);
				loadingView.setVisibility(View.VISIBLE);
				isLoadingRemoved =false;
			}
			else if(paramMessage.what == 4)
			{
				loadingView.setVisibility(View.VISIBLE);
			}
		}
	};
}
