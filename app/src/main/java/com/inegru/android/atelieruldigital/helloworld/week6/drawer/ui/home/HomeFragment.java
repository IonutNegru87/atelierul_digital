package com.inegru.android.atelieruldigital.helloworld.week6.drawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;
import com.inegru.android.atelieruldigital.helloworld.week6.common.NameUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        textView.setText(NameUtils.getFragmentName(this));

        return root;
    }


}