package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by phuong on 11/12/2016.
 */

public class DrawShapeView extends View {
    Paint mPaint = new Paint();
    Path mPath = new Path();
    public DrawShapeView(Context context, AttributeSet attrs) {
        super(context,attrs);
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

    public void drawRec(Canvas canvas){
        canvas.drawRect(100, 100, 300, 200, mPaint);
    }

    public void drawCircle(Canvas canvas){
        canvas.drawCircle(500,150,50,mPaint);
    }

    public void drawOval(Canvas canvas){
        canvas.drawOval(100,300,300,400,mPaint);
    }

    public void drawStar(Canvas canvas){
        mPath.reset();
        // top left
        mPath.moveTo(350,300);
        // top right
        mPath.lineTo(650,300);
        // bottom left
        mPath.lineTo(400,400);
        // top tip
        mPath.lineTo(500,250);
        // bottom right
        mPath.lineTo(600,400);
        // top left
        mPath.lineTo(350,300);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape5(Canvas canvas){
        mPath.reset();
        // top left
        mPath.moveTo(100,500);
        // top right
        mPath.lineTo(300,500);
        // bottom left
        mPath.lineTo(300,550);
        // top tip
        mPath.lineTo(100,600);
        mPath.lineTo(100,500);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape6(Canvas canvas){
        mPath.reset();
        // top left
        mPath.moveTo(450,500);
        // top right
        mPath.lineTo(650,500);
        // bottom left
        mPath.lineTo(550,600);
        // top tip
        mPath.lineTo(350,600);
        mPath.lineTo(450,500);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape7(Canvas canvas){
        mPath.reset();
        // top left
        mPath.moveTo(100,700);
        // top right
        mPath.lineTo(200,800);
        // bottom left
        mPath.lineTo(100,800);
        // top tip
        mPath.lineTo(100,700);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void drawShape8(Canvas canvas){
        mPath.reset();
        // top left
        mPath.moveTo(350,800);
        // top right
        mPath.lineTo(450,700);
        // bottom left
        mPath.lineTo(550,800);
        // top tip
        mPath.lineTo(350,800);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }


}
