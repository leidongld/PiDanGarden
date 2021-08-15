package com.openld.viewbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.openld.toolkit.ScreenUtils;

/**
 * author: lllddd
 * created on: 2021/8/15 10:50
 * description:圆形图片
 */
public class RoundImageView extends AppCompatImageView {
    private int mEdgeColor;

    private int mEdgeWidth;

    private int mWidth;

    private int mHeight;

    private int mCenterX;

    private int mCenterY;

    private int mRadius;

    private Path mCirclePath;

    private Paint mStrokePaint;

    public RoundImageView(@NonNull Context context) {
        this(context, null);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mEdgeColor = typedArray.getColor(R.styleable.RoundImageView_edgeColor, getResources().getColor(R.color.black));
        mEdgeWidth = (int) typedArray.getDimension(R.styleable.RoundImageView_edgeWidth, ScreenUtils.dp2px(2));
        typedArray.recycle();

        mCirclePath = new Path();

        // 初始化外边框线画笔
        initStrokePaint();
    }

    /**
     * 初始化外边框线画笔
     */
    private void initStrokePaint() {
        mStrokePaint = new Paint();
        mStrokePaint.setColor(mEdgeColor);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokePaint.setStrokeWidth(mEdgeWidth);
        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRadius = Math.max(mWidth, mHeight) >> 1;
        mCenterX = mRadius;
        mCenterY = mRadius;
        setMeasuredDimension(mRadius << 1, mRadius << 1);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        mCirclePath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);
        canvas.clipPath(mCirclePath);
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mRadius - (mEdgeWidth >> 1), mStrokePaint);
    }
}
