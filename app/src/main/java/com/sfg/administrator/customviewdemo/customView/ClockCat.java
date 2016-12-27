package com.sfg.administrator.customviewdemo.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/27.
 */

public class ClockCat extends View {

    private int screenHeight;
    private int screenWidth;

    public ClockCat(Context context) {
        super(context);
        init();
    }

    public ClockCat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画出仪表盘的圆
        Paint circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(screenWidth / 2, screenHeight / 2, screenWidth / 2, circlePaint);

        Paint degreePaint = new Paint();
        degreePaint.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                degreePaint.setStrokeWidth(5);
                degreePaint.setTextSize(30);
                canvas.drawLine(screenWidth / 2, screenHeight / 2 - screenWidth / 2,
                        screenWidth / 2, screenHeight / 2 - screenWidth / 2 + 60, degreePaint);
                canvas.drawText(i + "", screenWidth / 2 - degreePaint.measureText(i + "") / 2,
                        screenHeight / 2 - screenWidth / 2 + 90, degreePaint);
            } else {
                degreePaint.setStrokeWidth(3);
                degreePaint.setTextSize(15);
                canvas.drawLine(screenWidth / 2, screenHeight / 2 - screenWidth / 2,
                        screenWidth / 2, screenHeight / 2 - screenWidth / 2 + 30, degreePaint);
                canvas.drawText(i + "", screenWidth / 2 - degreePaint.measureText(i + "") / 2,
                        screenHeight / 2 - screenWidth / 2 + 60, degreePaint);
            }
            canvas.rotate(15, screenWidth / 2, screenHeight / 2);
        }
        Paint hourPaint = new Paint();
        hourPaint.setStyle(Paint.Style.FILL);
        hourPaint.setStrokeWidth(20);
        Paint minPaint = new Paint();
        minPaint.setStyle(Paint.Style.FILL);
        minPaint.setStrokeWidth(15);
        canvas.save();
        canvas.translate(screenWidth / 2, screenHeight / 2);
        canvas.drawLine(0, 0, 100, 100, hourPaint);
        canvas.drawLine(0, 0, 100, 200, minPaint);
        canvas.restore();
        super.onDraw(canvas);
    }
}
