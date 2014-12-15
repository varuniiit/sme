package com.shashi.companyworkerspoc.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shashi.companyworkerspoc.R;
import com.shashi.companyworkerspoc.db.NotificationDatabase;

public class NotificationListAdapter extends BaseAdapter {

	Context context;
	List<NotificationDatabase> notificationDatabases;
	int maxSize = 0;

	public void setList(List<NotificationDatabase> notificationDatabases) {
		this.notificationDatabases = notificationDatabases;
	}

	public NotificationListAdapter(Context context,
			List<NotificationDatabase> list) {
		this.context = context;
		notificationDatabases = list;
		maxSize = notificationDatabases.size();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notificationDatabases.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	BaseItem baseItem;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			baseItem = new BaseItem();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.notification_support,
					parent, false);
			baseItem.group = (TextView) convertView.findViewById(R.id.group);
			baseItem.time = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(baseItem);
		} else {
			baseItem = (BaseItem) convertView.getTag();
		}
		NotificationDatabase database = notificationDatabases.get((maxSize - 1)
				- position);
		//System.out.println("read status  " + database.getReadStatus());
		baseItem.group.setText("Group name: " + database.getGroupName());
		baseItem.time.setText("Report time:\n" + database.getTimeToReport());
		if (database.getReadStatus().equals("true")) {
			baseItem.group.setTypeface(null, Typeface.BOLD);
			baseItem.time.setTypeface(null, Typeface.BOLD);
		}else{
			baseItem.group.setTypeface(null, Typeface.NORMAL);
			baseItem.time.setTypeface(null, Typeface.NORMAL);
		}
		return convertView;
	}

	private static class BaseItem {
		TextView group;
		TextView time;
	}

}
