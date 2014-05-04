package com.goparty.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventDetailsActivity extends FragmentActivity {
	private FragmentManager fragmentManager;
	
	private EventDetailsMainFragment mainFragment;
	private EventDetailsChatFragment chatFragment;
	private EventDetailsShareFragment shareFragment;
	
	private String mockedEventId = "31";
//
//	private View mainLayout;
//	private View chatLayout;
//	private View shareLayout;
//
//	private ImageView mainImage;
//	private ImageView chatImage;
//	private ImageView shareImage;

	private TextView mainText;
	private TextView chatText;
	private TextView shareText;

//	private ImageView mainBgImage;
//	private ImageView chatBgImage;
//	private ImageView shareBgImage;
	
	Drawable navDrawable;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.event_details_layout);
		navDrawable = getResources().getDrawable(R.drawable.goparty_event_details_nav_bg);
		navDrawable.setBounds(0, 0, navDrawable.getMinimumWidth(), navDrawable.getMinimumHeight());
		
		initViews();
		fragmentManager = getFragmentManager();
		setTabSelection(0);
	}
	
	private void initViews() {
		NavMenuOnClickListener listener = new NavMenuOnClickListener();
		mainText = (TextView) findViewById(R.id.nav_main_text);
		mainText.setOnClickListener(listener);
		
		chatText = (TextView) findViewById(R.id.nav_chat_text);
		chatText.setOnClickListener(listener);
		
		shareText = (TextView) findViewById(R.id.nav_share_text);
		shareText.setOnClickListener(listener);
		
		Button returnBtn = (Button) findViewById(R.id.btn_nav_event_details_back);
		returnBtn.setOnClickListener(listener);
	}

	private class NavMenuOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.nav_main_text:
				setTabSelection(0);
				break;
			case R.id.nav_chat_text:
				setTabSelection(1);
				break;
			case R.id.nav_share_text:
				setTabSelection(2);
				break;
			case R.id.btn_nav_event_details_back:
				finish();
			default:
				break;
			}
		}
	}
	
	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		
		Bundle nBundle = new Bundle();  
		nBundle.putString("eventId", mockedEventId);  
		
		switch (index) {
			case 0:
				mainText.setCompoundDrawables(null, null, null, navDrawable);
				if (mainFragment == null) {
					mainFragment = EventDetailsMainFragment.getInstance(nBundle);
					transaction.add(R.id.event_details_panel, mainFragment);
				} else {
					transaction.show(mainFragment);
				}
				break;
			case 1:
				chatText.setCompoundDrawables(null, null, null, navDrawable);
				if (chatFragment == null) {
					chatFragment = EventDetailsChatFragment.getInstance(nBundle); 
					transaction.add(R.id.event_details_panel, chatFragment);
				} else {
					transaction.show(chatFragment);
				}
				break;
			case 2:
				shareText.setCompoundDrawables(null, null, null, navDrawable);
				if (shareFragment == null) {
					shareFragment = EventDetailsShareFragment.getInstance(nBundle);
					transaction.add(R.id.event_details_panel, shareFragment);
				} else {
					transaction.show(shareFragment);
				}
				break;
		}
		
		transaction.commit();
	}

	private void clearSelection() {
		mainText.setCompoundDrawables(null, null, null, null);
		chatText.setCompoundDrawables(null, null, null, null);
		shareText.setCompoundDrawables(null, null, null, null);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mainFragment != null) {
			transaction.hide(mainFragment);
		}
		if (chatFragment != null) {
			transaction.hide(chatFragment);
		}
		if (shareFragment != null) {
			transaction.hide(shareFragment);
		}
	}
}

/*
package com.goparty.app;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventDetailsActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.event_details_layout);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		//actionBar.setDisplayShowCustomEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		
		Tab first = actionBar.newTab().setText(R.string.details);
		Tab second = actionBar.newTab().setText(R.string.chat);
		
//		first.setIcon(R.drawable.ic_launcher);
//		second.setIcon(R.drawable.ic_launcher);
		
		myTabListener fitstTab = new myTabListener(new EventDetailsMainFragment());
		myTabListener secondTab = new myTabListener(new EventDetailsChatFragment());
		
		first.setTabListener(fitstTab);
		second.setTabListener(secondTab);
		
		actionBar.addTab(first);
		actionBar.addTab(second);
		
		 Button btnNavBack = (Button) findViewById(R.id.btn_event_details_back);
		 btnNavBack.setOnClickListener(new ButtonOnClickListener());
    }
    
    private class ButtonOnClickListener implements OnClickListener {
    	@Override
    	public void onClick(View v) {
    			finish();
    	}
    }
    
	class myTabListener implements TabListener {
		private Fragment fragment;

		public myTabListener(Fragment ft) {
			fragment = ft;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_replace, fragment, null);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}
	}

}
*/
/*package com.goparty.app;

import android.app.TabActivity;  
import android.content.Intent;  
import android.content.res.Resources;  
import android.os.Bundle;  
import android.widget.TabHost;  
import android.widget.TabHost.TabSpec;  
   
public class EventDetailsActivity extends TabActivity {  
    *//** Called when the activity is first created. *//* 
    @Override 
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);//这里使用了上面创建的xml文件（Tab页面的布局）  
        Resources res = getResources(); // Resource object to get Drawables  
        TabHost tabHost = getTabHost();  // The activity TabHost  
        TabSpec spec;  
        Intent intent;  // Reusable Intent for each tab  
   
      //第一个Tab  
        intent = new Intent(this,FirstActivity.class);//新建一个Intent用作Tab1显示的内容  
        spec = tabHost.newTabSpec("tab1")//新建一个 Tab  
        .setIndicator("Tab1", res.getDrawable(android.R.drawable.ic_media_play))//设置名称以及图标  
        .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xxx  
        tabHost.addTab(spec);//添加进tabHost  
   
        //第二个Tab  
        intent = new Intent(this,SecondActivity.class);//第二个Intent用作Tab1显示的内容  
        spec = tabHost.newTabSpec("tab2")//新建一个 Tab  
        .setIndicator("Tab2", res.getDrawable(android.R.drawable.ic_menu_edit))//设置名称以及图标  
        .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xxx  
        tabHost.addTab(spec);//添加进tabHost  
          
       //第三个Tab  
        intent = new Intent(this,SecondActivity.class);//第二个Intent用作Tab1显示的内容  
        spec = tabHost.newTabSpec("tab2")//新建一个 Tab  
        .setIndicator("Tab3", res.getDrawable(android.R.drawable.ic_menu_help))//设置名称以及图标  
        .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xxx  
        tabHost.addTab(spec);//添加进tabHost  
   
        tabHost.setCurrentTab(0);//设置当前的选项卡,这里为Tab1  
    }   
}
*/