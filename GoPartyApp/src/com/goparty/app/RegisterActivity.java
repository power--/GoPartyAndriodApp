package com.goparty.app;

import com.goparty.biz.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText nicknameEditText;
	private TextView usernameTextView;
	private TextView nicknameTextView;
	private TextView passwordTextView;
	private TextView backendTextview;
	String userName;
	String nickName;
	String password;
	
	boolean hideKeyboard = false;
	
	UserService service;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        usernameEditText = (EditText)findViewById(R.id.username_editbox);
        nicknameEditText = (EditText)findViewById(R.id.nickname_editbox);
        passwordEditText = (EditText)findViewById(R.id.password_editbox);
        
    	usernameTextView = (TextView)findViewById(R.id.userName_textview);
    	nicknameTextView = (TextView)findViewById(R.id.nickName_textview);
    	passwordTextView = (TextView)findViewById(R.id.password_textview);
    	backendTextview = (TextView)findViewById(R.id.backend_textview);
    }
	
	public void register_onClick(View v) {
		userName = usernameEditText.getText().toString();
		nickName = nicknameEditText.getText().toString();
		password = passwordEditText.getText().toString();
		
		usernameTextView.setText("");
		nicknameTextView.setText("");
		passwordTextView.setText("");
		
		hideKeyboard = false;
		service = new UserService();
		if (!service.ValidateUserName(userName)) {
			usernameTextView.setText("用户名不得少于三个字符。");
			hideKeyboard = true;
		} else if (!service.ValidateNickName(nickName)) {
			nicknameTextView.setText("昵称不得少于三个字符。");
			hideKeyboard = true;
		} else if (!service.ValidatePassword(password)) {
			passwordTextView.setText("密码不得少于三个字符");
			hideKeyboard = true;
		} else {		
			new Thread(new Runnable() {  
		         public void run() {
			 		boolean result = service.Create(nickName, password, userName);
					if (result) {
						Intent intent = new Intent();
			            intent.setClass(RegisterActivity.this, MainWeixin.class);
			            startActivity(intent);
					} else {
						backendTextview.setText("Error");
						hideKeyboard = true;
					}
				}  
	        }).start();  
		}
		
		if (hideKeyboard) {
			backendTextview.setVisibility(TextView.VISIBLE);
			InputMethodManager mInputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
			mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	public void register_back(View v) {     //标题栏 返回按钮
      	this.finish();
      }  
	
//	private class SubAsyncTask extends AsyncTask {
//		ProgressDialog mProgressDialog;
//		
//		@Override
//		protected Object doInBackground(Object... arg0) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		@Override
//		protected void onPreExecute() {
//			mProgressDialog = ProgressDialog.show(RegisterActivity.this, "load data", "loading");
//		}
//		
//		@Override
//		protected void onPostExecute(Void result) {
//			mProgressDialog.dismiss();
//		}
//		
//		@Override
//		protected void onProgressUpdate(String... values) {
//			super.onProgressUpdate(values);
//		    Toast.makeText(getBaseContext(), values[0], Toast.LENGTH_SHORT).show();
//		}
//		
//	}
}
