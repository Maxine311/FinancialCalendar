package com.example.maxine.myapplication12;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

import java.util.List;

import static com.example.maxine.myapplication12.MainActivity.event1;
import static com.example.maxine.myapplication12.MainActivity.event10;
import static com.example.maxine.myapplication12.MainActivity.event11;
import static com.example.maxine.myapplication12.MainActivity.event2;
import static com.example.maxine.myapplication12.MainActivity.event3;
import static com.example.maxine.myapplication12.MainActivity.event4;
import static com.example.maxine.myapplication12.MainActivity.event5;
import static com.example.maxine.myapplication12.MainActivity.event6;
import static com.example.maxine.myapplication12.MainActivity.event7;
import static com.example.maxine.myapplication12.MainActivity.event8;
import static com.example.maxine.myapplication12.MainActivity.event9;

/**
 * 精致星系月视图，利用三角函数计算坐标
 * solar MonthView ,using trigonometric function
 * Created by huanghaibin on 2018/2/8.
 */

public class SolarMonthView extends MonthView {


    private Paint mPointPaint = new Paint();

    private int mRadius;
    private int mPointRadius;

    public SolarMonthView(Context context) {
        super(context);


        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);
        mSchemePaint.setStyle(Paint.Style.STROKE);
        mSchemePaint.setStrokeWidth(dipToPx(context, 1.2f));
        mSchemePaint.setColor(0xFFFFFFFF);
        mPointRadius = dipToPx(context, 3.6f);
        mPointPaint.setColor(Color.RED);

        //兼容硬件加速无效的代码
        setLayerType(View.LAYER_TYPE_SOFTWARE, mPointPaint);
        //4.0以上硬件加速会导致无效
        mPointPaint.setMaskFilter(new BlurMaskFilter(28, BlurMaskFilter.Blur.SOLID));

        setLayerType(View.LAYER_TYPE_SOFTWARE, mSchemePaint);
        mSchemePaint.setMaskFilter(new BlurMaskFilter(28, BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected void onPreviewHook() {
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);
//
        List<Calendar.Scheme> schemes = calendar.getSchemes();

        //schemes.get(0) 顏色順序
        //紅綠黃藍灰紫
        if(event1 == true | event2 == true) {
            mPointPaint.setColor(schemes.get(0).getShcemeColor());
            int rightTop6X = (int) (cx-50 );
            int rightTop6Y = (int) (cy+40 );
            canvas.drawCircle(rightTop6X, rightTop6Y, mPointRadius, mPointPaint); }

        if(event3 == true | event4 == true) {
            mPointPaint.setColor(schemes.get(1).getShcemeColor());
            int rightTop4X = (int) (cx-25 );
            int rightTop4Y = (int) (cy+40 );
            canvas.drawCircle(rightTop4X, rightTop4Y, mPointRadius, mPointPaint);}

        if(event5 == true) {
            mPointPaint.setColor(schemes.get(2).getShcemeColor());
            int rightTop1X = (int) (cx );
            int rightTop1Y = (int) (cy+40 );
            canvas.drawCircle(rightTop1X, rightTop1Y, mPointRadius, mPointPaint);}

        if(event6 == true | event7 == true) {
            mPointPaint.setColor(schemes.get(3).getShcemeColor());
            int rightTop2X = (int) (cx+25 );
            int rightTop2Y = (int) (cy+40 );
            canvas.drawCircle(rightTop2X, rightTop2Y, mPointRadius, mPointPaint);}

        if(event8 == true | event9 == true | event10 == true | event11 == true) {
            mPointPaint.setColor(schemes.get(4).getShcemeColor());
            int rightTop3X = (int) (cx+50 );
            int rightTop3Y = (int) (cy+40 );
            canvas.drawCircle(rightTop3X, rightTop3Y, mPointRadius, mPointPaint);}

        mPointPaint.setColor(schemes.get(5).getShcemeColor());
        int rightTop7X = (int) (cx+50 );
        int rightTop7Y = (int) (cy-20 );
        canvas.drawCircle(rightTop7X, rightTop7Y, mPointRadius, mPointPaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    mSelectTextPaint);
        } else if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
