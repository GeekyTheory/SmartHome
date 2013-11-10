package com.geekytheory.SmartHome_App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * Author: Mario Pérez Esteso
 * Website: http://geekytheory.com
 * Mail: mario@geekytheory.com
 */

class AsyncLoadTasks extends CommonAsyncTask {
	MyActivity activity;

	private final int GET_JSON = 0;
	private final int UPDATE_DATABASE = 1;

	int option;

	int id;
	String atr, value;
	AsyncLoadTasks(MyActivity mainactivity, int option, int id, String atr, String value) {
		super(mainactivity);
		this.activity = mainactivity;
		this.option = option;
		this.id = id;
		this.atr = atr;
		this.value = value;
	}

	@Override
	protected void doInBackground() throws IOException {
		switch (option) {
		case GET_JSON:
			JSONuse json = new JSONuse(); // Receive JSON of the server
			Log.i("JSON_DATA", json.jArray.toString());
			try {
				
				for (int i = 0; i < json.jArray.length(); i++) {
					json.json_data = json.jArray.getJSONObject(i);
					activity.titles.add(json.json_data.getString("NAME"));
					ArrayList<DeviceItem> deviceItemList = new ArrayList<DeviceItem>();
					DeviceItem deviceItem;
					if (json.json_data.getString("LEDA").equals("1")) {
						deviceItem = new DeviceItem("LEDA",
								json.json_data.getInt("ID"), true);
					} else {
						deviceItem = new DeviceItem("LEDA",
								json.json_data.getInt("ID"), false);
					}
					deviceItemList.add(deviceItem);
					if (json.json_data.getString("LEDB").equals("1")) {
						deviceItem = new DeviceItem("LEDB",
								json.json_data.getInt("ID"), true);
					} else {
						deviceItem = new DeviceItem("LEDB",
								json.json_data.getInt("ID"), false);
					}
					deviceItemList.add(deviceItem);
					if (json.json_data.getString("LEDC").equals("1")) {
						deviceItem = new DeviceItem("LEDC",
								json.json_data.getInt("ID"), true);
					} else {
						deviceItem = new DeviceItem("LEDC",
								json.json_data.getInt("ID"), false);
					}
					deviceItemList.add(deviceItem);
					// deviceItemList = activity.deviceItemList;
					activity.matrixList.add(deviceItemList);
					Log.i("log_tag", "id: " + json.json_data.getInt("ID"));
				}

			} catch (Exception e) {
				Log.i("JSON ERROR", "Error al leer JSON");
			}
			break;
		case UPDATE_DATABASE:
			JSONuse update = new JSONuse(id, atr, value);
		}
	}

	static void run(MyActivity activity, int option, int id, String atr, String value) {
		new AsyncLoadTasks(activity, option, id, atr, value).execute();
	}
}
