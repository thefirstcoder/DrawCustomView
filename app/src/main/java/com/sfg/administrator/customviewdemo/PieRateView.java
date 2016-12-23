package com.sfg.administrator.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/22.
 */

public class PieRateView extends View {


    private float mSweepAngle = 90;
    private String mShowText = "morenmoren";

    public PieRateView(Context context) {
        super(context);
        init();
    }

    public PieRateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public PieRateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {


    }

    @Override
    protected void onDraw(Canvas canvas) {

        int mViewWidth = getMeasuredWidth();
        int mViewHeight = getMeasuredHeight();
        int length = mViewHeight<mViewWidth?mViewHeight:mViewWidth;
        float mRadius = length/4;
        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.CYAN);
        canvas.drawCircle(mViewWidth/2,mViewHeight/2,mRadius,circlePaint);
        RectF rectF = new RectF();
        rectF.set(mViewWidth/2-length/4-20,mViewHeight/2-length/4-20,mViewWidth/2+length/4+20,mViewHeight/2+length/4+20);

        Paint arcPaint = new Paint();
        arcPaint.setColor(Color.BLUE);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(10);
        arcPaint.setAntiAlias(true);
        canvas.drawArc(rectF,-95,mSweepAngle,false,arcPaint);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        canvas.drawText(mShowText,77,mShowText.length(),textPaint);
        super.onDraw(canvas);
    }
}
