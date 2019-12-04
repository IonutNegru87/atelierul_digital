package com.inegru.android.atelieruldigital.helloworld.week6.drawer;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DrawerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content layout for the activity: contains navigation drawer components
        setContentView(R.layout.activity_drawer);

        // Required toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Floating action button for add action
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::addNewTrip);

        // Setup navigation drawer layout: the whole area of the drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // The navigation view that contains the header and the menu items
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top
        // level destinations. Also required for Ancestral navigation
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_about_us, R.id.nav_contact, R.id.nav_share)
            // Required layout for the actual drawer
            .setDrawerLayout(drawer)
            .build();

        // Controller for navigation (Special NavHostFragment is used to provide an area within
        // our layout for self-contained navigation to occur.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Setup the actionbar/toolbar with the navigation controller and the appbar config
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        // Setup the navigation controller with the actual navigation view and the navigation
        // controller
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
            || super.onSupportNavigateUp();
    }

    private void addNewTrip(View view) {
        Snackbar.make(view, "Add new trip", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }
}
