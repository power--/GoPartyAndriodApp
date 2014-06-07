package com.goparty.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goparty.app.R;
import com.goparty.model.Event;
import com.goparty.model.EventCategory;

public class EventListAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	
	private List<Event> eventDataList;
	
	public EventListAdapter(Context context, List<Event> eventDataList) {
//	public EventListAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
		this.eventDataList = eventDataList;
	}
	
//	public EventListAdapter(Context context, List<Event> data) { 
//		super(context, android.R.layout.simple_list_item_2, data);
//		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	}
//	
//	public void setData(List<Event> data){
//		clear();
//		if(data != null){
//			addAll(data);
//		}
//	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if(convertView==null){
			view=mInflater.inflate(R.layout.event_item_layout, parent,false);
		}else{
			view=convertView;
		}
		
//		Event item= getItem(position);
		Event item= eventDataList.get(position);
//		((ImageView)view.findViewById(R.id.user_icon)).setImageDrawable(null);
		((TextView)view.findViewById(R.id.event_name)).setText(item.getTitle());
		((TextView)view.findViewById(R.id.total_number)).setText(item.getMembers().size() + "");
		((TextView)view.findViewById(R.id.location)).setText(item.getLocation());
		((TextView)view.findViewById(R.id.event_type)).setText(getFormatedNames(item.getCategories()));
		((TextView)view.findViewById(R.id.event_date)).setText(getFormatedDate(item.getStartTime()));
		
		return view;
	}
	
	private String getFormatedNames(List<EventCategory> cates) {
		if (cates == null || cates.size() == 0) {
			return "";
		}
		
		StringBuilder catesBuilder = new StringBuilder();
		for (int index = 0; index < cates.size(); index++) {
			catesBuilder.append(cates.get(index).getName());
			if (index != cates.size() -1) {
				catesBuilder.append("|");
			}
		}
		
		return catesBuilder.toString();
	}
	
	private String getFormatedDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
    	return dateFormat.format(date);
	}

	@Override
	public int getCount() {
		return eventDataList.size();
//		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		eventDataList.get(arg0);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}