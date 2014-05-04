package com.goparty.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.goparty.adapter.ChatMsgViewAdapter;
import com.goparty.biz.EventService;
import com.goparty.model.EventMessage;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class EventDetailsChatFragment extends Fragment {
	private View chatLayout;
	private ListView chatContentListView;
	private Button sendMsgButton;
	private EditText msgEdittext;
	private String eventId; 
	
	private ChatMsgViewAdapter mAdapter;
	//private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	private List<EventMessage> messageDataList = new ArrayList<EventMessage>();
	
	private EventService eventService = new EventService();

	public static EventDetailsChatFragment getInstance(Bundle bundle) {  
		EventDetailsChatFragment eventDetailsChatFragment = new EventDetailsChatFragment();  
		eventDetailsChatFragment.setArguments(bundle);  
	    return eventDetailsChatFragment;  
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		chatLayout = inflater.inflate(R.layout.event_details_chat, container, false);
		
		Bundle argus = getArguments();
		eventId = argus.getString("eventId");
		
		if (eventId == null || eventId == "") {
			Toast.makeText(getActivity(), "Event is empty.", Toast.LENGTH_LONG).show();
			return chatLayout;
		}
		
		initView();
        
        //initData();
		mAdapter = new ChatMsgViewAdapter(getActivity().getApplicationContext(), messageDataList);
		chatContentListView.setAdapter(mAdapter);
        
		DataQuery dataQuery = new DataQuery();
        dataQuery.execute(eventId);
        
		return chatLayout;
	}
	
	public void initView()
    {
		chatContentListView = (ListView) chatLayout.findViewById(R.id.chat_listview);
		sendMsgButton = (Button) chatLayout.findViewById(R.id.btn_send);
		sendMsgButton.setOnClickListener(new ButtonOnClick());
    	
		msgEdittext = (EditText) chatLayout.findViewById(R.id.chat_msg_edittext);
    }
	
/*	private String[]msgArray = new String[]{"hi", "hei", "how are you", "how are you doing", 
			"where are you from?", "star",
			"really", "....",};

	private String[]dataArray = new String[]{"2012-09-01 18:00", "2012-09-01 18:10", 
				"2012-09-01 18:11", "2012-09-01 18:20", 
				"2012-09-01 18:30", "2012-09-01 18:35", 
				"2012-09-01 18:40", "2012-09-01 18:50"};
	
	private final static int COUNT = 8;
*/
/*	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setName("ABC");
				entity.setMsgType(true);
			} else {
				entity.setName("Captain");
				entity.setMsgType(false);
			}

			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new ChatMsgViewAdapter(getActivity().getApplicationContext(), mDataArrays);
		chatContentListView.setAdapter(mAdapter);
	}*/
	
	private class ButtonOnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
				case R.id.btn_send:
					send();
					break;
			}
		}
	}
	
	private void send()
	{
		String contString = msgEdittext.getText().toString();
		if (contString.length() > 0)
		{
			/*ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setName("Captain");
			entity.setMsgType(false);
			entity.setText(contString);
			
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();
			
			msgEdittext.setText("");
			
			chatContentListView.setSelection(chatContentListView.getCount() - 1);*/
			
			//to do
			DataPost dataPost = new DataPost();
			dataPost.execute(eventId, contString);
	        
//			EventMessage entity = new EventMessage();
//			entity.setPublishTime(getDate());
//			entity.setName("Captain");
//			entity.setMsgType(false);
//			entity.setText(contString);
//			
//			mDataArrays.add(entity);
//			mAdapter.notifyDataSetChanged();
//			
//			msgEdittext.setText("");
//			
//			chatContentListView.setSelection(chatContentListView.getCount() - 1);
		}
	}
	
	private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));
        
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins); 
        											
        return sbBuffer.toString();
    }
	
	private class DataQuery extends AsyncTask<String, Integer, Boolean> 
    {
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected Boolean doInBackground(String... params) 
        {  
            try 
            {
            	List<EventMessage> messagesResult = eventService.getMessages(params[0]);
            	if (messagesResult != null && messagesResult.size() > 0) { 
        			for (EventMessage item : messagesResult) {
        				messageDataList.add(item);
        			}
        		}
        		
            	//messageDataList = arrayList;
            } 
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            
            return true;
        }  
          
        @Override  
        protected void onProgressUpdate(Integer... progresses) 
        {  
        }  
          
        @Override  
        protected void onPostExecute(Boolean result) 
        {
        	if (result) {
//        		mAdapter.count += messageDataList.size();
        		mAdapter.notifyDataSetChanged();
        	}
        	//TO-DO: we should add a loading mask and keep it with promote when error happens.
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
	
	private class DataPost extends AsyncTask<String, Integer, Boolean> 
    {
		EventMessage msgResult;
		
        @Override  
        protected void onPreExecute() 
        {  
        }  
          
        @Override  
        protected Boolean doInBackground(String... params) 
        {  
            try 
            {
            	msgResult = eventService.postEventMessage(params[0], params[1]);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            
            return true;
        }  
          
        @Override  
        protected void onProgressUpdate(Integer... progresses) 
        {  
        }  
          
        @Override  
        protected void onPostExecute(Boolean result) 
        {
        	if (result && msgResult != null && msgResult.getId() != "") {
        		messageDataList.add(msgResult);
    			mAdapter.notifyDataSetChanged();
    			
    			msgEdittext.setText("");
    			
    			chatContentListView.setSelection(chatContentListView.getCount() - 1);
        	}
        	//TO-DO: we should add a loading mask and keep it with promote when error happens.
        }  
          
        @Override  
        protected void onCancelled() 
        {  
        }  
    }
}
