package com.loopeer.android.librarys.dropindicator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class DrivePagerIndicator extends FrameLayout {

    private SelectView mSelectView;
    private DriveIndicator mDriveIndicator;

    private List<ValueAnimator> mAnimators;
    private int position;
    private int mWidth;
    private int mHeight;
    private int mCount = 3;
    private float radius;
    private float circleX;
    private List<PointF> mPoints;
    private AnimatorSet mSet;

    public DrivePagerIndicator(Context context) {
        this(context, null);
    }

    public DrivePagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrivePagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.drive_pager_indicator, this, true);
        mDriveIndicator = (DriveIndicator) rootView.findViewById(R.id.drive_indicator);
        mSelectView = (SelectView) rootView.findViewById(R.id.select_view);

        mAnimators = new ArrayList<>();
        mPoints = new ArrayList<>();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setPositionAndOffset(int position, float offset, int pixels) {
        this.position = position;
        mSelectAnim(position, offset);
        seekAnimator(offset);
        postInvalidate();
    }

    private void seekAnimator(float offset) {
        for (ValueAnimator animator : mAnimators) {
            animator.setCurrentPlayTime((long) (5000.0F * offset));
        }
    }

    private void mSelectAnim(int position, float offset) {
        circleX = (position) * mWidth / (mCount + 1);

        mDriveIndicator.setRadius(radius);
        mDriveIndicator.setSelectCircleX(circleX);

        mSelectView.setPosition(position);
        mSelectView.setLeftCircleX(circleX);
        mSelectView.setRadius(radius);
//        mSelectView.setVisibility(offset > 0.1 && offset < 0.9 ? View.VISIBLE : View.GONE);

        float leftX = mWidth / (mCount + 1) * (position + 1);
        float rightX = mWidth / (mCount + 1) * (position + 2);

        ObjectAnimator selectAnimator = ObjectAnimator.ofFloat(mDriveIndicator, DriveIndicator.SELECT_CIRCLE_X, leftX, rightX);
        selectAnimator.setDuration(5000L);
        selectAnimator.setInterpolator(new DecelerateInterpolator(1.2F));
        mAnimators.add(selectAnimator);

        ObjectAnimator bgLeftPositionAnimator = ObjectAnimator.ofInt(mSelectView, SelectView.CIRCLE_POSITION_Y, 0, (int) radius);
        bgLeftPositionAnimator.setDuration(5000L);
        bgLeftPositionAnimator.setInterpolator(new DecelerateInterpolator(1.5F));
        mAnimators.add(bgLeftPositionAnimator);

        ObjectAnimator bgLeftAnimator = ObjectAnimator.ofFloat(mSelectView, SelectView.LEFT_CIRCLE_X, leftX, rightX);
        bgLeftAnimator.setDuration(5000L);
        bgLeftAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimators.add(bgLeftAnimator);


        ObjectAnimator bgRightAnimator = ObjectAnimator.ofFloat(mSelectView, SelectView.RIGHT_CIRCLE_X, leftX, rightX);
        bgRightAnimator.setDuration(5000L);
        bgRightAnimator.setInterpolator(new DecelerateInterpolator(1.5F));
        mAnimators.add(bgRightAnimator);

        /*if (mSet == null) {
            mSet = new AnimatorSet();
        }
        mSet.play(bgLeftPositionAnimator);
        mSet.play(bgLeftPositionAnimator).before(bgLeftAnimator);
        mSet.play(bgLeftAnimator).with(bgRightAnimator);
        mSet.start();*/
    }


    private void clearAnim() {
        for (ValueAnimator animator : mAnimators) {
            if (animator != null) {
                animator.cancel();
            }
        }

    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCircleX() {
        return circleX;
    }

    public void setCircleX(float circleX) {
        this.circleX = circleX;
    }
}
