package com.goparty.app.settings;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class SettingsView extends View {
	  public SettingsView(Context context) {  
	        super(context);  
	    }  
	  
	    public SettingsView(Context context, AttributeSet attrs, int defStyle) {  
	        super(context, attrs, defStyle);  
	    }  
	  
	    public SettingsView(Context context, AttributeSet attrs) {  
	        super(context, attrs);
	        //LayoutInflater.from(context).inflate(R.layout.main_tab_settings, this, true);
	    }
	    
//	    public ImageBt(Context context, AttributeSet attrs) {  
//	        super(context, attrs);  
//	        // ���벼��  
//	        LayoutInflater.from(context).inflate(R.layout.custombt, this, true);  
//	        iv = (ImageView) findViewById(R.id.iv);  
//	        tv = (TextView) findViewById(R.id.tv);  
//	  
//	    }  
	      
//	    @Override  
//	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
//	        int measuredHeight = measureHeight(heightMeasureSpec);  
//	        int measuredWidth = measureWidth(widthMeasureSpec);  
//	        //Must make this call to setMeasuredDimension   
//	        //or you will cause a runtime exception when the control is laid out   
//	        setMeasuredDimension(measuredWidth, measuredHeight);  
//	    }  
//	  
//	    private int measureWidth(int widthMeasureSpec) {  
//	        int specMode = MeasureSpec.getMode(widthMeasureSpec);  
//	        int specSize = MeasureSpec.getSize(widthMeasureSpec);  
//	        int result = 500;  
//	        if(specMode == MeasureSpec.AT_MOST){  
//	            //calculate the ideal size of your control within this maximum size.   
//	            //if your control fills the available  space return the outer bound.   
//	            result = specSize;  
//	        }else if(specMode == MeasureSpec.EXACTLY){  
//	            //if your control can fit within these bounds return that value   
//	            result = specSize;  
//	        }  
//	        return result;  
//	    }  
//	  
//	    private int measureHeight(int heightMeasureSpec) {  
//	        int specMode = MeasureSpec.getMode(heightMeasureSpec);  
//	        int specSize = MeasureSpec.getSize(heightMeasureSpec);  
//	        int result = 500;  
//	        if(specMode == MeasureSpec.AT_MOST){  
//	            result = specSize;  
//	        }else if(specMode == MeasureSpec.EXACTLY){  
//	            result = specSize;  
//	        }  
//	        return result;  
//	    }  
//	      
//	    @Override  
//	    protected void onDraw(Canvas canvas) {  
//	        //get the size of the control based on the last call to onMeasure()   
//	        int height = getMeasuredHeight();  
//	        int width = getMeasuredWidth();  
//	          
//	        //Find the center   
//	        int px = width/2;  
//	        int py = height/2;    
//	          
//	        //create the new paint brushes   
//	        //note:For efficiency this should be done in the view's constructor   
//	        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
//	        mTextPaint.setColor(Color.WHITE);  
//	          
//	        //define the string   
//	        String displayText = "Hello World!";  
//	          
//	        //measure the width of the string   
//	        float textWidth = mTextPaint.measureText(displayText);  
//	          
//	        canvas.drawText(displayText, px - textWidth/2, py, mTextPaint);  
//	        super.onDraw(canvas);  
//	    }  
}
