package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by HoangDuy on 12/12/2016.
 */
public class CustomImageView extends View {
    Bitmap bitmap;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawBitmap(getCircle(), 300, 500, paint);
        canvas.drawBitmap(getHeart(0, 20, 100, 80), 500, 500, paint);
        canvas.drawBitmap(getStat(20, 120, 80), 300, 800, paint);
        canvas.drawBitmap(getTriangle(0, 0, 100, 100), 500, 800, paint);
    }

    public Bitmap getCircle() {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth() / 2,
                bitmap.getHeight() / 2);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(100,
                100, 100, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getStat(float top, float left, int height) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth() / 2,
                bitmap.getHeight() / 2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float topStatX = left;
        float topStatY = top;
        float secondX = topStatX - 20;
        float secondY = top + height / 2;
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
        Path path = new Path();
        path.moveTo(topStatX, topStatY);
        path.lineTo(secondX, secondY);
        path.lineTo(thirdX, thirdY);
        path.lineTo(fouthX, fouthY);
        path.lineTo(fifthX, fifthY);
        path.lineTo(sixthX, sixthY);
        path.lineTo(seventhX, seventhY);
        path.lineTo(eightthX, eightthY);
        path.lineTo(ninethX, ninethY);
        path.lineTo(finalX, finalY);
        path.lineTo(topStatX, topStatY);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getTriangle(float top, float left, int width, int height) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth() / 2,
                bitmap.getHeight() / 2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        Path path = new Path();
        float firstX = left;
        float firstY = top;
        float secondX = left + width;
        float secondY = firstY;
        float thirdX = secondX / 2;
        float thirdY = secondY + height;
        path.moveTo(firstX, firstY);
        path.lineTo(secondX, secondY);
        path.lineTo(thirdX, thirdY);
        path.lineTo(firstX, firstY);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getHeart(float top, float left, int width, int height) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth() / 2,
                bitmap.getHeight() / 2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float leftRect = left;
        float topRect = top;
        float rightRect = leftRect + width - 30;
        float bottomRect = topRect + height;
        float leftRect1 = rightRect;
        float topRect1 = topRect;
        float rightRect1 = leftRect1 + width - 30;
        float bottomRect1 = bottomRect;
        float middleX = leftRect1;
        float middleY = topRect + height + 100;
        Path path = new Path();
        path.moveTo(leftRect, topRect + height / 2);
        path.lineTo(middleX, middleY);
        path.lineTo(rightRect1, topRect + height / 2);
        path.lineTo(leftRect, topRect + height / 2);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawPath(path, paint);
        canvas.drawArc(new RectF(leftRect, topRect, rightRect, bottomRect), 180, 180, false, paint);
        canvas.drawArc(new RectF(leftRect1, topRect1, rightRect1, bottomRect1), 180, 180, false, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
