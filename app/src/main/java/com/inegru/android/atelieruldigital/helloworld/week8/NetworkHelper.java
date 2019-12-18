package com.inegru.android.atelieruldigital.helloworld.week8;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;

/**
 * .
 */
class NetworkHelper {

    private NetworkHelper() {
    }

    static boolean isConnected(@NonNull Context context) {
        ConnectivityManager cm =
            (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Get the network capabilities for the current active network
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    // Check the transport type for the current active network
                    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                } else {
                    // Invalid network capabilities
                    return false;
                }
            } else {
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        } else {
            // Invalid connectivity service - we cannot check the network capabilities
            return false;
        }
    }
}
