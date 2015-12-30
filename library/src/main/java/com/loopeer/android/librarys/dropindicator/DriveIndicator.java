package com.loopeer.android.librarys.dropindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

/**
 * Created by liangyifa on 2015/12/25.
 */
public class DriveIndicator extends View {
    private int mWidth;
    private int mHeight;
    private int mCount = 3;
    private Paint mPaint, mSelectPaint;
    private float radius;

    private float selectCircleX;

    public DriveIndicator(Context context) {
        this(context, null);
    }

    public DriveIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DriveIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xff00ffff);

        mSelectPaint = new Paint();
        mSelectPaint.setColor(0xff00ffff);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        selectCircleX = mWidth / 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mCount; i++) {
            canvas.drawCircle(getWidth() / (mCount + 1) * (i + 1), getHeight() / 2, radius, mPaint);
        }

        canvas.drawCircle(selectCircleX, mHeight / 2, radius, mSelectPaint);

        drawOnPath(canvas);
    }

    private void drawOnPath(Canvas canvas) {
        Path path = new Path();
//        path.cubicTo(0, 100, 50, 150, 100, 100);
//        path.cubicTo(0, 100, 50, 150, 100, 100);
        canvas.drawPath(path, mSelectPaint);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        postInvalidate();
    }

    public float getSelectCircleX() {
        return selectCircleX;
    }

    public void setSelectCircleX(float selectCircleX) {
        this.selectCircleX = selectCircleX;
        postInvalidate();
    }

    public static final Property<DriveIndicator, Float>  SELECT_CIRCLE_X =
            new Property<DriveIndicator, Float>(Float.class, "selectCircleX") {
                @Override
                public Float get(DriveIndicator object) {
                    return object.getSelectCircleX();
                }

                @Override
                public void set(DriveIndicator object, Float value) {
                    object.setSelectCircleX(value);
                }
            };
}
