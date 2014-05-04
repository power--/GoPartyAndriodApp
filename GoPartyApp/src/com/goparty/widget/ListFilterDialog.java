package com.goparty.widget;

import com.goparty.app.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListFilterDialog extends FilterDialog
{
  Adapter adapter = new Adapter();
  String countFieldName = "Count";
  String fieldName = "Name";
  final AdapterView.OnItemClickListener handler = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      if (ListFilterDialog.this.listener != null)
      {
    	FilterDataItem localDPObject = ListFilterDialog.this.adapter.getItem(paramAnonymousInt);
        ListFilterDialog.this.listener.onFilter(ListFilterDialog.this, localDPObject);
      }
    }
  };
  FilterDataItem headerItem;
  FilterDataItem[] items;
  ListView list = (ListView)getLayoutInflater().inflate(R.layout.list_filter, getFilterViewParent(), false);
  FilterDataItem selectedItem;
  boolean showCount;

  public ListFilterDialog(Activity paramActivity)
  {
    super(paramActivity);
    this.list.setAdapter(this.adapter);
    this.list.setOnItemClickListener(this.handler);
    setFilterView(this.list); 
  }

  public ListFilterDialog(Activity paramActivity, boolean paramBoolean)
  {
    this(paramActivity);
    this.showCount = paramBoolean;
  }

  public FilterDataItem getHeaderItem()
  {
    return this.headerItem;
  }

  public FilterDataItem[] getItems()
  {
    return this.items;
  }

  public FilterDataItem getSelectedItem()
  {
    return this.selectedItem;
  }

  public void setHeaderItem(FilterDataItem paramDPObject)
  {
    this.headerItem = paramDPObject;
    this.adapter.notifyDataSetChanged();
  }

  public void setItems(FilterDataItem[] paramArrayOfDPObject)
  {
    this.items = paramArrayOfDPObject;
    this.adapter.notifyDataSetChanged();
  }

  public void setSelectedItem(FilterDataItem paramDPObject)
  {
    this.selectedItem = paramDPObject;
    this.adapter.notifyDataSetChanged();
  }

  class Adapter extends BaseAdapter
  {
    Adapter()
    {
    }

    public int getCount()
    {
      if (ListFilterDialog.this.headerItem == null)
      {
    	  return 0;
      }
      
      return ListFilterDialog.this.items.length;
     
    }

    public FilterDataItem getItem(int paramInt)
    {
      if (ListFilterDialog.this.headerItem == null)
        return ListFilterDialog.this.items[paramInt];
      if (paramInt == 0)
        return ListFilterDialog.this.headerItem;
      return ListFilterDialog.this.items[(paramInt - 1)];
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = ListFilterDialog.this.getLayoutInflater().inflate(R.layout.filter_item, paramViewGroup, false);
      TextView localTextView1 = (TextView)paramView.findViewById(android.R.id.text1);
      TextView localTextView2 = (TextView)paramView.findViewById(android.R.id.text2);
      FilterDataItem aDataItem = getItem(paramInt);
      localTextView1.setText(aDataItem.getCategoryName());
      return paramView;
  }
}
}