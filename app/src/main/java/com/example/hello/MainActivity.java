package com.example.hello;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    Button pickMoodButton;
    Button pickSleepButton;
    Button diaryButton;
    Button friendButton;
    Button resourcesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        pickMoodButton = findViewById(R.id.mood_button);
        Button backButton = findViewById(R.id.back_button);
        diaryButton = findViewById(R.id.diary_button);
        pickSleepButton = findViewById(R.id.sleep_button);
        backButton.setVisibility(View.GONE);
        friendButton = findViewById(R.id.friend_button);
        resourcesButton = findViewById(R.id.resources_button);

        //Button qual = findViewById(R.id.quality_button);
        pickMoodButton.setOnClickListener(new View.OnClickListener() {
            ;
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, layoutClass.class);
                startActivity(intent);
                //ViewPager viewPager = findViewById(R.id.viewpager);
                //ImageAdapter adapter = new ImageAdapter(MainActivity.this); // Use MainActivity.this
                //viewPager.setAdapter(adapter);

            }
        });
        pickSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sleepClass.class);
                startActivity(intent);
            }
        });
        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle friend_button click event here
                Intent intent = new Intent(MainActivity.this, friendsActivity.class);
                startActivity(intent);
            }
        });

        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, resourcesActivity.class);
                startActivity(intent);
            }
        });
        /*qual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SleepQuality.class);
                startActivity(intent);
            }
        });
        */

    }
}
