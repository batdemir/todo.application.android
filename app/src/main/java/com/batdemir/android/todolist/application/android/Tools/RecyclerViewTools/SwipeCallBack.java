package com.batdemir.android.todolist.application.android.Tools.RecyclerViewTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.android.todolist.application.android.R;

public abstract class SwipeCallBack extends ItemTouchHelper.Callback {
    private Context mContext;
    private Paint mClearPaint;
    private ColorDrawable mBackground;
    private int backgroundColor;
    private Drawable drawable;
    private int intrinsicWidth;
    private int intrinsicHeight;
    private boolean isDelete = false;

    public SwipeCallBack(Context context, Boolean isSmallIcon, boolean isDelete) {
        this.mContext = context;
        this.isDelete = isDelete;

        this.mBackground = new ColorDrawable();
        this.mClearPaint = new Paint();
        this.mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        if (isDelete) {
            this.backgroundColor = mContext.getColor(R.color.secondaryColor);
            if (isSmallIcon) {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_garbage_mini);
            } else {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_garbage_medium);
            }
        } else {
            this.backgroundColor = mContext.getColor(R.color.green);
            if (isSmallIcon) {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_success_mini);
            } else {
                drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_success_medium);
            }
        }

        this.intrinsicWidth = drawable.getIntrinsicWidth();
        this.intrinsicHeight = drawable.getIntrinsicHeight();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if(isDelete){
            return makeMovementFlags(0,ItemTouchHelper.LEFT);
        }else {
            return makeMovementFlags(0, ItemTouchHelper.RIGHT);
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();
        boolean isCancelled = dX == 0 && !isCurrentlyActive;
        if (isCancelled) {
            if(isDelete){
                clearCanvas(c,itemView.getRight()+dX,(float) itemView.getTop(), (float) itemView.getLeft(), (float) itemView.getBottom());
            }else {
                clearCanvas(c, itemView.getLeft() + dX, (float) itemView.getTop(), (float) itemView.getLeft(), (float) itemView.getBottom());
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        mBackground.setColor(backgroundColor);
        if(isDelete){
            mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        }else {
            mBackground.setBounds(itemView.getLeft() + (int) dX, itemView.getTop(), itemView.getLeft(), itemView.getBottom());
        }
        mBackground.draw(c);

        if (isDelete) {
            int deleteIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
            int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
            int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
            int deleteIconRight = itemView.getRight() - deleteIconMargin;
            int deleteIconBottom = deleteIconTop + intrinsicHeight;
            drawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
            drawable.draw(c);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else {
            int completeIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
            int completeIconMargin = (itemHeight - intrinsicHeight) / 2;
            int completeIconLeft = itemView.getLeft() + completeIconMargin;
            int completeIconRight = itemView.getLeft() + completeIconMargin + intrinsicWidth;
            int completeIconBottom = completeIconTop + intrinsicHeight;
            drawable.setBounds(completeIconLeft, completeIconTop, completeIconRight, completeIconBottom);
            drawable.draw(c);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, mClearPaint);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}
