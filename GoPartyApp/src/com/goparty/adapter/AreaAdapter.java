package com.goparty.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goparty.app.R;
import com.goparty.app.ContactTempActivity;
import com.goparty.widget.ContactListItem;


public class AreaAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ArrayList<String>> mData;
	private boolean isTopLevel = true;
	
	private int typeIndex = 0;
	
	private Context context;
	
	public AreaAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}

	public ArrayList<ArrayList<String>> getDataList()
	{
		return mData;
	}
	
	public String getSelect()
	{
		return mData.get(typeIndex).get(1);
	}
	
	public boolean isTopLevel()
	{
		return isTopLevel;
	}
	
	public void setTypeIndex(int index)
	{
		typeIndex = index;
		if( index > 0)
		{
			isTopLevel = false;
		}
		else
		{
			isTopLevel = true;
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(isTopLevel)
		{
			return mData.size();
		}
		else
		{
			return mData.get(typeIndex).size()-1;
		}
		
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = mInflater.inflate(R.layout.dialog_list_item, null);
		
		if(isTopLevel)
		{
			View view = new View(context);
			LayoutParams param = new LayoutParams(30, 30);
			view.setLayoutParams(param);
			
			String area = mData.get(position).get(1);
			((TextView)convertView.findViewById(R.id.id_area)).setText(area);
			
			if(position == 0)
			{		
				convertView.findViewById(R.id.ic_checked).setVisibility(View.VISIBLE);
			}
			else
			{
				((LinearLayout)convertView).addView(view, 0);
			}
			
		}
		else
		{
			
			
			String area = mData.get(typeIndex).get(position);
			((TextView)convertView.findViewById(R.id.id_area)).setText(area);
			
			if(position == 1)
			{
				View view = new View(context);
				LayoutParams param = new LayoutParams(30, 30);
				view.setLayoutParams(param);
				convertView.findViewById(R.id.ic_checked).setVisibility(View.VISIBLE);
				((LinearLayout)convertView).addView(view, 0);
			}
			else if(position > 1 )
			{
				View view = new View(context);
				LayoutParams param = new LayoutParams(60, 30);
				view.setLayoutParams(param);
				((LinearLayout)convertView).addView(view, 0);
			}

		}
		
		return convertView;
	}
	
	private ArrayList<ArrayList<String>> getData()
	{
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> quanbu = new ArrayList<String>();
		quanbu.add("全锟斤拷锟斤拷锟斤拷");
		quanbu.add("全锟斤拷锟斤拷锟斤拷 ");
		data.add(quanbu);
		
		ArrayList<String> hongkou = new ArrayList<String>();
		hongkou.add("全锟斤拷锟斤拷锟斤拷");
		hongkou.add("锟斤拷锟斤拷锟�");
		hongkou.add("锟斤拷锟斤拷路/锟斤拷锟斤拷路");
		hongkou.add("锟斤拷平路/锟斤拷平锟斤拷园");
		hongkou.add("锟斤拷锟斤拷锟斤拷锟斤拷");
		hongkou.add("锟侥达拷锟斤拷路");
		hongkou.add("鲁迅锟斤拷园");
		data.add(hongkou);
		
		ArrayList<String> zhabei = new ArrayList<String>();
		zhabei.add("全锟斤拷锟斤拷锟斤拷");
		zhabei.add("闸锟斤拷锟斤拷");
		zhabei.add("锟斤拷锟斤拷锟斤拷锟斤拷");
		zhabei.add("锟斤拷锟斤拷锟斤拷站");
		zhabei.add("锟斤拷站");
		zhabei.add("闸锟斤拷锟斤拷园");
		data.add(zhabei);
		
		ArrayList<String> xuhui = new ArrayList<String>();
		xuhui.add("全锟斤拷锟斤拷锟斤拷");
		xuhui.add("锟斤拷锟斤拷锟�");
		xuhui.add("锟斤拷山路");
		xuhui.add("锟斤拷锟斤拷学院");
		xuhui.add("锟截硷拷锟铰凤拷锟斤拷锟�");
		xuhui.add("锟斤拷锟斤拷锟�锟斤拷锟斤拷");
		data.add(xuhui);
		
		ArrayList<String> changning = new ArrayList<String>();
		changning.add("全锟斤拷锟斤拷锟斤拷");
		changning.add("锟斤拷锟斤拷锟斤拷");
		changning.add("锟斤拷山");
		changning.add("锟较猴拷影锟斤拷/锟铰伙拷路");
		changning.add("锟斤拷山锟斤拷园");
		changning.add("锟脚憋拷");
		data.add(changning);
		
		ArrayList<String> yangpu = new ArrayList<String>();
		yangpu.add("全锟斤拷锟斤拷锟斤拷");
		yangpu.add("锟斤拷锟斤拷锟斤拷");
		yangpu.add("锟斤拷浅锟�");
		yangpu.add("锟截斤拷锟斤拷锟斤拷");
		yangpu.add("平锟斤拷路");
		yangpu.add("锟斤拷锟剿癸拷园");
		data.add(yangpu);
		
		ArrayList<String> qingpu = new ArrayList<String>();
		qingpu.add("全锟斤拷锟斤拷锟斤拷");
		qingpu.add("锟斤拷锟斤拷锟斤拷");
		qingpu.add("锟斤拷医锟�");
		data.add(qingpu);
		
		ArrayList<String> songjiang = new ArrayList<String>();
		songjiang.add("全锟斤拷锟斤拷锟斤拷");
		songjiang.add("锟缴斤拷锟斤拷");
		songjiang.add("锟缴斤拷锟斤拷");
		songjiang.add("锟斤拷亭");
		songjiang.add("锟斤拷山");
		songjiang.add("锟缴斤拷锟斤拷学锟斤拷");
		data.add(songjiang);
		
		ArrayList<String> baoshan = new ArrayList<String>();
		baoshan.add("全锟斤拷锟斤拷锟斤拷");
		baoshan.add("锟斤拷山锟斤拷");
		baoshan.add("锟襟华达拷锟斤拷");
		baoshan.add("锟斤拷锟斤拷锟斤拷");
		baoshan.add("锟斤拷锟斤拷");
		baoshan.add("锟较猴拷锟斤拷学");
		data.add(baoshan);
		
		ArrayList<String> pudong = new ArrayList<String>();
		pudong.add("全锟斤拷锟斤拷锟斤拷");
		pudong.add("锟斤拷锟斤拷/锟斤拷锟斤拷");
		pudong.add("陆锟斤拷锟斤拷");
		pudong.add("锟斤拷锟酵癸拷园");
		pudong.add("锟剿佰帮拷");
		data.add(pudong);
		
		return data;
	}
}
