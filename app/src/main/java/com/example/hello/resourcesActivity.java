package com.example.hello;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class resourcesActivity extends AppCompatActivity{
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_page); // Set the layout first

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(resourcesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button crisisButton = findViewById(R.id.crisis_button);
        crisisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(resourcesActivity.this, crisisActivity.class);
                startActivity(intent);
            }
        });

        position = getIntent().getIntExtra("position", 0);


    }
}





