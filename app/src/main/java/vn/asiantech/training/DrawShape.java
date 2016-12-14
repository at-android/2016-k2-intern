package vn.asiantech.training;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 11/12/2016.
 */

public class DrawShape extends View {
    private final int PAINT_COLOR = Color.BLACK;
    private Paint mDrawPaint;
    private final static int MARGIN_TOP = 80;

    public DrawShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        mDrawPaint = new Paint();
        mDrawPaint.setColor(PAINT_COLOR);
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(5);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(50, 20 + MARGIN_TOP, 200, 100 + MARGIN_TOP, mDrawPaint);
        canvas.drawCircle(400, 50 + MARGIN_TOP, 30, mDrawPaint);
        canvas.drawOval(50, 140 + MARGIN_TOP, 200, 220 + MARGIN_TOP, mDrawPaint);
        makeAStar(canvas);
        VeHinhThang(canvas, 50, 270 + MARGIN_TOP);
        VeHinhBinhHanh(canvas, 360, 270 + MARGIN_TOP);
        VeTamGiacVuong(canvas, 50, 400 + MARGIN_TOP);
        VeTamGiacDeu(canvas, 360, 500 + MARGIN_TOP);
        DrawIconLaugh(canvas, 350, 550 + MARGIN_TOP);
        DrawIconMouth(canvas, 400, 730 + MARGIN_TOP);
        DrawIconSilent(canvas, 100, 730 + MARGIN_TOP);
        DrawHeart(canvas, 100, 600 + MARGIN_TOP);
    }

    public void makeAStar(Canvas canvas) {
        float mid = 600 / 2;
        float min = Math.min(200, 400);
        min = 300;
        float half = min / 2;
        mid = mid - half;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        path.reset();
        paint.setStyle(Paint.Style.FILL);
        int distance = 150;
        // top left
        path.moveTo(mid + half * 0.5f + distance, half * 0.84f + distance - 90);
        // top right
        path.lineTo(mid + half * 1.5f + distance, half * 0.84f + distance - 90);
        // bottom left
        path.lineTo(mid + half * 0.68f + distance, half * 1.45f + distance - 90);
        // top tip
        path.lineTo(mid + half * 1.0f + distance, half * 0.5f + distance - 90);
        // bottom right
        path.lineTo(mid + half * 1.32f + distance, half * 1.45f + distance - 90);
        // top left
        path.lineTo(mid + half * 0.5f + distance, half * 0.84f + distance - 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void VeHinhThang(Canvas canvas, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 150, y);
        path.lineTo(x + 150, y + 30);
        path.lineTo(x, y + 100);
        path.lineTo(x, y);
        path.close();
        canvas.drawPath(path, mDrawPaint);
    }

    public void VeHinhBinhHanh(Canvas canvas, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 150, y);
        path.lineTo(x + 150 - 70, y + 50);
        path.lineTo(x - 70, y + 50);
        path.lineTo(x, y);
        path.close();
        canvas.drawPath(path, mDrawPaint);
    }

    public void VeTamGiacVuong(Canvas canvas, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x, y + 100);
        path.lineTo(x + 100, y + 100);
        path.lineTo(x, y);
        path.close();
        canvas.drawPath(path, mDrawPaint);
    }

    public void VeTamGiacDeu(Canvas canvas, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 100, y);
        path.lineTo(x + 50, y - 86.6f);
        path.lineTo(x, y);
        path.close();
        canvas.drawPath(path, mDrawPaint);
    }

    public void DrawIconLaugh(Canvas canvas, float x, float y) {
        RectF rectF = new RectF(x, y, x + 100, y + 100);
        canvas.drawArc(rectF, 55, 280, true, mDrawPaint);
        canvas.drawCircle(x + 20, y + 32, 10, mDrawPaint);
    }

    public void DrawIconMouth(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, 45, mDrawPaint);
        RectF rectF = new RectF(x, y - 25, x + 20, y - 5);
        canvas.drawArc(rectF, 0, 180, false, mDrawPaint);
        RectF rectF2 = new RectF(x - 20, y - 25, x, y - 5);
        canvas.drawArc(rectF2, 0, 180, false, mDrawPaint);
        canvas.drawCircle(x, y + 10, 5, mDrawPaint);
    }

    public void DrawIconSilent(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, 45, mDrawPaint);
        canvas.drawCircle(x - 17, y - 10, 10, mDrawPaint);
        canvas.drawCircle(x + 15, y - 10, 10, mDrawPaint);
        canvas.drawLine(x, y, x, y + 20, mDrawPaint);
    }

    public void DrawHeart(Canvas canvas, float x, float y) {
        RectF rectF = new RectF(x, y - 25, x + 60, y + 20);
        canvas.drawArc(rectF, 180, 180, false, mDrawPaint);
        RectF rectF2 = new RectF(x - 60, y - 25, x, y + 20);
        canvas.drawArc(rectF2, 180, 180, false, mDrawPaint);
        Path path = new Path();
        path.moveTo(x, y + 80);
        path.lineTo(x + 60, y);
        path.moveTo(x, y + 80);
        path.lineTo(x - 60, y);
        path.close();
        canvas.drawPath(path, mDrawPaint);
    }


}
