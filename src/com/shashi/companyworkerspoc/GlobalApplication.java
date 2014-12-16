package com.shashi.companyworkerspoc;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.PushService;

public class GlobalApplication extends Application {

	public static boolean isAppOpend = false;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.initialize(this, "VohYl19On2ZdUPmbIa8i85hxFOgbRQIyNnAYPU9z",
				"2BoZ0xM4Loo63jtMapBzsOud5WeOilrnfQNoCzH4");
		PushService.setDefaultPushCallback(this,
				NotificationDetailsActivity.class);
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);

	}
}
