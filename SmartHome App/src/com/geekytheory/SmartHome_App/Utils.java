package com.geekytheory.SmartHome_App;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

/**
 * Author: Mario Pérez Esteso
 * Website: http://geekytheory.com
 * Mail: mario@geekytheory.com
 */

public class Utils {

    public static void logAndShow(Activity activity, String tag, Throwable t) {
        Log.e(tag, "Error", t);
        String message = t.getMessage();
        showError(activity, message);
    }

    public static void logAndShowError(Activity activity, String tag,
                                       String message) {
        String errorMessage = getErrorMessage(activity, message);
        Log.e(tag, errorMessage);
        showErrorInternal(activity, errorMessage);
    }

    public static void showError(Activity activity, String message) {
        String errorMessage = getErrorMessage(activity, message);
        showErrorInternal(activity, errorMessage);
    }

    private static void showErrorInternal(final Activity activity,
                                          final String errorMessage) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private static String getErrorMessage(Activity activity, String message) {
        Resources resources = activity.getResources();
        if (message == null) {
            return resources.getString(R.string.error);
        }
        return resources.getString(R.string.error_format, message);
    }
}