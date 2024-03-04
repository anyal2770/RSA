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
    EditText reasonInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageAdapter adapter = new ImageAdapter(MainActivity.this);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewpager);


        pickMoodButton = findViewById(R.id.mood_button);


        pickMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageAdapter adapter = new ImageAdapter(MainActivity.this);
                viewPager.setAdapter(adapter);
                pickMoodButton.setVisibility(View.GONE);
            }
        });
    }
}

