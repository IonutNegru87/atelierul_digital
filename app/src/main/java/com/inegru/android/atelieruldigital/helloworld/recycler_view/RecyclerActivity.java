package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import android.os.Bundle;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * .
 */
public class RecyclerActivity extends AppCompatActivity {

    private MyCustomAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout that contains the RecyclerView
        setContentView(R.layout.activity_recycler_view);

        // Get the RecyclerView based on its ID and initialize it
        RecyclerView rv = findViewById(R.id.recycler_view);

        // Set the Layout Manager for the RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        // Initialize the custom Adapter for the Recycler View
        adapter = new MyCustomAdapter(RecyclerViewUtils.getData());

        // Set the adapter on the RecyclerView
        rv.setAdapter(adapter);


    }

    // Method interacting with a button
    void onButtonClicked() {
        addDummyUser();
    }

    // Add a dummy user to the adapter
    private void addDummyUser() {
        // Make sure the adapter is initialized
        if (null == adapter) {
            return;
        }

        // Create a new dummy user
        final UserModel dummyUser = new UserModel("Dummy", "User");

        // Add the new user
        adapter.addUser(dummyUser);
    }

}
