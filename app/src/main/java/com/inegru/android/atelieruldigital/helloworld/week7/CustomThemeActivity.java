package com.inegru.android.atelieruldigital.helloworld.week7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class CustomThemeActivity extends AppCompatActivity {

    @Nullable
    private Snackbar snackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_theme);
    }

    /**
     * Trigger method for the {@code R.id.btnShowSnackBack} {@link Button} click.
     *
     * @param view The view that triggered the action.
     */
    public void showSnackBar(@NonNull View view) {
        // Create the Snackbar and save it's instance for later manipulation
        snackbar = Snackbar.make(view, "Hello Material", Snackbar.LENGTH_INDEFINITE);

        // Add the callback for snackbar actions
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                // The snackbar was displayed
            }

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                // The snackbar was dismissed
            }
        });

        snackbar.setAction("UNDO", v -> handleSnackAction(v, snackbar));
        snackbar.show();
    }

    private void handleSnackAction(@NonNull View v, @NonNull Snackbar snackbar) {
        // Create & show a simple toast message
        Toast.makeText(v.getContext(), "Hello from Snackbar", Toast.LENGTH_SHORT)
             .show();

        // Manually dismiss the snackbar if not already dismissed
        if (snackbar.isShown()) {
            snackbar.dismiss();
        } // else do nothing
    }
}
