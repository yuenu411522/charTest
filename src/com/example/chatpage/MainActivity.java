package com.example.chatpage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private List<Msg> mMsgList = new ArrayList<Msg>();
	private ListView mList;
	private MsgAdapter mAdapter;
	private EditText mSendMsg;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		initMsgList();
		mAdapter = new MsgAdapter(MainActivity.this, R.layout.msg_layout, mMsgList);
		mList = (ListView)findViewById(R.id.list);
		mList.setAdapter(mAdapter);
		
		mSendMsg = (EditText)findViewById(R.id.edt_send);
		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String content = mSendMsg.getText().toString();
				if("".equals(content)){
					Toast.makeText(MainActivity.this, "Please type something here", Toast.LENGTH_SHORT).show();
				} else{
					Msg msg = new Msg(content, Msg.TYPE_SEND);
					mMsgList.add(msg);
					mAdapter.notifyDataSetChanged();
					mList.setSelection(mMsgList.size());
					mSendMsg.setText("");
				}
			}
		});
	}

	private void initMsgList() {
		Msg msg = new Msg("Hello, Chrry!", Msg.TYPE_RECV);
		mMsgList.add(msg);
		msg = new Msg("Hello, Where are you,Alex?", Msg.TYPE_SEND);
		mMsgList.add(msg);
		msg = new Msg("America, for three days ago", Msg.TYPE_RECV);
		mMsgList.add(msg);
	}
}
