package com.sfg.administrator.customviewdemo.qqSlide;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/12/26.
 */

public class QQSlideFrameLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mMainView;

    public QQSlideFrameLayout(Context context) {
        super(context);
    }

    public QQSlideFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        //当用户触摸到view时候
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }
        //拖曳状态变化时调用，idle,dragging等
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        //位置改变时候回调，比如缩放，变色等
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        //何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //当前按压在mainView上时开始检测触摸
            return mMainView == child;
        }
        //处理垂直滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }
        //处理平行滑动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.e("horizontal",left+"");
            if(left>550){
                return 550;
            }
            if(left<0){
                return 0;
            }
            return left;
        }
        //滑动结束后调用
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(mMainView.getLeft()<500){
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(QQSlideFrameLayout.this);
            }else{
                mViewDragHelper.smoothSlideViewTo(mMainView,400,0);
                ViewCompat.postInvalidateOnAnimation(QQSlideFrameLayout.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
