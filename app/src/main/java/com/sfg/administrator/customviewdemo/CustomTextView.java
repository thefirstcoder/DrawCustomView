package com.sfg.administrator.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CustomTextView extends TextView {

    private String showStr;
    private float mViewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public CustomTextView(Context context) {
        this(context,null,0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        showStr = "I will come back!ddddddddddddddddd";
        setText(showStr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate += mViewWidth/15;
            if(mTranslate>2*mViewWidth){
                mTranslate= (int) -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth=getMeasuredWidth();
            if(mViewWidth>0){
                Log.e("count","countStatic");
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                getPaint().setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    private int px2dp(int px){
        float density = getResources().getDisplayMetrics().density;
        return (int) (px/density+0.5);
    }
}
