package com.loopeer.android.librarys.dropindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

public class SelectView extends View {
    private Path mPath;
    private int position;
    private int mWidth;
    private int mHeight;
    private float radius;
    private Paint mPaint;
    private float leftCircleX;
    private float rightCircleX;
    private int leftCircleY;
    private int circlePositionY;

    public SelectView(Context context) {
        this(context, null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.select_color));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        leftCircleX = mWidth / 4 + leftCircleX;
        rightCircleX = leftCircleX;
        leftCircleY = mHeight / 2;
        circlePositionY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawOwnPath(canvas);
        drawOwnPathBeize(canvas);
    }

    private void drawOwnPathBeize(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(leftCircleX, leftCircleY + radius);
        mPath.addArc(new RectF(
                        leftCircleX - radius,
                        leftCircleY - radius, leftCircleX + radius,
                        leftCircleY + radius),
                90, 180);
        mPath.cubicTo(
                leftCircleX, leftCircleY - radius,
                leftCircleX + (rightCircleX - leftCircleX) / 2, leftCircleY - circlePositionY,
                rightCircleX, leftCircleY - radius);

        mPath.addArc(new RectF(
                        rightCircleX - radius, leftCircleY - radius,
                        rightCircleX + radius, leftCircleY + radius),
                -90, 180);
        mPath.cubicTo(
                rightCircleX, leftCircleY + radius,
                leftCircleX + (rightCircleX - leftCircleX) / 2, leftCircleY + circlePositionY,
                leftCircleX, leftCircleY + radius);
//        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }

    private void drawOwnPath(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(leftCircleX, leftCircleY + radius);
        mPath.addArc(new RectF(leftCircleX - radius, leftCircleY - radius, leftCircleX + radius, leftCircleY + radius), 90, 180);
        mPath.lineTo(rightCircleX, leftCircleY - radius);

        mPath.addArc(new RectF(rightCircleX - radius, leftCircleY - radius, rightCircleX + radius, leftCircleY + radius), -90, 180);
        mPath.lineTo(leftCircleX, leftCircleY + radius);
//        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public float getLeftCircleX() {
        return leftCircleX;
    }

    public void setLeftCircleX(float leftCircleX) {
        this.leftCircleX = leftCircleX;
        invalidate();
    }

    public float getRightCircleX() {
        return rightCircleX;
    }

    public void setRightCircleX(float rightCircleX) {
        this.rightCircleX = rightCircleX;
        invalidate();
    }

    public int getCirclePositionY() {
        return circlePositionY;
    }

    public void setCirclePositionY(int circlePositionY) {
        this.circlePositionY = circlePositionY;
        invalidate();
    }

    public static final Property<SelectView, Float> LEFT_CIRCLE_X =
            new Property<SelectView, Float>(Float.class, "leftCircleX") {
                @Override
                public Float get(SelectView object) {
                    return object.getLeftCircleX();
                }

                @Override
                public void set(SelectView object, Float value) {
                    object.setLeftCircleX(value);
                }
            };

    public static final Property<SelectView, Float> RIGHT_CIRCLE_X =
            new Property<SelectView, Float>(Float.class, "rightCircleX") {
                @Override
                public Float get(SelectView object) {
                    return object.getRightCircleX();
                }

                @Override
                public void set(SelectView object, Float value) {
                    object.setRightCircleX(value);
                }
            };

    public static final Property<SelectView, Integer> CIRCLE_POSITION_Y =
            new Property<SelectView, Integer>(Integer.class, "circlePositionY") {
                @Override
                public Integer get(SelectView object) {
                    return object.getCirclePositionY();
                }

                @Override
                public void set(SelectView object, Integer value) {
                    object.setCirclePositionY(value);
                }
            };
}
