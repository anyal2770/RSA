package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

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
    }
}

