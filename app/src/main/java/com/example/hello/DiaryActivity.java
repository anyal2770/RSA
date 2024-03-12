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

public class DiaryActivity extends AppCompatActivity{
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_page); // Set the layout first

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the visibility of your layouts as needed
                Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EditText editText = findViewById(R.id.diaryText);

        editText.setHint("Start Typing here");


        position = getIntent().getIntExtra("position", 0);

        String savedText = getSavedText(position);
        if (!savedText.isEmpty()) {
            editText.setText(savedText);
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    editText.setHint("");
                } else {
                    editText.setHint("Start Typing Here");
                }

                saveText(position, editable.toString());
            }
        });
    }

    // Move these methods outside of onCreate()
    private void saveText(int position, String text) {//I changed these names
        SharedPreferences sharedPreferences = getSharedPreferences("saveText", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text" + position, text);
        editor.apply();
    }

    private String getSavedText(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("saveText", Context.MODE_PRIVATE);
        return sharedPreferences.getString("text" + position, "");
    }
}





