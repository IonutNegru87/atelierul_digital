package com.inegru.android.atelieruldigital.helloworld.week8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * .
 */
@SuppressWarnings("unused")
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    /**
     * The list of users which is exposed through specific methods for list manipulation.
     *
     * Initialized at class level as we want to support empty lists also (provide holder while
     * the list is updated with data.
     * If we don't need this feature, we can remove the initialization and check the validity in
     * {@link #getItemCount()} directly.
     */
    @NonNull
    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view for a user
        final View itemView = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.list_item_user, parent, false);
        // Return the ViewHolder for our item view
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Get the user for the adapter position
        final User user = users.get(holder.getAdapterPosition());
        // Bind the user data to the view
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        // Must reflect the real size of the list.
        return users.size();
    }

    /**
     * Add a new user to the list.
     */
    void addUser(@NonNull User user) {
        users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    /**
     * Remove a user from the list.
     */
    void removeUser(@NonNull User user) {
        int indexOfUser = users.indexOf(user);
        if (indexOfUser != -1) {
            users.remove(indexOfUser);
            notifyItemRemoved(indexOfUser);
        }
    }

    /**
     * Add the specified list of users to be displayed.
     * Will be added at the end of the list.
     *
     * @param newUsers The new users to be displayed.
     */
    void addUsers(@NonNull List<User> newUsers) {
        int positionStart = users.size();
        int itemCount = newUsers.size();

        users.addAll(newUsers);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * Clear all previous users.
     */
    void clearUsers() {
        if (!users.isEmpty()) {
            users.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * {@link RecyclerView.ViewHolder} for {@link User}.
     */
    static final class UserViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView username;
        private final TextView email;
        private final TextView phone;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize all views which will update their values dynamically
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }

        //

        /**
         * Bind the POJO to the current view.
         *
         * @param user The data class representing the user
         */
        void bind(@NonNull final User user) {
            name.setText(user.getName());
            username.setText(String.format("(%s)", user.getUsername()));
            email.setText(user.getEmail());
            phone.setText(user.getPhone());
        }
    }
}
