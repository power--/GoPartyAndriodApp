package com.goparty.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class EventDetailsMainFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		//System.out.println("---------------onCreateView");
		View v = inflater.inflate(R.layout.event_details_main, container, false);
//		Button btn = (Button) v.findViewById(R.id.btn1);
//		btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getActivity(), "button", Toast.LENGTH_SHORT).show();
//			}
//		});
		return v;
	}

	/*
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		//System.out.println("---------------onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//System.out.println("---------------onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onResume");
		super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		//System.out.println("---------------onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onPause");
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		//System.out.println("---------------onDetach");
		super.onDetach();
	}
	*/
}