package com.inegru.android.atelieruldigital.helloworld.week9.model;

import androidx.annotation.NonNull;

/**
 * .
 */
public class Location {

    private double latitude;

    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
            "latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
