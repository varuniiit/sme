package com.shashi.companyworkerspoc;

import org.json.JSONException;
import org.json.JSONObject;

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

public class NotificationActivity extends ActionBarActivity implements
		OnItemClickListener {

	public static Boolean inBackground = true;
	ListView listView;
	NotificationListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		listView = (ListView) findViewById(R.id.notification);
		// startActivity(new Intent(this, NotificationDetailsActivity.class));
		adapter = new NotificationListAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		ParsePush.subscribeInBackground("Giants");
		JSONObject data = null;
		try {
			data = new JSONObject(
					"{\"alert\": \"The Mets scored!\",\"badge\": \"Increment\",\"sound\": \"cheering.caf\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * ParsePush push = new ParsePush(); push.setChannel("Giants");
		 * push.setData(data); push.sendInBackground();
		 */
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
		JSONObject jsonObject = null;
		try {
			/*
			 * jsonObject = new JSONObject(SimulationJSON.getJsonObject());
			 * Intent intent = new Intent(this,
			 * NotificationDetailsActivity.class); intent.putExtra("groupname",
			 * jsonObject.getString("groupname"));
			 * intent.putExtra("timetoreport",
			 * jsonObject.getString("timetoreport"));
			 * intent.putExtra("locationtoreport",
			 * jsonObject.getString("locationtoreport"));
			 */
			Intent intent = new Intent(this, NotificationDetailsActivity.class);
			intent.putExtra("data", SimulationJSON.getJsonObject());
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
		NotificationActivity.inBackground = false;
		checkMessage();
	}

	private void checkMessage() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null) {
				for (String key : extras.keySet()) {
					System.out.println(extras.getString(key));
					// message+= key + "=" + extras.getString(key) + "\n";
				}
			}
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		NotificationActivity.inBackground = true;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
		checkMessage();
	}
}
