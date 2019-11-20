package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import androidx.annotation.NonNull;

/**
 * Simple data class model that represents an User.
 */
class UserModel {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;

    UserModel(@NonNull String fn, @NonNull String ln) {
        firstName = fn;
        lastName = ln;
    }

    @NonNull
    String getFirstName() {
        return firstName;
    }

    @NonNull
    String getLastName() {
        return lastName;
    }
}
