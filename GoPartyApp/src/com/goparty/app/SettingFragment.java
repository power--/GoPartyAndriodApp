package com.goparty.app;

import java.io.File;

import com.goparty.app.R;
import com.goparty.app.common.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingFragment extends Fragment {

	private View userHeadBar;
	private ImageView faceView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		.setting_layout
		View settingLayout = inflater.inflate(R.layout.settings_layout, container, false);

		userHeadBar = (View) settingLayout.findViewById(R.id.userFaceBar);
		userHeadBar.setOnClickListener(new OnClickListener()
	    {
	        @Override
	        public void onClick(View view)
	        {
	            //Log.d("Test", "onClickListener ist gestartet");
//	            Toast.makeText(getActivity().getApplicationContext(), "Hi, Button Click Event", Toast.LENGTH_LONG).show();
	            //saveInString();
	        	showFaceImageDialog();
	        }
	    });
		
	    faceView = (ImageView)settingLayout.findViewById(R.id.iv_setting_user_face);

		return settingLayout;
	}

	private final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	private final String TEMP_FACE_FILE_NAME = "userFaceTemp.jpg";
	private final String User_FACE_FILE_NAME = "userFace.png";
	private File userFaceFile;

	public void showFaceImageDialog() {
		//Try to use DialogFragment, DialogFragment is better when you use Fragments
		new AlertDialog.Builder(getActivity())
				.setTitle(getString(R.string.picture))
				.setItems(
						new String[] { getString(R.string.select_from_gallery), getString(R.string.camera) }, 
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								switch (which) {
									case 0:
										showLocalImagesSelector();
										break;
									case 1:
										showCamera();
										break;
								}
							}
				})
				.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		getActivity();
		if (resultCode == Activity.RESULT_CANCELED) {
			Toast.makeText(getActivity(), "RESULT_CANCELED",	Toast.LENGTH_LONG).show();
			return;
		}

		switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				startPhotoCrop(data.getData());
				break;
				
			case CAMERA_REQUEST_CODE:
				if (Utils.hasSdcard()) {
					startPhotoCrop(getTempFileUri());
				} else {
					Toast.makeText(getActivity(), "no camera found", Toast.LENGTH_LONG).show();
				}
				break;
				
			case RESULT_REQUEST_CODE:
				setFaceImageToView();
				break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void showLocalImagesSelector() {
		Intent intentFromGallery = new Intent();
		intentFromGallery.setType("image/*");
		intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
		
		startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
	}
	
	private void showCamera() {
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (Utils.hasSdcard()) {
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, getTempFileUri());
		}

		startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
	}
	
	
	public void startPhotoCrop(Uri uri) {		
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);	
						
		intent.putExtra("output", Uri.fromFile(getUserFaceFile()));
		//intent.putExtra("outputFormat", "PNG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", false);
		startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	private Uri getTempFileUri() {
		File tempFile = new File(getActivity().getFilesDir().getPath() + File.separator + TEMP_FACE_FILE_NAME);
		return Uri.fromFile(tempFile);
	}
	
	private File getUserFaceFile() {
		if (userFaceFile != null) {
			return userFaceFile;
		}
		
		//return new File(getFilesDir().getPath() + File.separator + User_FACE_FILE_NAME);
		//return new File(getFilesDir().getPath() + File.separator + "userFace");
		return new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "userFace");
	}
	
	private void setFaceImageToView() {
//	    ImageView faceView = (ImageView)findViewById(R.id.iv_setting_user_face);
    	Bitmap myBitmap = BitmapFactory.decodeFile(getUserFaceFile().getAbsolutePath());       
    	faceView.setImageBitmap(myBitmap);  
	}

	/*	
	private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
*/
}
