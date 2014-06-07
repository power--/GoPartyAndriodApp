package com.goparty.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goparty.app.R;
import com.goparty.model.EventCategory;

public class EventCategoryListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<EventCategory> eventCategoryDataList;
	private List<String> selectedItemIdList;
	
	public EventCategoryListAdapter(Context context, List<EventCategory> eventCategoryDataList, List<String> selectedItemIdList) {
		this.mInflater = LayoutInflater.from(context);
		this.eventCategoryDataList = eventCategoryDataList;
		this.selectedItemIdList = selectedItemIdList;
	}
	
	@Override
	public int getCount() {
		if (eventCategoryDataList == null) {
			return 0;
		}
		
		return eventCategoryDataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return eventCategoryDataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
    	EventCategoryViewHolder viewHolder = null;	
	    if (convertView == null)
	    {
	    	convertView = mInflater.inflate(R.layout.event_type_item, null);
	    	viewHolder = new EventCategoryViewHolder();
			viewHolder.tvId = (TextView) convertView.findViewById(R.id.event_type_item_id);
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.event_type_item_name);
			viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.event_type_item_image);
			viewHolder.ivCheck = (ImageView) convertView.findViewById(R.id.event_type_item_check_icon);
			  
			convertView.setTag(viewHolder);
	    } else {
	        viewHolder = (EventCategoryViewHolder) convertView.getTag();
	    }
	    
	    EventCategory cate = eventCategoryDataList.get(position);
	    viewHolder.tvId.setText(cate.getId());
	    viewHolder.tvName.setText(cate.getName());
	    viewHolder.ivImage.setImageDrawable(viewHolder.ivImage.getResources().getDrawable(R.drawable.goparty_event_type_default));
	    if (selectedItemIdList != null && selectedItemIdList.contains(cate.getId())) {
	    	viewHolder.ivCheck.setVisibility(View.VISIBLE);
	    } else {
	    	viewHolder.ivCheck.setVisibility(View.INVISIBLE);
	    }
	    return convertView;
	}
	
	static class EventCategoryViewHolder { 
        public TextView tvId;
        public TextView tvName;
        public ImageView ivImage;
        public ImageView ivCheck;
    }
}
