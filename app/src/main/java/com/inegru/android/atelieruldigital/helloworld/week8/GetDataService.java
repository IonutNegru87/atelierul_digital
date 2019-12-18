package com.inegru.android.atelieruldigital.helloworld.week8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * .
 */
interface GetDataService {

    @GET("/users")
    Call<List<User>> getAllUsers();

    // Add other EP here

}
