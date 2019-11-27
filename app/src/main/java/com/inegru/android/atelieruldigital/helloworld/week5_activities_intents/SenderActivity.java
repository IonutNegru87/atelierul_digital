package com.inegru.android.atelieruldigital.helloworld.week5_activities_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SenderActivity extends AppCompatActivity {

    public static final String EXTRA_SENDER_NAME = "extra_sender_name";
    public static final String EXTRA_RESPONSE_TEXT = "extra_response_text";

    public static final int HANDLE_RESPONSE_TEXT_REQUEST = 1;

    private TextView tvReceivedMessage;
    private Button btnStartForResult;
    private EditText etSenderName;
    private Button btnSendToReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        setupToolbar();
        initViews();
        initListeners();
    }

    private void initListeners() {
        // Sanity check for the button view
        if (btnSendToReceiver != null) {
            // Bind the action with the OnClickListener interface
            btnSendToReceiver.setOnClickListener(view -> {
                // Get the message from the edit text view
                if (etSenderName != null && etSenderName.getText() != null) {
                    startSecondActivity(etSenderName.getText().toString());
                }
            });
        }

        // Sanity check for the button view
        if (btnStartForResult != null) {
            // Bind the action with the OnClickListener interface
            btnStartForResult.setOnClickListener(
                view -> startSecondActivityForResult("Ionut Negru"));
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
    }

    private void initViews() {
        etSenderName = findViewById(R.id.etSenderName);
        btnStartForResult = findViewById(R.id.btnStartForResult);
        btnSendToReceiver = findViewById(R.id.btnSendToReceiver);
        tvReceivedMessage = findViewById(R.id.tvReceivedMessage);
    }

    private void startSecondActivity(@Nullable String name) {
        Intent intent = new Intent(SenderActivity.this, ReceiverActivity.class);
        // Send name to the second activity
        intent.putExtra(EXTRA_SENDER_NAME, name);

        startActivity(intent);
    }

    @SuppressWarnings("SameParameterValue")
    private void startSecondActivityForResult(@Nullable String name) {
        Intent intent = new Intent(SenderActivity.this, ReceiverActivity.class);
        // Optional: send some data to the receiver activity
        intent.putExtra(EXTRA_SENDER_NAME, "From " + name);

        // The request code should be the same in the onActivityResult()
        startActivityForResult(intent, HANDLE_RESPONSE_TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (HANDLE_RESPONSE_TEXT_REQUEST == 0) {
            // We make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // Sanity check for the intent as it could be null
                if (data != null) {
                    handleReceivedMessage(data.getStringExtra(EXTRA_RESPONSE_TEXT));
                }
            }
        }
        // Otherwise ignore the result
    }

    private void handleReceivedMessage(@Nullable String message) {
        if (message != null) {
            tvReceivedMessage.setText(message);
        } else {
            // Fallback message in case the message in the bundle is not valid
            tvReceivedMessage.setText(R.string.invalid_response_message);
        }
    }
}
