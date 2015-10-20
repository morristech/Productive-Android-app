package co.infinum.productive.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import co.infinum.productive.ProductiveApp;

public final class SharedPrefsHelper {

    public static final String TOKEN = "token";

    private SharedPrefsHelper() {
    }

    private static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance());
    }

    public static String getToken() {
        SharedPreferences preferences = getPreferences();
        return preferences.getString(TOKEN, null);
    }

    public static void saveToken(String token) {
        PreferenceManager.getDefaultSharedPreferences(ProductiveApp.getInstance()).edit().putString(TOKEN, token).apply();
    }
}
