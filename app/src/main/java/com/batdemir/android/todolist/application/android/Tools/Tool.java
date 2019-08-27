package com.batdemir.android.todolist.application.android.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.batdemir.android.todolist.application.android.R;

public class Tool {

    private String TAG = Tool.class.getSimpleName();
    private Context context;

    public Tool(Context context) {
        this.context = context;
    }

    public void move(Class<?> to, boolean isLeft){
        try {
            Activity activity = (Activity) context;
            Intent intent = new Intent(context,to);
            context.startActivity(intent);
            if(isLeft){
                activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                activity.finish();
            }else {
                activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                activity.finish();
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    public void anim(LinearLayout linearProps){
        try {
            Animation animation  = AnimationUtils.loadAnimation(context,R.anim.slide_down);
            linearProps.startAnimation(animation);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

}
