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
		quanbu.add("ȫ������");
		quanbu.add("ȫ������ ");
		data.add(quanbu);
		
		ArrayList<String> hongkou = new ArrayList<String>();
		hongkou.add("ȫ������");
		hongkou.add("�����");
		hongkou.add("����·/����·");
		hongkou.add("��ƽ·/��ƽ��԰");
		hongkou.add("��������");
		hongkou.add("�Ĵ���·");
		hongkou.add("³Ѹ��԰");
		data.add(hongkou);
		
		ArrayList<String> zhabei = new ArrayList<String>();
		zhabei.add("ȫ������");
		zhabei.add("բ����");
		zhabei.add("��������");
		zhabei.add("������վ");
		zhabei.add("��վ");
		zhabei.add("բ����԰");
		data.add(zhabei);
		
		ArrayList<String> xuhui = new ArrayList<String>();
		xuhui.add("ȫ������");
		xuhui.add("�����");
		xuhui.add("��ɽ·");
		xuhui.add("����ѧԺ");
		xuhui.add("�ؼ��·����");
		xuhui.add("�����/����");
		data.add(xuhui);
		
		ArrayList<String> changning = new ArrayList<String>();
		changning.add("ȫ������");
		changning.add("������");
		changning.add("��ɽ");
		changning.add("�Ϻ�Ӱ��/�»�·");
		changning.add("��ɽ��԰");
		changning.add("�ű�");
		data.add(changning);
		
		ArrayList<String> yangpu = new ArrayList<String>();
		yangpu.add("ȫ������");
		yangpu.add("������");
		yangpu.add("��ǳ�");
		yangpu.add("�ؽ�����");
		yangpu.add("ƽ��·");
		yangpu.add("���˹�԰");
		data.add(yangpu);
		
		ArrayList<String> qingpu = new ArrayList<String>();
		qingpu.add("ȫ������");
		qingpu.add("������");
		qingpu.add("��ҽ�");
		data.add(qingpu);
		
		ArrayList<String> songjiang = new ArrayList<String>();
		songjiang.add("ȫ������");
		songjiang.add("�ɽ���");
		songjiang.add("�ɽ���");
		songjiang.add("��ͤ");
		songjiang.add("��ɽ");
		songjiang.add("�ɽ���ѧ��");
		data.add(songjiang);
		
		ArrayList<String> baoshan = new ArrayList<String>();
		baoshan.add("ȫ������");
		baoshan.add("��ɽ��");
		baoshan.add("�󻪴���");
		baoshan.add("������");
		baoshan.add("����");
		baoshan.add("�Ϻ���ѧ");
		data.add(baoshan);
		
		ArrayList<String> pudong = new ArrayList<String>();
		pudong.add("ȫ������");
		pudong.add("����/����");
		pudong.add("½����");
		pudong.add("���͹�԰");
		pudong.add("�˰۰�");
		data.add(pudong);
		
		return data;
	}
}
