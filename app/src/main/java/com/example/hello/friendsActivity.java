package com.example.hello;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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

    private static final int LAYOUT_FRIENDS_PAGE_ID = R.layout.friends_page;
    private static final int BUTTON_SAVE_NUMBER_ID = R.id.savenumber;
    private static final int EDIT_TEXT_PHONE_NUMBER_ID = R.id.editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_FRIENDS_PAGE_ID); // Set the layout first

        sharedPreferences = getSharedPreferences("sleep_data", Context.MODE_PRIVATE);

        Button saveButton = findViewById(BUTTON_SAVE_NUMBER_ID);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText2 = findViewById(EDIT_TEXT_PHONE_NUMBER_ID);
                String phoneNumber = editText2.getText().toString().trim();

                if (isValidPhoneNumber(phoneNumber)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("friend_phone_number", phoneNumber);
                    editor.apply();

                    if (checkPermission()) {
                        sendHelloMessage(phoneNumber);
                    } else {
                        requestPermission();
                    }
                } else {
                    editText2.setError("Invalid phone number");
                }
            }
        });
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(friendsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{7,15}");
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted. You can now send SMS.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied. You cannot send SMS.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendHelloMessage(String phoneNumber) {
        String message = "Hi! I just added you on Nova! It will be sending you automatic updates on how I'm doing";
        sendSMS(phoneNumber, message);
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
        }
    }
}
