package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class sleepClass extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    int which;
    private SharedPreferences sharedPreferences;
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (which == 0) {
            TextView textView = (TextView) findViewById(R.id.input);
            textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
            editor.putString("input_text", textView.getText().toString());
        } else if (which == 1) {
            TextView text2View = (TextView) findViewById(R.id.output);
            text2View.setText("Hour: " + hourOfDay + " Minute: " + minute);
            editor.putString("output_text", text2View.getText().toString());
        }
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_sleep_hours); // Set the layout first
        Button savetimes = (Button) findViewById(com.example.hello.R.id.save_sleep);
        sharedPreferences = getSharedPreferences("sleep_data", Context.MODE_PRIVATE);
        Button timeButton = (Button) findViewById(R.id.sleep_button);
        Button time2Button = (Button)findViewById(R.id.wake_button);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                which = 0;
            }
        });
        time2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment time2Picker = new TimePickerFragment();
                time2Picker.show(getSupportFragmentManager(), "time2 picker");
                which = 1;
            }
        });
        Button backButton = findViewById(R.id.back_button);

        // pickSleepButton.setVisibility(View.GONE); // Hide pickSleepButton initially

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(sleepClass.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.input);
        TextView text2View = findViewById(R.id.output);
        textView.setText(sharedPreferences.getString("input_text", "time"));
        text2View.setText(sharedPreferences.getString("output_text", "time"));


        savetimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input_text and output_text from SharedPreferences
                String inputText = textView.getText().toString();
                String outputText = text2View.getText().toString();

                // Retrieve friend's phone number from SharedPreferences
                String phoneNumber = sharedPreferences.getString("friend_phone_number", "");

                SmsManager smsManager = SmsManager.getDefault();


                // Check if friend's phone number is saved
                if (!phoneNumber.isEmpty()) {
                    // Construct message with sleep times
                    String message = "Sleep Time: " + inputText + "\nWake Time: " + outputText;

                    // Send SMS with sleep times
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);

                    // Display a success message
                    Toast.makeText(sleepClass.this, "Sleep times sent to friend.", Toast.LENGTH_SHORT).show();
                } else {
                    // If friend's phone number is not saved, display an error message
                    Toast.makeText(sleepClass.this, "Friend's phone number not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}