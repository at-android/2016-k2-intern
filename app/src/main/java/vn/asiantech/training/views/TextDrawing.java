package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HoangDuy on 11/12/2016.
 */
public class TextDrawing extends View {
    public final String MESSAGE_ANDROID = "Android By Google";
    public final String MESSAGE_IOS = "Ios by Apple";
    public final String MESSAGE_MICROSOFT = "Window by Microsoft";

    public TextDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        int dx = canvas.getWidth() / 6;
        int dy = canvas.getHeight() / 6;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
        path.addArc(dx, dy, dx + 650, dy + 600, 180, 60);
        canvas.drawTextOnPath(MESSAGE_ANDROID, path, 0, 0, paint);
        paint.setColor(Color.BLUE);
        Path path1 = new Path();
        path1.addArc(dx, dy, dx + 650, dy + 600, 250, 60);
        canvas.drawTextOnPath(MESSAGE_IOS, path1, 0, 0, paint);
        paint.setColor(Color.BLACK);
        Path path2 = new Path();
        path2.addArc(dx, dy, dx + 650, dy + 600, 300, 60);
        canvas.drawTextOnPath(MESSAGE_MICROSOFT, path2, 0, 0, paint);
    }
}
