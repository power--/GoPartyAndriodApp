package com.goparty.widget;

import com.goparty.app.R;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class FilterBar extends LinearLayout
{
  final View.OnClickListener handler = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (FilterBar.this.listener != null)
        FilterBar.this.listener.onClickItem(paramAnonymousView.getTag(), paramAnonymousView);
    }
  };
  OnItemClickListener listener;

  public FilterBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public FilterBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBackgroundResource(R.drawable.filter_item_bg);
    setOrientation(android.widget.LinearLayout.HORIZONTAL);
  }

  public void addItem(Object paramObject, String paramString)
  {
    if (getChildCount() > 0)
    {
      ImageView localImageView = new ImageView(getContext());
      localImageView.setImageDrawable(getResources().getDrawable(R.drawable.filter_bar_divider));
      localImageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
      addView(localImageView);
    }
    View localView = LayoutInflater.from(getContext()).inflate(R.layout.filter_bar_item, this, false);
    localView.setTag(paramObject);
    localView.setOnClickListener(this.handler);
    addView(localView);
    setItem(paramObject, paramString);
  }

  public void setItem(Object paramObject, String paramString)
  {
    View localView = findViewWithTag(paramObject);
    if (localView == null)
      return;
    ((TextView)localView.findViewById(android.R.id.text1)).setText(paramString);
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.listener = paramOnItemClickListener;
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void onClickItem(Object paramObject, View paramView);
  }
}