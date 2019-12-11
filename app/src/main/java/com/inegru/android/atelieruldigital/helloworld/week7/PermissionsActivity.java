package com.inegru.android.atelieruldigital.helloworld.week7;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Note that permissions above Android 6.0 (API 23) the permissions are requested at runtime
 * rather than prior to installation.
 */
public class PermissionsActivity extends AppCompatActivity {

    private static final String TAG = PermissionsActivity.class.getSimpleName();
    private static final int REQUEST_ALL_PERMISSIONS = 1;
    private TextView tvPermissionsStatus;
    private Button btnCheckPermissions;

    private static void requestPermission(@NonNull Activity activity,
                                          @NonNull String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_ALL_PERMISSIONS);
    }

    private static boolean isPermissionGranted(@NonNull Context context,
                                               @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission)
            == PackageManager.PERMISSION_GRANTED;
    }

    private void checkPermissions(@NonNull Context context) {
        checkPermissions(context, true);
    }

    private void checkPermissions(@NonNull Context context, boolean autoRequestPermissions) {
        Log.i(TAG, "checkPermissions: verifying if all permissions are granted...");
        try {
            checkDeclaredPermissions(context, autoRequestPermissions);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "checkPermissions: failed to check for permissions.", e);
        }
    }

    private void checkDeclaredPermissions(@NonNull Context context, boolean autoRequestPermissions)
        throws PackageManager.NameNotFoundException {
        // Get the package manager from the Context
        final PackageManager packageManager = context.getPackageManager();

        // Get the package information - application info
        final PackageInfo packageInfo = packageManager
            .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);

        // Get all the requested permissions declared in Manifest
        final String[] requestedPermissions = packageInfo.requestedPermissions;

        final List<String> permissionsNotGranted = new ArrayList<>();

        // Sanity check as we can have no permissions
        if (requestedPermissions != null && requestedPermissions.length > 0) {
            // Loop through all the permissions
            for (String permission : requestedPermissions) {
                // Check if the permission was granted or not
                final boolean isPermissionGranted = isPermissionGranted(context, permission);
                Log.i(TAG,
                      "checkDeclaredPermissions: " + permission + " is " + (isPermissionGranted
                          ? "granted" : "not granted"));
                if (!isPermissionGranted) {
                    permissionsNotGranted.add(permission);
                }
            }
        }

        updatePermissionsStatus(permissionsNotGranted);
        if (permissionsNotGranted.size() > 0 && autoRequestPermissions) {
            requestPermission(this, permissionsNotGranted.toArray(new String[0]));
        }
    }

    private void updatePermissionsStatus(List<String> permissionsNotGranted) {
        Log.i(TAG, "updatePermissionsStatus: updating status...");
        if (permissionsNotGranted.isEmpty()) {
            tvPermissionsStatus.setText("All permissions are granted.");
        } else {
            StringBuilder builder = new StringBuilder(
                "The following permissions were not granted:");
            for (String permission :
                permissionsNotGranted) {
                builder.append("\n");
                builder.append(permission);
            }
            tvPermissionsStatus.setText(builder.toString());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permissions);

        tvPermissionsStatus = findViewById(R.id.tvPermissionsStatus);
        btnCheckPermissions = findViewById(R.id.btnCheckPermissions);
        btnCheckPermissions.setOnClickListener(v -> checkPermissions(PermissionsActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Auto check for permissions when resuming the activity
        checkPermissions(this, false);
    }
}
