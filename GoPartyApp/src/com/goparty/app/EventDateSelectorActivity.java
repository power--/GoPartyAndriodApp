package com.goparty.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.goparty.app.common.ActivityConst;
import com.goparty.datetimepicker.JudgeDate;
import com.goparty.datetimepicker.ScreenInfo;
import com.goparty.datetimepicker.WheelMain;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

public class EventDateSelectorActivity  extends Activity {
	WheelMain wheelMain;
	
	TextView tvStartTitle;
	TextView tvStartDate;
	TextView tvStartDay;
	TextView tvStartTime;
	
	TextView tvEndTitle;
	TextView tvEndDate;
	TextView tvEndDay;
	TextView tvEndTime;
	
	Calendar startDatetime;
	Calendar endDatetime;
	
	boolean isStartDatetimeEnabled = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_selector);
        
        initDatetimes();
        initComponents();
        
        initDatetimePicker(wheelMain, startDatetime);
        
        resetDateValue(startDatetime, tvStartDate, tvStartDay, tvStartTime);
        resetStartDateStyle(true);
        
        resetDateValue(endDatetime, tvEndDate, tvEndDay, tvEndTime);
        resetEndDateStyle(false);
    }
    
    private void initDatetimes() {
    	startDatetime = Calendar.getInstance();
        endDatetime = Calendar.getInstance();
        
    	Intent intent = getIntent();
        String startString = intent.getStringExtra(ActivityConst.INVENT_ARG_EVENT_START_DATE);
        if (startString != null && !startString.equals("")) {
        	startDatetime.setTimeInMillis(Long.valueOf(startString));
        	
        	String endString = intent.getStringExtra(ActivityConst.INVENT_ARG_EVENT_END_DATE);
            if (endString != null && !endString.equals("")) {
            	endDatetime.setTimeInMillis(Long.valueOf(endString));
            }
        } else {
        	endDatetime.add(Calendar.HOUR, 3);
        }
    }
    
//    private class BackOnClick implements OnClickListener {
//    	@Override
//    	public void onClick(View v) {
//    			finish();
//    	}
//    }
//  
    private void initComponents() {
		 tvStartTitle = (TextView) findViewById(R.id.event_start_date_title);
		 tvStartDate = (TextView) findViewById(R.id.event_start_date);
		 tvStartDay = (TextView) findViewById(R.id.event_start_day);
		 tvStartTime = (TextView) findViewById(R.id.event_start_time);
		 
		 tvEndTitle = (TextView) findViewById(R.id.event_end_date_title);
		 tvEndDate = (TextView) findViewById(R.id.event_end_date);
		 tvEndDay = (TextView) findViewById(R.id.event_end_day);
		 tvEndTime = (TextView) findViewById(R.id.event_end_time);
					
		 View timepickerview = findViewById(R.id.event_datetimepicker);
		 wheelMain = new WheelMain(timepickerview, true);
		 wheelMain.screenheight = new ScreenInfo(EventDateSelectorActivity.this).getHeight();
		 
		 ButtonOnClick buttonOnClick = new ButtonOnClick();
		 Button btnNavBack = (Button) findViewById(R.id.btn_nav_event_time_back);
		 btnNavBack.setOnClickListener(buttonOnClick);
		
		 Button btnSubmit = (Button) findViewById(R.id.btn_event_datetime_submit);
		 btnSubmit.setOnClickListener(buttonOnClick);
		 
		 LinearLayout startLayout = (LinearLayout)findViewById(R.id.event_start_date_layout);
		 startLayout.setOnClickListener(buttonOnClick);
		 
		 LinearLayout endLayout = (LinearLayout)findViewById(R.id.event_end_date_layout);
		 endLayout.setOnClickListener(buttonOnClick);
    }
    
    private class ButtonOnClick implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.event_start_date_layout:
    				if (isStartDatetimeEnabled) {
    					break;
    				}
    				
    				endDatetime = ConvertTo(wheelMain.getTime());
    				resetDateValue(endDatetime, tvEndDate, tvEndDay, tvEndTime);
    				
    				initDatetimePicker(wheelMain, startDatetime);
    				
    				isStartDatetimeEnabled = true;
    				resetStartDateStyle(true);
    				resetEndDateStyle(false);
    				    				
    				break;
    				
    			case R.id.event_end_date_layout:
    				if (!isStartDatetimeEnabled) {
    					break;
    				}
    				
    				startDatetime = ConvertTo(wheelMain.getTime());
    				resetDateValue(startDatetime, tvStartDate, tvStartDay, tvStartTime);
    				
    				isStartDatetimeEnabled = false;
    				resetStartDateStyle(false);
    				resetEndDateStyle(true);
    				initDatetimePicker(wheelMain, endDatetime);    				
    				break;
    				
    			case R.id.btn_event_datetime_submit:
    				//ArrayList<String> selectedDateList = new ArrayList<String>();
    				//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    				long[] selectedDateList = new long[2];
    				
//    				selectedDateList.add(dateFormat.format(startDatetime.getTime()));
//    				selectedDateList.add(dateFormat.format(endDatetime.getTime()));
    				selectedDateList[0] = startDatetime.getTimeInMillis();
    				selectedDateList[1] = endDatetime.getTimeInMillis();
    				
    				if (isStartDatetimeEnabled) {
    					startDatetime = ConvertTo(wheelMain.getTime());
        				resetDateValue(startDatetime, tvStartDate, tvStartDay, tvStartTime);
    				} else {
    					endDatetime = ConvertTo(wheelMain.getTime());
        				resetDateValue(endDatetime, tvEndDate, tvEndDay, tvEndTime);
    				}
    				
    				Bundle bundle = new Bundle(); 
    		        bundle.putLongArray(ActivityConst.EVENT_DATE_IDS, selectedDateList); 
    		         
    		        setResult(RESULT_OK, getIntent().putExtras(bundle));
    		        finish();
    				break;
    			case R.id.btn_nav_event_time_back:
    				finish();
    				break;
    		}
    		
    	}
    }
    
    private void resetDateValue(
    		Calendar dateVal, 
    		TextView dateTextView,
    		TextView dayTextView,
    		TextView timeTextView) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    	
    	dateTextView.setText(dateFormat.format(dateVal.getTime()));
		
    	dayTextView.setText(String.format("%s%s", getString(R.string.day_of_week), dateVal.get(Calendar.DAY_OF_WEEK)));
		
		dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		timeTextView.setText(dateFormat.format(dateVal.getTime()));
    }
    
    private void resetStartDateStyle(boolean isActive) {
    	if (isActive) {
    		tvStartTitle.setTextColor(Color.GREEN);
    		tvStartTitle.setTextColor(Color.BLACK);
    		tvStartDate.setTextColor(Color.BLACK);
    		tvStartDay.setTextColor(Color.BLACK);
    		tvStartTime.setTextColor(Color.BLACK);
    	} else {
    		tvStartTitle.setTextColor(Color.GRAY);
    		tvStartTitle.setTextColor(Color.GRAY);
    		tvStartDate.setTextColor(Color.GRAY);
    		tvStartDay.setTextColor(Color.GRAY);
    		tvStartTime.setTextColor(Color.GRAY);
    	}
    }
    
    private void resetEndDateStyle(boolean isActive) {
    	if (isActive) {
    		tvEndTitle.setTextColor(Color.RED);
    		tvEndTitle.setTextColor(Color.BLACK);
    		tvEndDate.setTextColor(Color.BLACK);
    		tvEndDay.setTextColor(Color.BLACK);
    		tvEndTime.setTextColor(Color.BLACK);
    	} else {
    		tvEndTitle.setTextColor(Color.GRAY);
    		tvEndTitle.setTextColor(Color.GRAY);
    		tvEndDate.setTextColor(Color.GRAY);
    		tvEndDay.setTextColor(Color.GRAY);
    		tvEndTime.setTextColor(Color.GRAY);
    	}
    }
    
    private void initDatetimePicker(WheelMain wheelMain, Calendar calendar) {
//		String time = txttime.getText().toString();
//		if(JudgeDate.isDate(time, "yyyy-MM-dd")){
//			try {
//				calendar.setTime(dateFormat.parse(time));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		
		wheelMain.initDateTimePicker(year,month,day, hour, min);
    }
    
    private Calendar ConvertTo(String inputTime) {
    	Calendar cal = Calendar.getInstance();
    	if(!JudgeDate.isDate(inputTime, "yyyy-MM-dd HH:mm")){
    		return cal;
    	}
    	
		try {
			SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
			cal.setTime(datetimeFormat.parse(inputTime));
			return cal;			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return cal;
		}
    }
}
