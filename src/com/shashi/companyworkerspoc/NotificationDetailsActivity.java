package com.shashi.companyworkerspoc;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseObject;

public class NotificationDetailsActivity extends ActionBarActivity implements
		OnClickListener {

	TextView group, time, location;
	Button accept;
	String comments = null;
	EditText editText;

	ParseObject parseObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_details);
		group = (TextView) findViewById(R.id.groupid);
		time = (TextView) findViewById(R.id.timetoreport);
		location = (TextView) findViewById(R.id.locationtoreport);
		accept = (Button) findViewById(R.id.accept);
		editText = (EditText) findViewById(R.id.comments);
		accept.setOnClickListener(this);
		String data = getIntent().getStringExtra("data");
		try {
			JSONObject jsonObject = new JSONObject(data);
			group.setText(jsonObject.getString("groupname"));
			time.setText(jsonObject.getString("timetoreport"));
			location.setText(jsonObject.getString("locationtoreport"));
			parseObject = new ParseObject("WorkerResponseData");
			parseObject.put("groupname", jsonObject.getString("groupname"));
			parseObject.put("timetoreport",
					jsonObject.getString("timetoreport"));
			parseObject.put("locationtoreport",
					jsonObject.getString("locationtoreport"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		try {
			parseObject.put("comments", editText.getText().toString().trim());
			parseObject.saveInBackground();
			Toast.makeText(NotificationDetailsActivity.this,
					"Saved Successfully", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
