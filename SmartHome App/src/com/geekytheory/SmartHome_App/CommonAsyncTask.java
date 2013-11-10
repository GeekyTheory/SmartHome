package com.geekytheory.SmartHome_App;

/**
 * Author: Mario Pérez Esteso
 * Website: http://geekytheory.com
 * Mail: mario@geekytheory.com
 */

import java.io.IOException;

import android.os.AsyncTask;

abstract class CommonAsyncTask extends AsyncTask<Void, Void, Boolean> {

	final MyActivity activity;

	CommonAsyncTask(MyActivity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected final Boolean doInBackground(Void... ignored) {
		try {
			doInBackground();
			return true;
		} catch (Exception e) {
			Utils.logAndShow(activity, "MyActivity", e);
		}
		return false;
	}

	@Override
	protected final void onPostExecute(Boolean success) {
		super.onPostExecute(success);
		if (success) {
			activity.refreshView();
		}
	}

	protected void doInBackground() throws IOException {

	}

}