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

public class NotificationDetailsActivity extends ActionBarActivity implements
		OnClickListener {

	TextView group, time, location;
	Button accept;
	String comments = null;
	EditText editText;

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

		int position = getIntent().getIntExtra("position", -1);
		System.out.println(position);
		if (position != -1) {

			try {
				JSONObject jsonObject = new JSONObject(
						SimulationJSON.getJsonObject());
				group.setText(jsonObject.getString("groupname"));
				time.setText(jsonObject.getString("timetoreport"));
				location.setText(jsonObject.getString("locationtoreport"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("groupname", group.getText().toString());
			jsonObject.put("comments", editText.getText().toString().trim());
			Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_LONG)
					.show();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
