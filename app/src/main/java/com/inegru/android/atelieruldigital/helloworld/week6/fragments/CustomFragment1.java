package com.inegru.android.atelieruldigital.helloworld.week6.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Utility First Fragment.
 */
public class CustomFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate a new view from the specified layout resource.
        // The provided container will be the actual root for our new view
        // Do not attach the new view to the actual root, as we want to keep the defined layout
        // parameters
        return inflater.inflate(R.layout.fragment_custom1, container, false);
    }
}
