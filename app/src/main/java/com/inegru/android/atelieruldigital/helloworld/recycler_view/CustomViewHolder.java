package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import android.view.View;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder that will be used by the RecyclerView to display each item. Will also be used to
 * access the necessary fields for setting the content.
 */
class CustomViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    final TextView tvFirstName;
    @NonNull
    final TextView tvLastName;

    CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        tvFirstName = itemView.findViewById(R.id.item_first_name);
        tvLastName = itemView.findViewById(R.id.item_last_name);
    }
}
