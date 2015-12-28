package co.infinum.productive.helpers;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by noxqs on 11.12.15..
 */
public class SharedPrefsHelper {

    public static void getSharedPrefsString(Context context, String key) {
        PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }

    public void setSharedPrefsString(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static void clearEntireSharedPrefs(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    public static void clearSingleSharedPrefItem(Context context, String key) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).apply();
    }

}
