package com.batdemir.android.todolist.application.android.Tools.ButtonTools;

import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OnTouchEvent implements View.OnTouchListener {

    private String TAG = OnTouchEvent.class.getSimpleName();
    private final View view;
    private ImageView imageView;
    private ImageButton imageButton;
    private Button button;
    private TextView textView;

    public OnTouchEvent(ImageView imageView) {
        this.imageView = imageView;
        view = imageView;
    }

    public OnTouchEvent(ImageButton imageButton) {
        this.imageButton = imageButton;
        view = imageButton;
    }

    public OnTouchEvent(Button button) {
        this.button = button;
        view = button;
    }

    public OnTouchEvent(TextView textView) {
        this.textView = textView;
        view = textView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    button.getBackground().setColorFilter(0x88FFFFFF, PorterDuff.Mode.SRC_ATOP);
                    button.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    button.getBackground().clearColorFilter();
                    button.invalidate();
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
