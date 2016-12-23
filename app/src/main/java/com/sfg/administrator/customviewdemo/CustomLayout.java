package com.sfg.administrator.customviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CustomLayout extends LinearLayout {

    public CustomLayout(Context context) {
        super(context);
        init();
    }

    private void init() {

    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if(widthMode!=MeasureSpec.EXACTLY){
            widthSize=widthSize>px2dp(200)?widthSize:px2dp(200);
            setMeasuredDimension(widthSize,widthSize);
        }else{
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    private int px2dp(int px){
        float density = getResources().getDisplayMetrics().density;
        return (int) (px/density+0.5);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //加载完xml后回调
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //控件大小改变
    }
}
