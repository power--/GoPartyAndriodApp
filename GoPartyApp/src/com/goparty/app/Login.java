package com.goparty.app;

import com.goparty.biz.AuthService;
import com.goparty.model.AuthInfo;
import com.goparty.net.JsonHttpClient;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login extends Activity {
private Button loginButton;
private EditText messageCodeText;
private EditText mobileText;
private boolean mobileStep1 = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        
        RadioButton mobileButton =(RadioButton) this.findViewById(R.id.login_mobile_radiobtn);
        mobileButton.setChecked(true);
        
        RadioButton qqButton =(RadioButton) this.findViewById(R.id.login_qq_radiobtn);
        qqButton.setEnabled(false);
        
        RadioButton weChatButton =(RadioButton) this.findViewById(R.id.login_wechat_radiobtn);
        weChatButton.setEnabled(false);
        
        mobileText=(EditText) this.findViewById(R.id.login_user_edit);
        messageCodeText =(EditText) this.findViewById(R.id.login_message_code);
        loginButton =(Button) this.findViewById(R.id.login_mobile_login_btn);
        
        InitLoginButton();
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	login_clicked(v);
            }
        });
            
        }
    
    private void InitLoginButton()
    {
        if(mobileStep1)
        {
        	loginButton.setText(R.string.message_code_btn);
        	messageCodeText.setVisibility(View.INVISIBLE);
        }else
        {
        	loginButton.setText(R.string.login);
        	messageCodeText.setVisibility(View.VISIBLE);
        }
        
    }
    
//
//    public void login_mainweixin(View v) {
//    	if("buaa".equals(mUser.getText().toString()) && "123".equals(mPassword.getText().toString()))   //�ж� �ʺź�����
//        {
//             Intent intent = new Intent();
//             intent.setClass(Login.this,LoadingActivity.class);
//             startActivity(intent);
//             //Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
//          }
//        else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //�ж� �ʺź�����
//        {
//        	new AlertDialog.Builder(Login.this)
//			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
//			.setTitle("��¼����")
//			.setMessage("΢���ʺŻ������벻��Ϊ�գ�\n��������ٵ�¼��")
//			.create().show();
//         }
//        else{
//           
//        	new AlertDialog.Builder(Login.this)
//			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
//			.setTitle("��¼ʧ��")
//			.setMessage("΢���ʺŻ������벻��ȷ��\n������������룡")
//			.create().show();
//        }
//    	
//    	//��¼��ť
//    	/*
//      	Intent intent = new Intent();
//		intent.setClass(Login.this,Whatsnew.class);
//		startActivity(intent);
//		Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
//		this.finish();*/
//      }  
//    public void login_back(View v) {     //������ ���ذ�ť
//      	this.finish();
//      }  
//    public void login_pw(View v) {     //������밴ť
//    	Uri uri = Uri.parse("http://3g.qq.com"); 
//    	Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
//    	startActivity(intent);
//    	//Intent intent = new Intent();
//    	//intent.setClass(Login.this,Whatsnew.class);
//        //startActivity(intent);
//      }
    
    public void login_clicked(View v) {
    	
    	if(mobileStep1)
    	{
    		if(mobileText.getText().length() > 0)
    		{
    			mobileStep1 = false;
    			InitLoginButton();
    		}
    		
    	}else
    	{
    		if(messageCodeText.getText().length() <= 0 )
    		{
    			return;
    		}
    		
    		AuthInfo aInfo = new AuthInfo();
    		aInfo.setLoginName(mobileText.getText().toString());
    		aInfo.setPassword(messageCodeText.getText().toString());
    		new AuthAsyncTask().execute(aInfo);
    	}
    }

    private class AuthAsyncTask extends AsyncTask<AuthInfo, Void, Boolean> {
        /** The system calls this to perform work in a worker thread and
          * delivers it the parameters given to AsyncTask.execute() */
        protected Boolean doInBackground(AuthInfo... Infos) {
            return AppContext.CurrentContext.Login(Infos[0]);
        }
        
        protected void onProgressUpdate(Integer... progress){
			
		}
        
        /** The system calls this to perform work in the UI thread and delivers
          * the result from doInBackground() */
        protected void onPostExecute(Boolean result) {
            if(result)
            {
            	Intent intent = new Intent(Login.this,SimpleMainActivity.class); 
            	startActivity(intent);
            	
            }else
            {
            	
            }
        }
    }
    
//    public class LeftRightSlideActivity extends Activity {
//    	    @Override
//    	    public void onCreate(Bundle savedInstanceState) {
//    	        super.onCreate(savedInstanceState);
//    	        setContentView(R.layout.main);  
//
//    	        Button button = (Button)findViewById(R.id.button1);
//    	        button.setOnClickListener(new View.OnClickListener() { 
//    	            @Override
//    	            public void onClick(View v) {
//    	                Intent intent = new Intent();
//    	                intent.setClass(LeftRightSlideActivity.this, SlideSecondActivity.class);
//    	                startActivity(intent);
//    	                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);               
//    	            }
//    	        });
//    	    }
//    	}
}
