package com.inegru.android.atelieruldigital.helloworld.week7;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the activity layout that contains a BottomNavigationView and a fragment host
        setContentView(R.layout.activity_botom_nav);

        // Initialize the BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each menu should be considered as top
        // level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
            .build();

        // Initialize the Navigation controller that manages navigation within a navigation host
        // The navigation host also contains the navigation graph required to handle the actual
        // navigation between the fragments
        // Note that the controller will use the add() function for handling fragment
        // transactions thus creating an bug/issue with the states of the fragments (you cannot
        // persist it's state relying on the default functionality of the navigation)
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Setup the actionbar with the navigation controller
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Setup the bottom navigation view with the navigation controller
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}
