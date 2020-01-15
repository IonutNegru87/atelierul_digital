package com.inegru.android.atelieruldigital.helloworld.week9;

import android.app.Application;

import com.inegru.android.atelieruldigital.helloworld.week9.db.AppDatabase;

/**
 * .
 */
public class AtelierulDigitalApp extends Application {


    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(AtelierulDigitalApp.this, appExecutors);
    }
}
