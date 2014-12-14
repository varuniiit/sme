package com.shashi.companyworkerspoc.receivers;

import java.util.Iterator;

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
import com.shashi.companyworkerspoc.NotificationDetailsActivity;
import com.shashi.companyworkerspoc.R;

public class PushBroadcastReceiver extends ParsePushBroadcastReceiver {

	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// super.onReceive(context, intent);
		// System.out.println("Broadcast Receiver Push");
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
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void showNotification(String message) {
		Intent emptyIntent = new Intent(context,
				NotificationDetailsActivity.class);
		emptyIntent.putExtra("data", message);
		try {
			JSONObject jsonObject = new JSONObject(message);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 9,
					emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					context)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Worker Notification")
					.setContentText(
							jsonObject.getString("groupname") + ".\n"
									+ jsonObject.getString("timetoreport") + ".")
					.setContentIntent(pendingIntent);
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
