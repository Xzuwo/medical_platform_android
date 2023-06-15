package com.example.medical_platform_android.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Paint mPaint;
//下划线类，传入下划线的颜色和宽度
//    fragment_home_content_list.addItemDecoration(new DividerItemDecoration(Color.GRAY, 3));
    public DividerItemDecoration(int color, int height) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStrokeWidth(height);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = top + (int) mPaint.getStrokeWidth();
            canvas.drawLine(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, (int) mPaint.getStrokeWidth());
    }
}
