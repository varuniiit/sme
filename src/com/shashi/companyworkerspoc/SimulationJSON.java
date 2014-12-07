package com.shashi.companyworkerspoc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class SimulationJSON {

	public SimulationJSON() {
		// TODO Auto-generated constructor stub
	}

	public static String getJsonObject() {
		JSONObject jsonObject = new JSONObject();

		Date date = new Date();
		try {
			jsonObject.put("groupname", "Hr Unit");
			jsonObject.put("timetoreport", new SimpleDateFormat(
					"E yyyy-MM-dd 'at' hh:mm a").format(date));
			jsonObject.put("locationtoreport", "Bangalore");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
