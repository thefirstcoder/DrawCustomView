package com.sfg.administrator.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/22.
 */

public class MusicRectView extends TextView {

    private float mRectWidth = 2;
    private float mRectCount = 10;
    private float offsetWidth = 17;
    private int mWidth;
    private int mHeight;
    private Paint paint;

    public MusicRectView(Context context) {
        super(context);
    }

    public MusicRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    public MusicRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i<mRectCount; i++){
            double random = Math.random();
            double currentHeight = random * getMeasuredHeight();
            canvas.drawRect((float) (mRectWidth * i+offsetWidth),
                    (float)currentHeight,
                    (float) (mRectWidth * (i+1)),
                    mHeight,paint);
        }
        postInvalidateDelayed(100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mRectWidth= (float) (mWidth *0.8/mRectCount);
        paint.setShader(new LinearGradient(0,0,mWidth,mHeight,new int[]{Color.RED,Color.GREEN,Color.BLUE},
                 null,Shader.TileMode.CLAMP));
    }
}
