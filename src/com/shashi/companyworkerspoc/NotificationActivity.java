package com.shashi.companyworkerspoc;

import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.parse.ParsePush;
import com.shashi.companyworkerspoc.adapter.NotificationListAdapter;
import com.shashi.companyworkerspoc.db.DataBaseHelper;
import com.shashi.companyworkerspoc.db.NotificationDatabase;

public class NotificationActivity extends ActionBarActivity implements
		OnItemClickListener {

	static ListView listView;
	static NotificationListAdapter adapter;
	static DataBaseHelper helper;
	int maxSize = 0;
	static List<NotificationDatabase> Notilist;
	static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		GlobalApplication.isAppOpend = true;
		listView = (ListView) findViewById(R.id.notification);
		context = this;
		// startActivity(new Intent(this, NotificationDetailsActivity.class));
		helper = new DataBaseHelper(this);
		Notilist = helper.getAllEntries();
		for (NotificationDatabase iterable : Notilist) {
			System.out.println(iterable);
		}
		maxSize = Notilist.size();
		adapter = new NotificationListAdapter(this, Notilist);
		listView.setOnItemClickListener(this);
		if (maxSize != 0) {
			//adapter = new NotificationListAdapter(this, Notilist);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
		} else {
			listView.setVisibility(View.INVISIBLE);
		}

		ParsePush.subscribeInBackground("Giants");
		helper = new DataBaseHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			NotificationDatabase database = Notilist.get((maxSize - 1)
					- position);
			jsonObject.put("id", database.getId());
			jsonObject.put("groupname", database.getGroupName());
			jsonObject.put("timetoreport", database.getTimeToReport());
			jsonObject.put("locationtoreport", database.getLocationToReport());
			jsonObject.put("readstatus", database.getReadStatus());
			jsonObject.put("comments", database.getComments());
			Intent intent = new Intent(this, NotificationDetailsActivity.class);
			intent.putExtra("data", jsonObject.toString());
			startActivity(intent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("Resume");
		Notilist = helper.getAllEntries();
		maxSize = Notilist.size();
		if (maxSize != 0) {
			Notilist = helper.getAllEntries();
			adapter.setList(Notilist);
			// adapter.notifyDataSetChanged();
			// adapter = new NotificationListAdapter(this, Notilist);
			listView.setAdapter(adapter);
		}

	}

	public static void updateListView() {
		Notilist = helper.getAllEntries();
		adapter.setList(Notilist);
		// adapter.notifyDataSetChanged();
		// adapter = new NotificationListAdapter(this, Notilist);
		listView.setAdapter(adapter);
	}
}
