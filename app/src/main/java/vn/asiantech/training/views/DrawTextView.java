package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by phuong on 13/12/2016.
 */

public class DrawTextView extends View {
    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        Path path = new Path();
        path.moveTo(50, 50);
        path.cubicTo(500, 100, 100, 500, 1000, 500);
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(100);

        //drawTextOnPath(text, path, hOffset, vOffset, paint)
        paint.setColor(Color.GREEN);
        canvas.drawTextOnPath("--- Android.Coding ---", path, 10, 0, paint);
        //set font cho 1 string
    }
}
