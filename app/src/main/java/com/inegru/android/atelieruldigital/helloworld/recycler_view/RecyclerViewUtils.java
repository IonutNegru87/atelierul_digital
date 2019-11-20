package com.inegru.android.atelieruldigital.helloworld.recycler_view;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * .
 */
final class RecyclerViewUtils {

    private RecyclerViewUtils() {
    }

    @NonNull
    static List<UserModel> getData() {
        return new ArrayList<UserModel>() {
            {
                add(new UserModel("John", "Doe"));
                add(new UserModel("Popescu", "Ion"));
                add(new UserModel("test", "test"));
                add(new UserModel("John", "Doe"));
                add(new UserModel("Popescu", "Ion"));
                add(new UserModel("test", "test"));
                add(new UserModel("John", "Doe"));
                add(new UserModel("Popescu", "Ion"));
                add(new UserModel("test", "test"));
                add(new UserModel("John", "Doe"));
                add(new UserModel("Popescu", "Ion"));
                add(new UserModel("test", "test"));
                add(new UserModel("John", "Doe"));
                add(new UserModel("Popescu", "Ion"));
                add(new UserModel("test", "test"));
            }
        };
    }
}
