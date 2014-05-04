package com.goparty.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.goparty.adapter.ContactListAdapter;
import com.goparty.model.Contact;

public class FriendInfoEditActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_info_edit_layout);
    }
}
