package com.inegru.android.atelieruldigital.helloworld.week7;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.inegru.android.atelieruldigital.helloworld.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Note that permissions above Android 6.0 (API 23) the permissions are requested at runtime
 * rather than prior to installation.
 */
public class PermissionsActivity extends AppCompatActivity {

    private static final String TAG = PermissionsActivity.class.getSimpleName();
    private static final int REQUEST_ALL_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 2;
    private static final int REQUEST_LOCATION_PERMISSION = 3;
    private static final int REQUEST_STORAGE_PERMISSION = 4;
    private TextView tvPermissionsStatus;

    /**
     * Root of the layout of this Activity.
     */
    private View rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_permissions);

        rootLayout = findViewById(R.id.root);

        tvPermissionsStatus = findViewById(R.id.tvPermissionsStatus);
        Button btnCheckPermissions = findViewById(R.id.btnCheckPermissions);
        btnCheckPermissions.setOnClickListener(v -> checkPermissions(PermissionsActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Auto check for permissions when resuming the activity
        checkPermissions(this, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            // Received permission result for camera permission.
            Log.i(TAG, "Received response for Camera permission request.");

            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "CAMERA permission has now been granted.");
                Snackbar.make(rootLayout, R.string.permission_available_camera,
                              Snackbar.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "CAMERA permission was NOT granted.");
                Snackbar.make(rootLayout, R.string.permissions_not_granted,
                              Snackbar.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_LOCATION_PERMISSION) {
            // Received permission result for location permission.
            Log.i(TAG, "Received response for Location permission request.");

            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission has been granted
                Log.i(TAG, "Location permission has now been granted.");
                Snackbar.make(rootLayout, R.string.permission_available_location,
                              Snackbar.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "CAMERA permission was NOT granted.");
                Snackbar.make(rootLayout, R.string.permissions_not_granted,
                              Snackbar.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_ALL_PERMISSIONS) {
            // TODO: 11/12/19 Check if all permissions are granted

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestPermission(@NonNull Activity activity,
                                   @NonNull String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_ALL_PERMISSIONS);
    }

    private void requestPermission(@NonNull Activity activity,
                                   @NonNull String permission,
                                   int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    private boolean isPermissionGranted(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission)
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
                final boolean isPermissionGranted = isPermissionGranted(permission);
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
            tvPermissionsStatus.setText(R.string.all_permissions_granted);
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

    public void checkCameraPermission(@NonNull View view) {
        final String permission = Manifest.permission.CAMERA;
        if (isPermissionGranted(permission)) {
            Toast.makeText(this, "Camera permission already granted.", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                                                    permission)) {
                Snackbar.make(rootLayout, R.string.permission_camera_rationale,
                              Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok,
                                   v -> requestPermission(PermissionsActivity.this, permission,
                                                          REQUEST_LOCATION_PERMISSION))
                        .show();
            } else {
                requestPermission(PermissionsActivity.this, permission, REQUEST_LOCATION_PERMISSION);
            }
        }
    }

    public void checkLocationPermission(@NonNull View view) {
        final String permission = Manifest.permission.ACCESS_COARSE_LOCATION;
        if (isPermissionGranted(permission)) {
            Toast.makeText(this, "Location permission already granted.", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                                                    permission)) {
                Snackbar.make(rootLayout, R.string.permission_location_rationale,
                              Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok,
                                   v -> requestPermission(PermissionsActivity.this, permission,
                                                          REQUEST_CAMERA_PERMISSION))
                        .show();
            } else {
                requestPermission(PermissionsActivity.this, permission, REQUEST_CAMERA_PERMISSION);
            }
        }
    }

    public void checkStoragePermission(@NonNull View view) {
        // When the write permission is granted, the read is automatically granted also
        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (isPermissionGranted(permission)) {
            Toast.makeText(this, "Storage permission already granted.", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                                                    permission)) {
                Snackbar.make(rootLayout, R.string.permission_storage_rationale,
                              Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok,
                                   v -> requestPermission(PermissionsActivity.this, permission,
                                                          REQUEST_STORAGE_PERMISSION))
                        .show();
            } else {
                requestPermission(PermissionsActivity.this, permission, REQUEST_STORAGE_PERMISSION);
            }
        }
    }
}
