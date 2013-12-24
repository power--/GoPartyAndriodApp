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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText nicknameEditText;
	private TextView msgTextView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        usernameEditText = (EditText)findViewById(R.id.username_editbox);
        nicknameEditText = (EditText)findViewById(R.id.nickname_editbox);
        passwordEditText = (EditText)findViewById(R.id.password_editbox);
        msgTextView = (TextView)findViewById(R.id.msg_textview);
	}
	
	public void register_onClick(View v) {		
		new Thread(new Runnable() {  
	         public void run() {UserService service = new UserService();
	 		boolean result = service.Create(nicknameEditText.getText().toString(), passwordEditText.getText().toString(), usernameEditText.getText().toString());
			if (result) {
				Intent intent = new Intent();
	            intent.setClass(RegisterActivity.this, MainWeixin.class);
	            startActivity(intent);
			} else {
				msgTextView.setText("Error");
			}}  
        }).start();  
		
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
