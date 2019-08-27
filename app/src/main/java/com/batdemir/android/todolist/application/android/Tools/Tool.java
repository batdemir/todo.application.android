package com.batdemir.android.todolist.application.android.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.batdemir.android.todolist.application.android.R;

public class Tool {

    public static void move(Context from, Class<?> to, boolean isLeft){
        Activity activity = (Activity) from;
        Intent intent = new Intent(from,to);
        from.startActivity(intent);
        if(isLeft){
            activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            activity.finish();
        }else {
            activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
            activity.finish();
        }
    }

}
