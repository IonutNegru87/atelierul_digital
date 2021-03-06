package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inegru.android.atelieruldigital.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom adapter that will be used by the RecyclerView.
 */
class MyCustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    // The items (from the Data Source) that will be used to populate each row content.
    @NonNull
    private final List<UserModel> items = new ArrayList<>();

    @Nullable
    private OnItemClickListener itemClickListener;

    MyCustomAdapter(List<UserModel> items) {
        this.items.addAll(items);
    }

    void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    void addUser(UserModel user) {
        // Alter the data source
        items.add(user);

        // Generic notify for data source change: to be used for multiple changes
        // notifyDataSetChanged();

        // Fine grained method for refreshing only the item that was added to the Data Source
        notifyItemChanged(items.size() - 1);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Get the context from the parent group
        Context context = parent.getContext();

        // Initialize the utility inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate view - list item layout
        View view = inflater.inflate(R.layout.item_list_layout, parent, false);

        // Return our custom view holder
        return new CustomViewHolder(view, position -> {
            UserModel user = items.get(position);
            // Pass the data to the top click listener
            if (itemClickListener != null && user != null) {
                itemClickListener.onItemClicked(view, user);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(items.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        // Mandatory for indicating the number of items that should be displayed
        return items.size();
    }

    /**
     * Contract for click actions on list items.
     */
    interface OnItemClickListener {
        void onItemClicked(@NonNull View view, @NonNull UserModel user);
    }
}
