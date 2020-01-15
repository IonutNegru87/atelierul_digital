package com.inegru.android.atelieruldigital.helloworld.week9;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * .
 */
@SuppressWarnings("SameParameterValue")
class SharedPrefUtil {

    private static final String APP_KEY = "android_course_key";

    private SharedPrefUtil() {
    }

    static void setStringValueInSharedPreferences(
        @NonNull Context context,
        @NonNull String key,
        @Nullable String value) {
        SharedPreferences sp = getSharedPreference(context);
        Editor editor = sp.edit();
        editor.putString(key, value)
              .apply();
    }

    @Nullable
    static String getStringValueFromSharedPreferences(
        @NonNull Context context,
        @NonNull String key) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getString(key, null);
    }

    static void deleteValueFromSharedPreferences(
        @NonNull Context context,
        @NonNull String key) {
        SharedPreferences sp = getSharedPreference(context);
        Editor editor = sp.edit();
        editor.remove(key)
              .apply();
    }

    static void deleteAllValuesFromSharedPreferences(@NonNull Context context) {
        SharedPreferences sp = getSharedPreference(context);
        Editor editor = sp.edit();
        editor.clear()
              .apply();
    }

    private static SharedPreferences getSharedPreference(@NonNull Context context) {
        // Get the Shared Preference of the file with the APP_KEY name
        return context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);

        // If you need app specific preferences (used in combination with Settings/Preference
        // screen, you can instead use:
        // return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
