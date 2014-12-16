package com.shashi.companyworkerspoc.receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.parse.ParsePushBroadcastReceiver;
import com.shashi.companyworkerspoc.GlobalApplication;
import com.shashi.companyworkerspoc.NotificationActivity;
import com.shashi.companyworkerspoc.NotificationDetailsActivity;
import com.shashi.companyworkerspoc.R;
import com.shashi.companyworkerspoc.db.DataBaseHelper;
import com.shashi.companyworkerspoc.db.NotificationDatabase;

public class PushBroadcastReceiver extends ParsePushBroadcastReceiver {

	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		Toast.makeText(context, "Recived", Toast.LENGTH_LONG).show();
		System.out.println("Received");
		System.out.println("Intent  " + intent.getAction());
		System.out.println("Channel  "
				+ intent.getExtras().getString("com.parse.Channel"));
		System.out.println("Channel  "
				+ intent.getExtras().getString("com.parse.Data"));
		if (intent.getAction().equals("com.parse.push.intent.RECEIVE")) {
			try {
				JSONObject jsonObject = new JSONObject(intent.getExtras()
						.getString("com.parse.Data"));
				// Iterator<String> iterator = jsonObject.keys();
				String data = jsonObject.getString("alert");
				System.out.println("Data  " + data);
				showNotification(data);
				if(GlobalApplication.isAppOpend){
					NotificationActivity.updateListView();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void showNotification(String message) {
		Intent emptyIntent = new Intent(context,
				NotificationDetailsActivity.class);
		
		try {
			JSONObject jsonObject = new JSONObject(message);
			DataBaseHelper baseHelper = new DataBaseHelper(context);
			NotificationDatabase database = new NotificationDatabase();
			database.setGroupName(jsonObject.getString("groupname"));
			database.setLocationToReport(jsonObject
					.getString("locationtoreport"));
			database.setTimeToReport(jsonObject.getString("timetoreport"));
			database.setReadStatus("true");
			database.setComments("");
			baseHelper.insert(database);
			JSONObject json = new JSONObject();
			json.put("id", database.getId());
			json.put("groupname", database.getGroupName());
			json.put("timetoreport", database.getTimeToReport());
			json.put("locationtoreport", database.getLocationToReport());
			json.put("readstatus", database.getReadStatus());
			json.put("comments", database.getComments());
			
			emptyIntent.putExtra("data", json.toString());
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 9,
					emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					context)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Worker Notification")
					.setContentText(
							jsonObject.getString("groupname") + ".\n"
									+ jsonObject.getString("timetoreport")
									+ ".").setContentIntent(pendingIntent);
			mBuilder.setSound(RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
			NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify(99, mBuilder.build());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
