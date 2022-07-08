package com.fyp.iot.project;

import static com.fyp.iot.project.Utils.PrefConstants.*;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static void showToast(String msg) {
        Toast.makeText(AppController.getInstance().getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void setPrefs(String key, String value) {
        PreferenceUtility.setStringPreference(AppController.getInstance().getContext(), APP_PREFS, key, value);
    }

    public static String getStringPrefs(String key) {
        return PreferenceUtility.getStringPreference(AppController.getInstance().getContext(), APP_PREFS, key, "");
    }

    public static String getDateTime(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static class PrefConstants {
        public static String APP_PREFS = "app_prefs";

        public static String PREF_USER_EMAIL = "pref_user_email";
    }
}
