package com.inegru.android.atelieruldigital.helloworld.week6.fragments;

import android.os.Bundle;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class StaticFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity (should include the custom <fragment>)
        setContentView(R.layout.activity_static_fragment);
    }
}
