package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder that will be used by the RecyclerView to display each item. Will also be used to
 * access the necessary fields for setting the content.
 */
class CustomViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final TextView tvFirstName;
    @NonNull
    private final TextView tvLastName;
    @NonNull
    private final Button btnInnerClick;
    @Nullable
    private final OnClickListener onClickListener;

    CustomViewHolder(@NonNull View itemView, @Nullable OnClickListener onClickListener) {
        super(itemView);

        tvFirstName = itemView.findViewById(R.id.tvFirstName);
        tvLastName = itemView.findViewById(R.id.tvLastName);
        btnInnerClick = itemView.findViewById(R.id.btnInnerClick);
        this.onClickListener = onClickListener;


        btnInnerClick.setOnClickListener(this::handleInnerClick);
    }

    private void handleInnerClick(View v) {
        // Make sure the button was actually clicked
        if (v.getId() == btnInnerClick.getId()) {
            if (onClickListener != null) {
                onClickListener.onButtonClicked(getAdapterPosition());
            }
        }
    }

    void bind(@NonNull UserModel item,
              @Nullable MyCustomAdapter.OnItemClickListener itemClickListener) {
        tvFirstName.setText(item.getFirstName());
        tvLastName.setText(item.getLastName());

        if (itemClickListener != null) {
            itemView.setOnClickListener(v -> itemClickListener.onItemClicked(itemView, item));
        }
    }

    interface OnClickListener {
        void onButtonClicked(int position);
    }
}
