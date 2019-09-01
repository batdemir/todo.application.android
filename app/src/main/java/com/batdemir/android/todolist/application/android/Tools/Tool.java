package com.batdemir.android.todolist.application.android.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.batdemir.android.todolist.application.android.R;

public class Tool {

    private String TAG = Tool.class.getSimpleName();
    private Context context;

    public Tool(Context context) {
        this.context = context;
    }

    public void move(Class<?> to, boolean isLeft, boolean isHistory) {
        try {
            Activity activity = (Activity) context;
            Intent intent = new Intent(context, to);
            context.startActivity(intent);
            if (isLeft) {
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                if (!isHistory)
                    activity.finish();
            } else {
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                if (!isHistory)
                    activity.finish();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void anim(LinearLayout linearProps) {
        try {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            linearProps.startAnimation(animation);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void animDialog(LinearLayout linearProps) {
        try {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
            linearProps.startAnimation(animation);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String getPath() {
        String[] directories = ToolStorage.getSDCardDirectory(context);
        if (directories.length > 0) {
            return directories[0] + "Android/data/" + context.getPackageName();
        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

}
