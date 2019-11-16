package com.inegru.android.atelieruldigital.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * .
 */
public class LoginFormActivity extends AppCompatActivity {

    private EditText email;
    private EditText phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_form);


        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        findViewById(R.id.submit)
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email != null) {
                    if (email.getText() == null
                        || email.getText().toString().length() == 0) {
                        email.setError("Invalid email");
                    }
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no-op
            }

            @Override
            public void afterTextChanged(Editable s) {
                email.setError(null);
            }
        });
    }

    private boolean submit(Editable email, Editable phone) {
        if (email == null || phone == null) {
            return false;
        }
        if (email.toString().length() == 0
        || phone.toString().length() == 0) {
            return false;
        }
        return true;
    }
}
