package com.shashi.companyworkerspoc.adapter;

import org.json.JSONException;
import org.json.JSONObject;

import com.shashi.companyworkerspoc.R;
import com.shashi.companyworkerspoc.SimulationJSON;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotificationListAdapter extends BaseAdapter {

	Context context;

	public NotificationListAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
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
		String group="error", time="error";
		try {
			JSONObject jsonObject = new JSONObject(
					SimulationJSON.getJsonObject());
			group = jsonObject.getString("groupname");
			time = jsonObject.getString("timetoreport");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baseItem.group.setText("Group name: " + group);
		baseItem.time.setText("Report time:\n" + time);
		return convertView;
	}

	private static class BaseItem {
		TextView group;
		TextView time;
	}

}
