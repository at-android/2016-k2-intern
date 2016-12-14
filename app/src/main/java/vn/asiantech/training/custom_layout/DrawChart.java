package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MaiManhDuy on 12/13/2016.
 */

public class DrawChart extends View {
    public DrawChart(Context context) {
        super(context);
    }

    public DrawChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawXY(canvas);
        drawChart1(canvas);
        drawChart2(canvas);
        drawChart3(canvas);
        super.onDraw(canvas);
    }

    public void drawXY(Canvas canvas) {
        Paint black = new Paint();
        black.setColor(Color.rgb(0, 0, 0));
        black.setStrokeWidth(8);
        black.setStyle(Paint.Style.STROKE);
        black.setTextSize(60f);
        canvas.drawLine(50, 50, 50, 1200, black);
        canvas.drawLine(50, 1200, 800, 1200, black);
        canvas.drawLine(50, 50, 70, 70, black);
        canvas.drawLine(50, 50, 30, 70, black);
        canvas.drawLine(800, 1200, 780, 1180, black);
        canvas.drawLine(800, 1200, 780, 1220, black);
        canvas.drawText("Y", 100, 100, black);
        canvas.drawText("X", 750, 1150, black);
        canvas.drawText("0", 20, 1270, black);
        canvas.drawText("A", 125, 1270, black);
        canvas.drawText("B", 325, 1270, black);
        canvas.drawText("C", 525, 1270, black);
    }

    public void drawChart1(Canvas canvas) {
        Paint red = new Paint();
        red.setColor(Color.rgb(223, 0, 41));
        red.setTextSize(30f);
        Path mPath1 = new Path();
        mPath1.moveTo(100, 1200);
        mPath1.lineTo(100, 200);
        mPath1.lineTo(200, 200);
        mPath1.lineTo(200, 1200);
        mPath1.moveTo(100, 1200);
        mPath1.close();
        canvas.drawPath(mPath1, red);
        canvas.drawText("100%", 125, 150, red);
    }

    public void drawChart2(Canvas canvas) {
        Paint blue = new Paint();
        blue.setColor(Color.rgb(115, 136, 193));
        blue.setTextSize(30f);
        Path mPath1 = new Path();
        mPath1.moveTo(300, 1200);
        mPath1.lineTo(300, 400);
        mPath1.lineTo(400, 400);
        mPath1.lineTo(400, 1200);
        mPath1.moveTo(300, 1200);
        mPath1.close();
        canvas.drawPath(mPath1, blue);
        canvas.drawText("80%", 325, 350, blue);
    }

    public void drawChart3(Canvas canvas) {
        Paint violet = new Paint();
        violet.setColor(Color.rgb(162, 0, 124));
        violet.setTextSize(30f);
        Path mPath1 = new Path();
        mPath1.moveTo(500, 1200);
        mPath1.lineTo(500, 700);
        mPath1.lineTo(600, 700);
        mPath1.lineTo(600, 1200);
        mPath1.moveTo(500, 1200);
        mPath1.close();
        canvas.drawPath(mPath1, violet);
        canvas.drawText("50%", 525, 650, violet);
    }
}
