package com.inegru.android.atelieruldigital.helloworld.week6.drawer.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;
import com.inegru.android.atelieruldigital.helloworld.week6.common.NameUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AboutUsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        final TextView textView = root.findViewById(R.id.text_about_us);

        textView.setText(NameUtils.getFragmentName(this));

        return root;
    }
}