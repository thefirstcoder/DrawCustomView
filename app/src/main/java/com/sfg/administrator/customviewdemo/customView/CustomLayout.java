package com.sfg.administrator.customviewdemo.customView;

import android.content.Context;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CustomLayout extends LinearLayout {

    private int widthPixels;
    private int heightPixels;
    private Scroller mScroller;
    private int mLastY;
    private int mEnd;
    private int mStart;
    private int dropUpOffset = 300;
    private int dropDownOffset = 300;

    public CustomLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mScroller = new Scroller(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            //对每一个子view进行测量
            View view = getChildAt(i);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightPixels, MeasureSpec.EXACTLY);
            measureChild(getChildAt(i), widthMeasureSpec, childHeightMeasureSpec);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        int childHeight = 0;
        for (int i = 0; i < childCount; i++) {
            childHeight += heightPixels;
        }
        mlp.height = childHeight;
        setLayoutParams(mlp);
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).getVisibility() != GONE) {
                getChildAt(i).layout(l, i * heightPixels, r, (i + 1) * heightPixels);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - heightPixels) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY>0){
                    Log.e(">0:",dScrollY+"");
                    if(dScrollY>dropUpOffset){
                        mScroller.startScroll(0,getScrollY(),0,heightPixels-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }
                }else{
                    Log.e("else:",dScrollY+"");
                    if(-dScrollY<dropDownOffset){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-(heightPixels+dScrollY));
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    private int px2dp(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //加载完xml后回调
    }
}
