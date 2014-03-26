package com.goparty.widget;

import com.goparty.app.ContactSelectorActivity;
import com.goparty.app.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactSelectorItem extends LinearLayout
{
	ContactFace contactFace;
	TextView contactName;
	CheckBox contactCheckBox;
	Context context;

	public ContactSelectorItem(Context context) {
		super(context);
		this.context = context;
	}

	public ContactSelectorItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		contactFace = (ContactFace) findViewById(R.id.contactFace);
		contactName = (TextView) findViewById(R.id.contactName);
		contactCheckBox = (CheckBox) findViewById(R.id.contactCheckBox);
//		contactCheckBox.setOnClickListener(new CheckBoxOnClick());
	}
	
	public void setContactItemData(String faceUri, String name, boolean isSelected) {
		contactFace.setFaceUri(faceUri);
		contactName.setText(name);
		contactCheckBox.setSelected(isSelected);
	}
	
	public boolean setSelected() {
		if (contactCheckBox == null) {
			return false;
		}
		
		contactCheckBox.toggle();
		
		return contactCheckBox.isChecked();
	}
	
//	private class CheckBoxOnClick implements OnClickListener {
//    	@Override
//    	public void onClick(View v) {
//    		Toast.makeText(context, "Checkbox Click Event!!", Toast.LENGTH_SHORT).show();
//    	}
//    }
}