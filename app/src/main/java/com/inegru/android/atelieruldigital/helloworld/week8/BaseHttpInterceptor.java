package com.inegru.android.atelieruldigital.helloworld.week8;

import android.util.Log;

import androidx.annotation.NonNull;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * .
 */
abstract class BaseHttpInterceptor {

    /**
     * Get the {@link HttpLoggingInterceptor.Level} for the
     * {@link HttpLoggingInterceptor interceptor}
     */
    @NonNull
    abstract HttpLoggingInterceptor.Level getInterceptorLevel();

    /**
     * Get the custom interceptor that will be used for all HTTP requests.
     */
    final HttpLoggingInterceptor getInterceptor() {
        // Create a custom interceptor which allows us to change the location of the logs if needed
        HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));

        // Set the level for the interceptor
        loggingInterceptor.level(getInterceptorLevel());

        return loggingInterceptor;
    }
}
