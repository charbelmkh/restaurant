package com.domain.restaurant.core.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.domain.restaurant.R;

public class PriceTiersView extends LinearLayoutCompat {

    private static final String TAG = "PriceTiersView";

    public PriceTiersView(@NonNull Context context) {
        super(context);
        Log.d(TAG, "1");
    }

    public PriceTiersView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTiersView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "3");
    }

    public void setTiers(int tiers, String symbol) {
        int childCount = getChildCount();
        int tobeHighlight = Math.min(childCount, tiers);
        AppCompatTextView appCompatTextView;
        for (int i = 0; i < tobeHighlight; i++) {
            updateChildColor(symbol, i, R.color.red);
        }
        for (int i = tobeHighlight; i <childCount; i++) {
            updateChildColor(symbol, i, R.color.greyDark);
        }
    }

    private void updateChildColor(String symbol, int i, int p) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) getChildAt(i);
        appCompatTextView.setTextColor(ContextCompat.getColor(getContext(), p));
        appCompatTextView.setText(symbol);
    }
}
