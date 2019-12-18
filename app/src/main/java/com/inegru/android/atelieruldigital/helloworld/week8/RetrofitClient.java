package com.inegru.android.atelieruldigital.helloworld.week8;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * .
 */
final class RetrofitClient {

    // Change with other API if need or provide dynamic creation for retrofit client
    // Check https://github.com/public-apis/public-apis for other public APIs
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    // @Nullable
    // private static volatile RetrofitClient INSTANCE;
    //
    // Create a custom method to provide a singleton(thread-safe) object for RetrofitClient
    //
    // @NonNull
    // static RetrofitClient getInstance() {
    //     RetrofitClient result = INSTANCE;
    //     if (result == null) {
    //         synchronized (RetrofitClient.class) {
    //             result = INSTANCE;
    //             if (result == null) {
    //                 INSTANCE = result = new RetrofitClient();
    //             }
    //         }
    //     }
    //     return result;
    // }

    private static final Retrofit retrofit;

    static {
        final OkHttpClient httpClient = new OkHttpClient.Builder()
            // Add the interceptor for all HTTP requests
            .addInterceptor(new HttpInterceptor().getInterceptor())
            .build();

        // Statically initialize Retrofit client
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Custom OkHttpClient
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    private RetrofitClient() {
        // Hide the constructor - avoid object instantiation
    }

    /**
     * Expose the client, already configured.
     *
     * @return Retrofit client.
     */
    @NonNull
    static Retrofit getClient() {
        return retrofit;
    }
}
