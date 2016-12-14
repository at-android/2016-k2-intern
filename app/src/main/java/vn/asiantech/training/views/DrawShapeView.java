package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by phuong on 11/12/2016.
 */

public class DrawShapeView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();

    public DrawShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.rgb(0, 0, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        drawRec(canvas);
        drawCircle(canvas);
        drawOval(canvas);
        drawStar(canvas);
        drawShape5(canvas);
        drawShape6(canvas);
        drawShape7(canvas);
        drawShape8(canvas);
    }

    public void drawRec(Canvas canvas) {
        canvas.drawRect(100, 100, 300, 200, mPaint);
    }

    public void drawCircle(Canvas canvas) {
        canvas.drawCircle(500, 150, 50, mPaint);
    }

    public void drawOval(Canvas canvas) {
        canvas.drawOval(100, 300, 300, 400, mPaint);
    }

    public void drawStar(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(350, 300);
        mPath.lineTo(650, 300);
        mPath.lineTo(400, 400);
        mPath.lineTo(500, 250);
        mPath.lineTo(600, 400);
        mPath.lineTo(350, 300);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape5(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(100, 500);
        mPath.lineTo(300, 500);
        mPath.lineTo(300, 550);
        mPath.lineTo(100, 600);
        mPath.lineTo(100, 500);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape6(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(450, 500);
        mPath.lineTo(650, 500);
        mPath.lineTo(550, 600);
        mPath.lineTo(350, 600);
        mPath.lineTo(450, 500);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape7(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(100, 700);
        mPath.lineTo(200, 800);
        mPath.lineTo(100, 800);
        mPath.lineTo(100, 700);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape8(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(350, 800);
        mPath.lineTo(450, 700);
        mPath.lineTo(550, 800);
        mPath.lineTo(350, 800);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
