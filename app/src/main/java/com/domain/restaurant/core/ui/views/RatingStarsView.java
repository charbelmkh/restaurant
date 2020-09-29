package com.domain.restaurant.core.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.domain.restaurant.R;

public class RatingStarsView extends LinearLayoutCompat {

    private static final String TAG = "RatingStarsView";

    private final int MAX_STARS = 5;


    public RatingStarsView(@NonNull Context context) {
        super(context);
        Log.d(TAG, "1");
    }

    public RatingStarsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < MAX_STARS; i++) {
            addView(layoutInflater.inflate(R.layout.start, this, false));
        }
    }

    public RatingStarsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "3");
    }

    public void setRating(int rating) {
        int tobeHighlight = Math.min(MAX_STARS, rating);
        AppCompatImageView image;
        for (int i = 0; i < tobeHighlight; i++) {
            AppCompatImageView view = (AppCompatImageView) getChildAt(i);
            view.setImageResource(R.drawable.ic_star_selected);
        }
        for (int i = tobeHighlight; i <MAX_STARS; i++) {
            AppCompatImageView view = (AppCompatImageView) getChildAt(i);
            view.setImageResource(R.drawable.ic_star_not_selected);
        }

    }
}
