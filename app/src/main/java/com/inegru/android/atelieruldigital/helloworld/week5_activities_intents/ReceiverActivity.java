package com.inegru.android.atelieruldigital.helloworld.week5_activities_intents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inegru.android.atelieruldigital.helloworld.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiverActivity extends AppCompatActivity {

    private TextView tvFirstName;
    private EditText etResponseMessage;
    private Button btnRespond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        initViews();
        initListeners();
        handleReceivedIntent(getIntent());
    }

    private void initListeners() {
        if (null != btnRespond) {
            btnRespond.setOnClickListener(
                view -> createAndSendIntentResponse(etResponseMessage.getText().toString()));
        }
    }

    private void handleReceivedIntent(@Nullable Intent receivedIntent) {
        if (tvFirstName == null) {
            // Something happened with our view - exit
            return;
        }

        String senderName = null;
        if (receivedIntent != null) {
            // Get the bundle from the received intent
            Bundle bundle = getIntent().getExtras();
            // Sanity check on the bundle
            if (bundle != null) {
                senderName = bundle.getString(SenderActivity.EXTRA_SENDER_NAME);
            }
        }

        if (senderName != null) {
            // Format hello message using string resource with placeholder
            String helloMessage = getString(R.string.hello_sender, senderName);
            tvFirstName.setText(helloMessage);
        } else {
            // Fallback message in case the sender name cannot be processed
            tvFirstName.setText(R.string.error_receiving_sender);
        }
    }

    private void createAndSendIntentResponse(@Nullable String response) {
        // Create an empty Intent object
        Intent intent = new Intent();
        // Set the response data in the intent bundle
        intent.putExtra(SenderActivity.EXTRA_RESPONSE_TEXT, response);
        // Set the result with the OK status
        setResult(RESULT_OK, intent);
        // Close current activity
        finish();
    }

    private void initViews() {
        tvFirstName = findViewById(R.id.tvReceivedMessage);
        etResponseMessage = findViewById(R.id.etResponseMessage);
        btnRespond = findViewById(R.id.btnRespond);
    }
}
