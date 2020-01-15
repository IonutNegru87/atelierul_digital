package com.inegru.android.atelieruldigital.helloworld.week9;

import android.os.Bundle;
import android.util.Log;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class SharedPrefActivity extends AppCompatActivity {

    private static final String TAG = "SharedPrefActivity";

    private static final String USERNAME_KEY = "username_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpref);

        String userNameTest = "test_user";

        Log.d(TAG, "saving [" + userNameTest + "] in SP with key [" + USERNAME_KEY + "]");
        // Save a value to shared preference
        SharedPrefUtil.setStringValueInSharedPreferences(this, USERNAME_KEY, "test_user");

        // Read a value from shared preference
        String username = SharedPrefUtil.getStringValueFromSharedPreferences(this, USERNAME_KEY);
        Log.d(TAG, "retrieving value from SP for key [" + USERNAME_KEY + "]: " + username);

        // Delete a value from shared preference
        SharedPrefUtil.deleteValueFromSharedPreferences(this, USERNAME_KEY);
        Log.d(TAG, "deleted value from SP for key [" + USERNAME_KEY + "]");

        // Delete all values from shared preference
        SharedPrefUtil.deleteAllValuesFromSharedPreferences(this);
        Log.d(TAG, "deleted all keys from SP");
    }


}
