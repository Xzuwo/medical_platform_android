package com.example.medical_platform_android.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HeartView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mWidth;
    private int mHeight;
    private int mColor;
    private int mAlpha = 255;

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mColor = Color.RED;
        mPaint.setColor(mColor);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        float x = mWidth / 2f;
        float y = mHeight / 2f - 40f;

        float offset = mWidth / 4f;
        mPath.moveTo(x, y);
        mPath.cubicTo(x + offset, y - offset, x + offset, y + offset, x, y + offset * 2f);
        mPath.cubicTo(x - offset, y + offset, x - offset, y - offset, x, y);

        mPaint.setAlpha(mAlpha);
        canvas.drawPath(mPath, mPaint);
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(mColor);
        invalidate();
    }

    public void setAlpha(int alpha) {
        mAlpha = alpha;
        invalidate();
    }
}

