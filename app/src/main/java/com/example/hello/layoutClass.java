package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class layoutClass extends AppCompatActivity {

    // Button pickSleepButton;
    Button backButton;
    Button pickMoodButton;
    Button pickSleepButton;
    Button friendsButton;
    EditText addReason;
    Button diaryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewpager);
        ImageAdapter adapter = new ImageAdapter(layoutClass.this); // Use MainActivity.this
        viewPager.setAdapter(adapter);

        //pickSleepButton = findViewById(R.id.pick_sleep_button);

        backButton = findViewById(R.id.back_button);
        pickMoodButton = findViewById(R.id.mood_button);
        diaryButton = findViewById(R.id.diary_button);
        friendsButton = findViewById(R.id.friend_button);
        pickSleepButton = findViewById(R.id.sleep_button);
        backButton.setVisibility(View.VISIBLE);

        pickMoodButton.setVisibility(View.GONE);
        pickSleepButton.setVisibility(View.GONE);
        diaryButton.setVisibility(View.GONE);
        friendsButton.setVisibility(View.GONE);
        // pickSleepButton.setVisibility(View.GONE); // Hide pickSleepButton initially

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Set the visibility of your layouts as needed
                Intent intent = new Intent(layoutClass.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

