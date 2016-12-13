package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by HoangDuy on 10/12/2016.
 */
public class Shape extends View {
    public Shape(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(getResources().getColor(R.color.colorToolbar));
        int width = 200;
        int height = 150;
        float left = getWidth() / 8;
        float top = getHeight() / 8;
        canvas.drawRect(left, top, left + width, top + height, paint);
        paint.setColor(Color.GREEN);
        int radius = 100;
        canvas.drawCircle(left + width / 2 + canvas.getWidth() / 2, top + height / 2, radius, paint);
        paint.setColor(Color.RED);
        canvas.drawOval(new RectF(left, top * 2, left + width, top * 2 + height), paint);
        drawStat(left, top, canvas, paint, width, height);
        drawShape5(top, left, canvas, paint);
        drawShape6(top, left, canvas, paint, width);
        drawShape7(top, left, canvas, paint, width);
        drawShape8(top, left, canvas, paint, width);
        drawShape9(top, left, canvas, paint, width, radius);
        drawShape10(top, left, canvas, paint, width, radius);
        drawShape11(top, left, canvas, paint, width, height, radius);
        drawHeart(top, left, canvas, paint, width, height, radius);
    }

    public void drawShape5(float top, float left, Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        float firstX = left;
        float firstY = top * 3;
        float secondX = left;
        float secondY = top * 3 + 60;
        float thirdX = left + 200;
        float thirdY = firstY;
        float forthX = thirdX;
        float forthY = 3 * top + (secondY - firstY) / 2;
        canvas.drawLine(firstX, firstY, secondX, secondY, paint);
        canvas.drawLine(firstX, firstY, thirdX, thirdY, paint);
        canvas.drawLine(thirdX, thirdY, forthX, forthY, paint);
        canvas.drawLine(forthX, forthY, secondX, secondY, paint);
    }

    public void drawShape6(float top, float left, Canvas canvas, Paint paint, int width) {
        paint.setColor(Color.GRAY);
        float firstX = left + width / 2 + canvas.getWidth() / 2 - 50;
        float firstY = top * 3;
        float secondX = firstX - 30;
        float secondY = top * 3 + 100;
        float thirdX = firstX + width;
        float thirdY = firstY;
        float forthX = thirdX - 30;
        float forthY = secondY;
        canvas.drawLine(firstX, firstY, secondX, secondY, paint);
        canvas.drawLine(firstX, firstY, thirdX, thirdY, paint);
        canvas.drawLine(thirdX, thirdY, forthX, forthY, paint);
        canvas.drawLine(secondX, secondY, forthX, forthY, paint);
    }

    public void drawShape7(float top, float left, Canvas canvas, Paint paint, int width) {
        paint.setColor(Color.YELLOW);
        float firstX = left;
        float firstY = top * 4;
        float secondX = left;
        float secondY = firstY + 100;
        float thirdX = secondX + width;
        float thirdY = secondY;
        canvas.drawLine(firstX, firstY, secondX, secondY, paint);
        canvas.drawLine(secondX, secondY, thirdX, thirdY, paint);
        canvas.drawLine(firstX, firstY, thirdX, thirdY, paint);
    }

    public void drawShape8(float top, float left, Canvas canvas, Paint paint, int width) {
        paint.setColor(getResources().getColor(R.color.colorPink));
        float firstX = left + width / 2 + canvas.getWidth() / 2 - 50;
        float firstY = top * 4;
        float secondX = firstX - 50;
        float secondY = firstY + 100;
        float thirdY = firstY + 100;
        float thirdX = firstX + 60;
        canvas.drawLine(firstX, firstY, secondX, secondY, paint);
        canvas.drawLine(firstX, firstY, thirdX, thirdY, paint);
        canvas.drawLine(thirdX, thirdY, secondX, secondY, paint);
    }

    public void drawShape9(float top, float left, Canvas canvas, Paint paint, int width, int radius) {
        paint.setColor(getResources().getColor(R.color.colorShape1));
        canvas.drawCircle(left + width / 2, top * 5 + 20, radius, paint);
        canvas.drawCircle(left + width / 3, top * 5 - 20, radius / 8, paint);
        canvas.drawLine(left + width / 2, top * 5 + 20, left + width / 2, top * 5 + 60, paint);
        canvas.translate(70, 0);
        canvas.drawCircle(left + width / 3, top * 5 - 20, radius / 8, paint);
        canvas.translate(-70, 0);
    }

    public void drawShape10(float top, float left, Canvas canvas, Paint paint, int width, int radius) {
        paint.setColor(getResources().getColor(R.color.colorShape2));
        canvas.drawCircle(left + width / 2 + canvas.getWidth() / 2 - 50, top * 5 + 20, radius, paint);
        canvas.drawCircle(left + width / 2 + canvas.getWidth() / 2 - 50, top * 5 + 60, radius / 5, paint);
        float leftRect = left + width / 2 + canvas.getWidth() / 2 - 120;
        float topRect = top * 5 - 40;
        float rightRect = left + width / 2 + canvas.getWidth() / 2 - 70;
        float bottomRect = top * 5;
        canvas.drawArc(new RectF(leftRect, topRect, rightRect, bottomRect), 0, 180, false, paint);
        canvas.translate(100, 0);
        canvas.drawArc(new RectF(leftRect, topRect, rightRect, bottomRect), 0, 180, false, paint);
        canvas.translate(-100, 0);
    }

    public void drawShape11(float top, float left, Canvas canvas, Paint paint, int width, int height, int radius) {
        paint.setColor(getResources().getColor(R.color.colorShape3));
        float leftRect = left;
        float topRect = top * 6;

        float rightRect = left + width;
        float bottomRect = topRect + height;
        canvas.drawArc(new RectF(leftRect, topRect, rightRect, bottomRect), 45, 270, true, paint);
        canvas.drawCircle(leftRect + width / 2, topRect + height / 2 - 30, radius / 8, paint);
    }

    public void drawHeart(float top, float left, Canvas canvas, Paint paint, int width, int height, int radius) {
        paint.setColor(getResources().getColor(R.color.colorShape4));
        float leftRect = left + width / 2 + canvas.getWidth() / 2 - 100;
        float topRect = top * 6;

        float rightRect = leftRect + width - 30;
        float bottomRect = topRect + height;

        float leftRect1 = rightRect;
        float topRect1 = topRect;

        float rightRect1 = leftRect1 + width - 30;
        float bottomRect1 = bottomRect;

        float middleX = leftRect1;
        float middleY = topRect + height + 100;

        canvas.drawArc(new RectF(leftRect, topRect, rightRect, bottomRect), 180, 180, false, paint);
        canvas.drawArc(new RectF(leftRect1, topRect1, rightRect1, bottomRect1), 180, 180, false, paint);
        canvas.drawLine(leftRect, topRect + height / 2, middleX, middleY, paint);
        canvas.drawLine(rightRect1, topRect + height / 2, middleX, middleY, paint);
    }

    public void drawStat(float left, float top, Canvas canvas, Paint paint, int width, int height) {
        float topStatX = left + width / 2 + canvas.getWidth() / 2;
        float topStatY = top * 2;
        float secondX = topStatX - 20;
        float secondY = top * 2 + height / 2;
        float thirdX = secondX - 90;
        float thirdY = secondY + 5;
        float fouthX = secondX - 10;
        float fouthY = thirdY + (secondY - topStatY) / 2;
        float fifthX = fouthX - 10;
        float fifthY = fouthY + secondY - topStatY;
        float sixthX = topStatX;
        float sixthY = fifthY - (fouthY - secondY);
        float seventhX = 2 * sixthX - fifthX;
        float seventhY = fifthY;
        float eightthX = 2 * sixthX - fouthX;
        float eightthY = fouthY;
        float ninethX = 2 * topStatX - thirdX;
        float ninethY = thirdY;
        float finalX = 2 * topStatX - secondX;
        float finalY = secondY;
        paint.setColor(Color.BLUE);
        canvas.drawLine(topStatX, topStatY, secondX, secondY, paint);
        canvas.drawLine(secondX, secondY, thirdX, thirdY, paint);
        canvas.drawLine(thirdX, thirdY, fouthX, fouthY, paint);
        canvas.drawLine(fouthX, fouthY, fifthX, fifthY, paint);
        canvas.drawLine(fifthX, fifthY, sixthX, sixthY, paint);
        canvas.drawLine(sixthX, sixthY, seventhX, seventhY, paint);
        canvas.drawLine(seventhX, seventhY, eightthX, eightthY, paint);
        canvas.drawLine(eightthX, eightthY, ninethX, ninethY, paint);
        canvas.drawLine(ninethX, ninethY, finalX, finalY, paint);
        canvas.drawLine(finalX, finalY, topStatX, topStatY, paint);
    }
}
