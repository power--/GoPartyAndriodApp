package com.goparty.widget;

import com.goparty.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CommonListItem extends LinearLayout
{
	private ContactFace contactFace;
	private TextView nameView;
	private TextView noteView;
	private ImageView detailsView;
//	private ImageView checkView;
	private ImageView addView;

	private boolean isSelectableView;
	//private boolean isSelected;
	
	public CommonListItem(Context context) {
		super(context);
	}

	public CommonListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onFinishInflate() {
		super.onFinishInflate();
		contactFace = (ContactFace) findViewById(R.id.contactFace);
		nameView = (TextView) findViewById(R.id.common_list_item_name);
		noteView = (TextView) findViewById(R.id.common_list_item_note);
		detailsView = (ImageView) findViewById(R.id.common_list_item_details);
//		checkView = (ImageView) findViewById(R.id.common_list_item_check);
		addView = (ImageView) findViewById(R.id.common_list_item_add);
	}
			
	public void setContactItemData(String faceUri, String name, String note) {
		contactFace.setFaceUri(faceUri);
		nameView.setText(name);
		noteView.setText(note);

		if (!isSelectableView) {
//			checkView.setVisibility(View.GONE);
			addView.setVisibility(View.GONE);
		} else {
			detailsView.setVisibility(View.GONE);
			//setCheckViewBg(isSelected);
			
//			checkView.setVisibility(View.INVISIBLE);
//			addView.setVisibility(View.VISIBLE);
		}
	}

	public boolean isSelectable() {
		return isSelectableView;
	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectableView = isSelectable;
	}
	
/*	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {		
		this.isSelected = isSelected;
		//setCheckViewBg(isSelected);
	}
	
	private void setCheckViewBg(boolean isSelected) {
		if (isSelected) {
			checkView.setBackgroundResource(R.drawable.goparty_v2_btn_checked_bg);
		} else {
			checkView.setBackgroundResource(R.drawable.goparty_v2_btn_add_bg);
		}
	}*/
	
/*	public void setDistanceText(String dis)
	{
		this.distance.setText(dis);
	}*/
}