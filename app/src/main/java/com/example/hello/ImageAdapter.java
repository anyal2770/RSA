package com.example.hello;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
}
