package com.geekytheory.SmartHome_App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Author: Mario Pérez Esteso
 * Website: http://geekytheory.com
 * Mail: mario@geekytheory.com
 */

public class JSONuse {

	private final int GET_JSON = 0;
	private final int UPDATE_DATABASE = 1;

	String returnString = null; // to store the result of MySQL query after
								// decoding JSON
	String response = null;
	String result = null;
	JSONObject json_data;
	JSONArray jArray;

	public JSONuse() {
		try {
			response = CustomHttpClient.executeHttpPost(
					"http://192.168.1.9/smarthome/index.php",
					new ArrayList<NameValuePair>(0));

			// store the result returned by PHP script that runs MySQL query
			result = response.toString();
			// parse json data
			try {
				returnString = "";
				jArray = new JSONArray(result);
			} catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}

			try {
				Log.i("RETURN", returnString);
			} catch (Exception e) {
				Log.e("log_tag", "Error in Display!" + e.toString());

			}
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection!!" + e.toString());
		}

	}

	public JSONuse(int id, String atr, String value) {
		postData(id, atr, value);
	}

	public void postData(int id, String atr, String value) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://192.168.1.9/smarthome/update.php"); // Raspberry Pi IP

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("ID", id + "")); // POST															// Values
			nameValuePairs.add(new BasicNameValuePair("ATR", atr));
			nameValuePairs.add(new BasicNameValuePair("VALUE", value));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity ent = response.getEntity();
			Log.i("RESPUESTA", EntityUtils.toString(ent));

		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}
	}
}