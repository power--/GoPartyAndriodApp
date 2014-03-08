package com.goparty.app;

import com.goparty.app.R;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;

public class SimpleMainActivity extends Activity implements OnClickListener {

	private HomeFragment homeFragment;
	private ContactsFragment contactFragment;
	private EventsFragment eventFragment;
	private SettingFragment settingFragment;

	private View homeLayout;
	private View contactLayout;
	private View eventLayout;
	private View settingLayout;

	private ImageView homeImage;
	private ImageView contactImage;
	private ImageView eventImage;
	private ImageView settingImage;

	private TextView homeText;
	private TextView contactText;
	private TextView eventText;
	private TextView settingText;

	private ImageView homeBgImage;
	private ImageView contactBgImage;
	private ImageView eventBgImage;
	private ImageView settingBgImage;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simple_main);
		// 初始化布局元素
		initViews();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		homeLayout = findViewById(R.id.home_layout);
		homeImage = (ImageView) findViewById(R.id.home_image);
		homeText = (TextView) findViewById(R.id.home_text);
		homeBgImage = (ImageView) findViewById(R.id.home_bg_image);
		homeLayout.setOnClickListener(this);
		
		contactLayout = findViewById(R.id.contact_layout);
		contactImage = (ImageView) findViewById(R.id.contact_image);
		contactText = (TextView) findViewById(R.id.contact_text);
		contactBgImage = (ImageView) findViewById(R.id.contact_bg_image);
		contactLayout.setOnClickListener(this);
		
		eventLayout = findViewById(R.id.event_layout);
		eventImage = (ImageView) findViewById(R.id.event_image);
		eventText = (TextView) findViewById(R.id.event_text);
		eventBgImage = (ImageView) findViewById(R.id.event_bg_image);
		eventLayout.setOnClickListener(this);
		
		settingLayout = findViewById(R.id.setting_layout);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		settingText = (TextView) findViewById(R.id.setting_text);
		settingBgImage = (ImageView) findViewById(R.id.setting_bg_image);
		settingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_layout:
			// 当点击了消息tab时，选中第1个tab
			setTabSelection(0);
			break;
		case R.id.contact_layout:
			// 当点击了联系人tab时，选中第2个tab
			setTabSelection(1);
			break;
		case R.id.event_layout:
			// 当点击了动态tab时，选中第3个tab
			setTabSelection(2);
			break;
		case R.id.setting_layout:
			// 当点击了设置tab时，选中第4个tab
			setTabSelection(3);
			break;
		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			homeImage.setImageResource(R.drawable.goparty_home_selected);
			homeText.setTextColor(Color.WHITE);
			homeBgImage.setVisibility(ImageView.VISIBLE);
			if (homeFragment == null) {
				// 如果homeFragment为空，则创建一个并添加到界面上
				homeFragment = new HomeFragment();
				transaction.add(R.id.content, homeFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(homeFragment);
			}
			break;
		case 1:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			contactImage.setImageResource(R.drawable.goparty_contact_selected);
			contactText.setTextColor(Color.WHITE);
			contactBgImage.setVisibility(ImageView.VISIBLE);
			if (contactFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				contactFragment = new ContactsFragment();
				transaction.add(R.id.content, contactFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(contactFragment);
			}
			break;
		case 2:
			// 当点击了动态tab时，改变控件的图片和文字颜色
			eventImage.setImageResource(R.drawable.goparty_event_selected);
			eventText.setTextColor(Color.WHITE);
			eventBgImage.setVisibility(ImageView.VISIBLE);
			if (eventFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				eventFragment = new EventsFragment();
				transaction.add(R.id.content, eventFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(eventFragment);
			}
			break;
		case 3:
		default:
			// 当点击了设置tab时，改变控件的图片和文字颜色
			settingImage.setImageResource(R.drawable.goparty_setting_selected);
			settingText.setTextColor(Color.WHITE);
			settingBgImage.setVisibility(ImageView.VISIBLE);
			if (settingFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		homeImage.setImageResource(R.drawable.goparty_home);
		homeText.setTextColor(Color.parseColor("#82858b"));
		homeBgImage.setVisibility(ImageView.INVISIBLE);
		
		contactImage.setImageResource(R.drawable.goparty_contact);
		contactText.setTextColor(Color.parseColor("#82858b"));
		contactBgImage.setVisibility(ImageView.INVISIBLE);
		
		eventImage.setImageResource(R.drawable.goparty_event);
		eventText.setTextColor(Color.parseColor("#82858b"));
		eventBgImage.setVisibility(ImageView.INVISIBLE);
		
		settingImage.setImageResource(R.drawable.goparty_setting);
		settingText.setTextColor(Color.parseColor("#82858b"));
		settingBgImage.setVisibility(ImageView.INVISIBLE);
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (contactFragment != null) {
			transaction.hide(contactFragment);
		}
		if (eventFragment != null) {
			transaction.hide(eventFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}
}
