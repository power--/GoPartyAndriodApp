package com.goparty.widget;

import com.goparty.app.R;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FilterDialog extends Dialog
{
  private Activity activity;
  private View.OnClickListener close = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      FilterDialog.this.cancel();
    }
  };
  private View contentView;
  protected OnFilterListener listener;
  private Object tag;

  public FilterDialog(Activity paramActivity)
  {
    super(paramActivity, android.R.style.Theme_Translucent_NoTitleBar);
    this.activity = paramActivity;
    this.contentView = getLayoutInflater().inflate(R.layout.filter_dialog, null, false);
    this.contentView.findViewById(R.id.filter_top).setOnClickListener(this.close);
    this.contentView.findViewById(R.id.filter_bottom).setOnClickListener(this.close);
    setContentView(this.contentView);
  }

  public Activity getActivity()
  {
    return this.activity;
  }

  public ViewGroup getFilterViewParent()
  {
    return (ViewGroup)this.contentView.findViewById(R.id.filter_content);
  }

  public Object getTag()
  {
    return this.tag;
  }

  public void setFilterView(View paramView)
  {
    ViewGroup localViewGroup = (ViewGroup)this.contentView.findViewById(R.id.filter_content);
    localViewGroup.removeAllViews();
    ViewGroup.LayoutParams localLayoutParams = localViewGroup.getLayoutParams();
    localLayoutParams.height = paramView.getLayoutParams().height;
    localViewGroup.setLayoutParams(localLayoutParams);
    localViewGroup.addView(paramView);
  }

  public void setOnFilterListener(OnFilterListener paramOnFilterListener)
  {
    this.listener = paramOnFilterListener;
  }

  public void setTag(Object paramObject)
  {
    this.tag = paramObject;
  }

  public void show(View paramView)
  {
    Rect localRect = new Rect();
    this.activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i = localRect.top;
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    int j = arrayOfInt[0] + paramView.getWidth() / 2;
    int k = arrayOfInt[1] - i + paramView.getHeight();
    View localView1 = this.contentView.findViewById(R.id.filter_trig);
    RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)localView1.getLayoutParams();
    localLayoutParams1.leftMargin = (j - ((ImageView)localView1).getDrawable().getIntrinsicWidth() / 2);
    localView1.setLayoutParams(localLayoutParams1);
    View localView2 = this.contentView.findViewById(R.id.filter_top);
    RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)localView2.getLayoutParams();
    localLayoutParams2.height = k;
    localView2.setLayoutParams(localLayoutParams2);
    show();
  }

  public static abstract interface OnFilterListener
  {
    public abstract void onFilter(FilterDialog paramFilterDialog, Object paramObject);
  }
}