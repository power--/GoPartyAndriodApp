package com.goparty.adapter;

import java.util.List;

import com.goparty.app.R;
import com.goparty.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FaceGridViewAdapter extends BaseAdapter {
		private List<Contact> itemList;
		private LayoutInflater mInflater;

		public FaceGridViewAdapter(Context context, List<Contact> itemList) {
			this.itemList = itemList;
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return itemList.size();
		}

		public Object getItem(int position) {
			return itemList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			return  mInflater.inflate(R.layout.contact_face_gridview_item, null);
			
			/*View itemView;
			if (convertView == null) {
				itemView = new ImageView(context);
			} else {
				itemView = (ImageView)convertView;
			}
			
			if (DRAWABLE00 == null) {
				DRAWABLE00 = context.getResources().getDrawable(R.drawable.goparty_face_default);
			}
			
			return itemView;*/
		}

}
