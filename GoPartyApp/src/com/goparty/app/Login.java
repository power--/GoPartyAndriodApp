package com.goparty.app;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
//	private EditText mUser; // �ʺű༭��
//	private EditText mPassword; // ����༭��

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
//        mUser = (EditText)findViewById(R.id.login_user_edit);
//        mPassword = (EditText)findViewById(R.id.login_passwd_edit);     
    }
    
    private void initFootBar() {
//		fbNews = (RadioButton) findViewById(R.id.main_footbar_news);
//		fbQuestion = (RadioButton) findViewById(R.id.main_footbar_question);
//		fbTweet = (RadioButton) findViewById(R.id.main_footbar_tweet);
//		fbactive = (RadioButton) findViewById(R.id.main_footbar_active);
//
//		fbSetting = (ImageView) findViewById(R.id.main_footbar_setting);
//		fbSetting.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// 展示快捷栏&判断是否登录&是否加载文章图片
//				UIHelper.showSettingLoginOrLogout(Main.this,
//						mGrid.getQuickAction(0));
//				mGrid.show(v);
//			}
//		});
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
    
    public void go_to_home_clicked(View v) {
    	Intent intent = new Intent(Login.this,MainWeixin.class); 
    	startActivity(intent);
    	overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
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
