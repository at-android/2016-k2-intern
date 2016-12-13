package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by phuong on 13/12/2016.
 */

public class DrawShape2View extends View {
    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public DrawShape2View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShap9(canvas);
        drawShap11(canvas);
    }

    public void drawShap9(Canvas canvas){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setColor(Color.BLACK); // Change the boundary color
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

        float length = 100;
        float x = 100;
        float y = 200;

        canvas.rotate(45,x,y);

        mPath.moveTo(x,y);
        mPath.lineTo(x-length, y);
        mPath.arcTo(new RectF(x-length-(length/2),y-length,x-(length/2),y),90,180);
        mPath.arcTo(new RectF(x-length,y-length-(length/2),x,y-(length/2)),180,180);
        mPath.lineTo(x,y);
        mPath.close();

        canvas.drawPath(mPath,mPaint);
    }

    public void drawShap11(Canvas canvas){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setColor(Color.BLACK); // Change the boundary color
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(200,280,100,mPaint);
        canvas.drawCircle(150,290,20,mPaint);
        canvas.drawCircle(220,220,20,mPaint);
        canvas.drawLine(200,270,230,300,mPaint);

    }
}
