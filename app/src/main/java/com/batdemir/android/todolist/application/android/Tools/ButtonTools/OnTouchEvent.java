package com.batdemir.android.todolist.application.android.Tools.ButtonTools;

import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class OnTouchEvent implements View.OnTouchListener {

    private String TAG = OnTouchEvent.class.getSimpleName();
    private final View view;

    public OnTouchEvent(View view) {
        this.view = view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    view.getBackground().setColorFilter(0x88FFFFFF, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    view.getBackground().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }
            return false;
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            return false;
        }
    }
}
