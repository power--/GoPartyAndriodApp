package com.goparty.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.goparty.app.R;

public class ContactFace extends ImageView {

	private static Drawable DRAWABLE00;
	
	public ContactFace(Context context) {
		super(context);
	}

	public ContactFace(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ContactFace(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setFaceUri(String faceUri) {
		if (DRAWABLE00 == null) {
			DRAWABLE00 = getResources().getDrawable(R.drawable.goparty_face_default);
		}
		setImageDrawable(DRAWABLE00);
	}
}
