package com.example.hello;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class friendsActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 123;
    private SharedPreferences sharedPreferences;

    // Define constants for layout IDs
    private static final int LAYOUT_FRIENDS_PAGE_ID = R.layout.friends_page;
    private static final int BUTTON_SAVE_NUMBER_ID = R.id.savenumber;
    private static final int EDIT_TEXT_PHONE_NUMBER_ID = R.id.editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_FRIENDS_PAGE_ID); // Set the layout first

        sharedPreferences = getSharedPreferences("sleep_data", Context.MODE_PRIVATE);

        // Adding OnClickListener for the save button
        Button saveButton = findViewById(BUTTON_SAVE_NUMBER_ID);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered phone number
                EditText editText2 = findViewById(EDIT_TEXT_PHONE_NUMBER_ID);
                String phoneNumber = editText2.getText().toString().trim();

                // Validate phone number format
                if (isValidPhoneNumber(phoneNumber)) {
                    // Save the phone number to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("friend_phone_number", phoneNumber);
                    editor.apply();

                    // Send a hello message to the friend
                    sendHelloMessage(phoneNumber);
                } else {
                    // Display error message for invalid phone number format
                    editText2.setError("Invalid phone number");
                }
            }
        });
    }

    // Method to validate phone number format
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Your validation logic here
        // For example, you can use regular expressions to check if the phone number matches a specific format
        // This example assumes that a valid phone number consists of digits only and has a length between 7 and 15 characters
        return phoneNumber.matches("\\d{7,15}");
    }

    // Method to send SMS with specified message text
    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            // SMS sent successfully, you can show a toast or perform any action here
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            // Failed to send SMS, you can show a toast or perform any action here
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to send "Hello!" message to the friend
    private void sendHelloMessage(String phoneNumber) {
        String message = "Hello! A new friend has been added.";
        sendSMS(phoneNumber, message);
    }
}
