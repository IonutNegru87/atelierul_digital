package com.inegru.android.atelieruldigital.helloworld.week6.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * .
 */
public final class NameUtils {

    private NameUtils() {
    }

    public static String getFragmentName(@NonNull Fragment fragment) {
        String className = fragment.getClass().getSimpleName();
        // Remove "Fragment" from the class name as the naming convention usually requires this
        return className.replace("Fragment", "");
    }

}
