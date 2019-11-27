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

    /**
     * Get the full name of the user as: "[FIRST_NAME] [LAST_NAME]".
     *
     * @return The full name of the user.
     */
    @NonNull
    String getFullName() {
        return firstName + " " + lastName;
    }
}
