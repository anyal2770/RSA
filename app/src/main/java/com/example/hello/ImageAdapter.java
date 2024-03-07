package com.example.hello;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;



public class ImageAdapter extends PagerAdapter {
    private Context mContext;
     int mimageIds[] =
             {R.layout.anxious_mood, R.layout.bored_mood, R.layout.sad_mood, R.layout.angry_mood, R.layout.content_mood,
                     R.layout.disguist_mood, R.layout.happy_mood, R.layout.flirty_mood, R.layout.surprise_mood,R.layout.tired_mood};
    ImageAdapter(Context context){
        mContext = context;
    }
    @Override
    public int getCount() {
        return mimageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view ==  object;
    }

@NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
    LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = layoutInflater.inflate(mimageIds[position], container, false);
    container.addView(view);
    EditText editText = view.findViewById(R.id.addreason);

    editText.setHint("Add a reason");


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
            // If there's text input, remove the hint
            if (editable.length() > 0) {
                editText.setHint("");
            } else {
                // If the text is cleared, restore the hint
                editText.setHint("Add a reason");
            }

            // Save the edited text
            saveText(position, editable.toString());
        }
    });
    Button selectMoodButton = view.findViewById(R.id.select_button);
    selectMoodButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "You have selected a mood!", Toast.LENGTH_SHORT).show();

        }
    });


    return view;

   // ImageView imageView = new ImageView(mContext);
       // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
       // imageView.setImageResource(mimageIds[position]);
        //container.addView(imageView, 0);
        //return imageView;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private void saveText(int position, String text) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text_" + position, text);
        editor.apply();
    }


    private String getSavedText(int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("text_" + position, "");
    }
}
