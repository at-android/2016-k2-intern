package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HoangDuy on 12/12/2016.
 */
public class ChartDrawing extends View {
    public ChartDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = 100;
        int height = canvas.getHeight() / 2 + 200;
        int originX = width;
        int originY = height;
        int ox1 = canvas.getWidth();
        int oy1 = height;
        int ox2 = width;
        int oy2 = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        canvas.drawLine(originX, originY, ox1, oy1, paint);
        canvas.drawLine(originX, originY, ox2, oy2, paint);
        float stoppointAx = width + 120;
        float stoppointAy = height;
        float startpointAx = stoppointAx - 60;
        float startpointAy = 60;
        float stoppointBx = stoppointAx + 120;
        float stoppointBy = height;
        float startpointBx = stoppointBx - 60;
        float startpointBy = height - (height - 60) * 0.8f;
        paint.setTextSize(40);
        float stoppointCx = stoppointBx + 120;
        float stoppointCy = height;
        float startpointCx = stoppointCx - 60;
        float startpointCy = height - (height - 60) * 0.6f;
        canvas.drawText("60%", startpointCx, startpointCy, paint);
        canvas.drawText("80%", startpointBx, startpointBy, paint);
        canvas.drawText("100%", startpointAx, startpointAy, paint);
        canvas.drawText("A", stoppointAx - 20, stoppointAy + 50, paint);
        canvas.drawText("B", stoppointBx - 20, stoppointBy + 50, paint);
        canvas.drawText("C", stoppointCx - 20, stoppointCy + 50, paint);
        canvas.drawText("0", originX - 20, originY + 50, paint);
        canvas.drawLine(ox2, oy2, 60, 40, paint);
        canvas.drawLine(ox2, oy2, 140, 40, paint);
        canvas.drawText("y", 60, 80, paint);
        canvas.drawLine(ox1, oy1, ox1 - 60, oy1 - 40, paint);
        canvas.drawLine(ox1, oy1, ox1 - 60, oy1 + 40, paint);
        canvas.drawText("x", ox1 - 20, oy1 + 50, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(startpointAx, startpointAy, stoppointAx, stoppointAy, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(startpointBx, startpointBy, stoppointBx, stoppointBy, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(startpointCx, startpointCy, stoppointCx, stoppointCy, paint);
    }
}
