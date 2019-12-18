package com.inegru.android.atelieruldigital.helloworld.week8;

import androidx.annotation.NonNull;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * .
 */
class HttpInterceptor extends BaseHttpInterceptor {

    @NonNull
    @Override
    HttpLoggingInterceptor.Level getInterceptorLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }
}
