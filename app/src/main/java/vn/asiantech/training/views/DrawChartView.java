package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by phuong on 12/12/2016.
 */

public class DrawChartView extends View {
    private Paint mPaint = new Paint();

    public DrawChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);

        canvas.drawLine(10, 10, 10, 700, mPaint);
        canvas.drawLine(10, 700, 700, 700, mPaint);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (i = 0; i < 3; i++) {

            int a = 100;
            int b = 80;
            int c = 50;

            mPaint.setTextSize(30);
            canvas.drawText(getResources().getString(R.string.goc_toa_do), 10, 740, mPaint);
            canvas.drawText(getResources().getString(R.string.chart2_value), 280, 200, mPaint);
            canvas.drawText(getResources().getString(R.string.chart2_name), 280, 740, mPaint);
            canvas.drawText(getResources().getString(R.string.chart1_value), 90, 92, mPaint);
            canvas.drawText(getResources().getString(R.string.chart1_name), 90, 740, mPaint);
            canvas.drawText(getResources().getString(R.string.chart3_value), 450, 380, mPaint);
            canvas.drawText(getResources().getString(R.string.chart3_name), 450, 740, mPaint);
            if (i == 0) {
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(40, 700 - a * 6, 190, 700, mPaint);
            }
            if (i == 1) {
                mPaint.setColor(Color.GRAY);
                canvas.drawRect(i * 150 + 70, 700 - b * 6, i * 150 + 220, 700, mPaint);
            }
            if (i == 2) {
                mPaint.setColor(Color.RED);
                canvas.drawRect(i * 150 + 100, 700 - c * 6, i * 150 + 250, 700, mPaint);
            }
        }
    }
}
