package com.goparty.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventListItemView extends RelativeLayout {
	private ImageView imageView;
	private TextView titleView;
	private TextView datetimeView;
	private TextView contentView;
	
	public EventListItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public EventListItemView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        inflater.inflate(R.layout.event_list_item, this);  
        imageView = (ImageView)findViewById(R.id.eventImage);
        titleView = (TextView)findViewById(R.id.eventTitle);
        datetimeView = (TextView)findViewById(R.id.eventDatetime);
        contentView = (TextView)findViewById(R.id.eventContent);
//        imageView=(ImageView) findViewById(R.id.imageView1);  
//        textView=(TextView)findViewById(R.id.textView1);      
    }
	
	public void setImageResource(int resId) {    
		imageView.setImageResource(resId);    
	}
	
	public void setTitleViewText(String title) {    
		titleView.setText(title);    
	}
	
	public void setDatetimeViewText(String time) {    
		datetimeView.setText(time);    
	}
	
	public void setContentViewText(String content) {    
		contentView.setText(content);    
	}
	
//	private ImageView imageView;  
//    private TextView  textView;  
      
//    public ImageBtn(Context context) {  
//        super(context);  
//        // TODO Auto-generated constructor stub  
//    }  
//    public ImageBtn(Context context, AttributeSet attrs) {  
//        super(context, attrs);  
//        // TODO Auto-generated constructor stub  
//        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
//        inflater.inflate(R.layout.imagebtn, this);  
//        imageView=(ImageView) findViewById(R.id.imageView1);  
//        textView=(TextView)findViewById(R.id.textView1);  
//    }  
//      
//    /**   
//     * 设置图片资源   
//     */    
//    public void setImageResource(int resId) {    
//        imageView.setImageResource(resId);    
//    }    
//    
//    /**   
//     * 设置显示的文字   
//     */    
//    public void setTextViewText(String text) {    
//        textView.setText(text);    
//    }    

}