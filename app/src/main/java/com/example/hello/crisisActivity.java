package com.example.hello;


import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.telephony.SmsManager;

import androidx.appcompat.app.AppCompatActivity;

public class crisisActivity extends AppCompatActivity{
    private int position;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crisis_page); // Set the layout first

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("sleep_data", MODE_PRIVATE);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(crisisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button notifyButton = findViewById(R.id.notify_button);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve friend's phone number from SharedPreferences
                String phoneNumber = sharedPreferences.getString("friend_phone_number", "");

                SmsManager smsManager = SmsManager.getDefault();

                // Check if friend's phone number is saved
                if (!phoneNumber.isEmpty()) {
                    // Construct message with crisis alert
                    String message = "Please check up on your friend as soon as possible, they are in a crisis";

                    // Send SMS with crisis alert
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);

                    // Display a success message
                    Toast.makeText(crisisActivity.this, "Friends have been alerted.", Toast.LENGTH_SHORT).show();
                } else {
                    // If friend's phone number is not saved, display an error message
                    Toast.makeText(crisisActivity.this, "Friends' phone number not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        position = getIntent().getIntExtra("position", 0);
    }

}





