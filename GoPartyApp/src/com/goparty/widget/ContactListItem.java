package com.goparty.widget;

import com.goparty.app.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListItem extends LinearLayout
{

	/*private static int CARD_PADDING = 0;
	private static int CHECKIN_PADDING = 0;
	public static float DISTANCE_FACTOR = 1.0F;
	private static int PROMO_PADDING = 0;
	private static int TUAN_PADDING = 0;
	
	View card;
	View checkin;
	Star star;
	View promo;
	TextView name;
	TextView price;
	TextView addr;
	TextView distance;
	View tuan;
	*/
	ContactFace contactFace;
	TextView contactName;
	TextView contactSignature;

	public ContactListItem(Context context) {
		super(context);
	}

	public ContactListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onFinishInflate() {
		super.onFinishInflate();
	/*	this.star = ((Star) findViewById(R.id.poistar));
		this.tuan = findViewById(R.id.tuan);
		this.promo = findViewById(R.id.promo);
		this.checkin = findViewById(R.id.checkin);
		this.card = findViewById(R.id.card);
		this.name = ((TextView) findViewById(R.id.name));
		this.price = ((TextView) findViewById(R.id.price));
		this.addr = ((TextView) findViewById(R.id.addr));
		this.distance = ((TextView) findViewById(R.id.distance));*/
		
		contactFace = (ContactFace) findViewById(R.id.contactFace);
		contactName = (TextView) findViewById(R.id.contactName);
		contactSignature = (TextView) findViewById(R.id.contactSignature);
	}
	
	public void setContactItemData(String faceUri, String name, String signature) {
		contactFace.setFaceUri(faceUri);
		contactName.setText(name);
		contactSignature.setText(signature);
	}
	
/*	public void setDistanceText(String dis)
	{
		this.distance.setText(dis);
	}*/
}