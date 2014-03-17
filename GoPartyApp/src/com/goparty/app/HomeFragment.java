package com.goparty.app;

import java.util.ArrayList;
import java.util.List;

import com.goparty.app.R;
import com.goparty.model.Event;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class HomeFragment extends Fragment {
	//original
	/*public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		return messageLayout;
	}*/
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, null);
        Spinner timeSpinner = (Spinner)v.findViewById(R.id.timeSelector);
		List<String> timeList = new ArrayList<String>();
		timeList.add("最近3天");
		timeList.add("最近7天");
		timeList.add("最近30天");
        addItemsOnSpinner(timeSpinner,timeList);
        
        Spinner ownerSpinner = (Spinner)v.findViewById(R.id.ownerSelector);
		List<String> ownerList = new ArrayList<String>();
		ownerList.add("瑞贝卡");
		ownerList.add("杰克");
		ownerList.add("露丝");
        addItemsOnSpinner(ownerSpinner,ownerList);
        
        Spinner typeSpinner = (Spinner)v.findViewById(R.id.typeSelector);
		List<String> typeList = new ArrayList<String>();
		typeList.add("户外");
		typeList.add("交友");
		typeList.add("体育");
        addItemsOnSpinner(typeSpinner,typeList);
        ListView eventlv = (ListView) v.findViewById(R.id.homeEventView);
        EventListAdapter aAdapter = new EventListAdapter(this.getActivity());
        List<Event> aData = new ArrayList<Event>();
        for(int i=0;i<10;i++)
        {
        	aData.add(new Event());
        }
        aAdapter.setData(aData);
        eventlv.setAdapter(aAdapter);
        return v;
    }

	  public void addItemsOnSpinner(Spinner theSpinner, List<String> theList) {
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, theList);
			dataAdapter.setDropDownViewResource(R.layout.drop_down_item);
			theSpinner.setAdapter(dataAdapter);
		  }
	  
 public static class EventListAdapter extends ArrayAdapter<Event>{

		
		private LayoutInflater mInflater;
		public EventListAdapter(Context context) { 
			// TODO Auto-generated constructor stub
			super(context, android.R.layout.simple_list_item_2);
			mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		}
		
		
		public void setData(List<Event> data){
			clear();
			if(data!=null){
				addAll(data);
			}
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			if(convertView==null){
				view=mInflater.inflate(R.layout.event_item_layout, parent,false);
			}else{
				view=convertView;
			}
			/*
			Event item=getItem(position);
			((ImageView)view.findViewById(R.id.user_icon)).setImageDrawable(null);
			((TextView)view.findViewById(R.id.event_name)).setText(item.getTitle());
			((TextView)view.findViewById(R.id.total_number)).setText(item.getAttendees().size());
			((TextView)view.findViewById(R.id.location)).setText(item.getLocation().getLocation());
			((TextView)view.findViewById(R.id.event_type)).setText(item.getEventCategory().toString());
			((TextView)view.findViewById(R.id.event_date)).setText(item.getEndTime().toString());
			*/
			return view;
		}
		
	}

}
