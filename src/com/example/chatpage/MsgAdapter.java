package com.example.chatpage;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg> {
	private Context mContext;
	private int mResourseId;

	public MsgAdapter(Context context, int resource,
			List<Msg> objects) {
		super(context, resource, objects);
		mResourseId = resource;
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Msg msg = getItem(position);
		View view;
		ViewHolder holder;
		
		if(convertView == null){
			view = LayoutInflater.from(mContext).inflate(mResourseId, null);
			holder = new ViewHolder();
			holder.leftLayout = (LinearLayout)view.findViewById(R.id.layout_left);
			holder.rightLayout = (LinearLayout)view.findViewById(R.id.layout_right);
			holder.leftMsg = (TextView)view.findViewById(R.id.msg_left);
			holder.rightMsg = (TextView)view.findViewById(R.id.msg_right);
			view.setTag(holder);
		} else{
			view = convertView;
			holder = (ViewHolder)view.getTag();
		}
		
		if(msg.getType() == Msg.TYPE_RECV){
			holder.leftLayout.setVisibility(View.VISIBLE);
			holder.rightLayout.setVisibility(View.GONE);
			holder.leftMsg.setText(msg.getContent());
		} else if(msg.getType() == Msg.TYPE_SEND){
			holder.leftLayout.setVisibility(View.GONE);
			holder.rightLayout.setVisibility(View.VISIBLE);
			holder.rightMsg.setText(msg.getContent());
		}
		return view;
	}
	
	class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}

}
