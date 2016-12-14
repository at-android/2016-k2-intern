package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by MaiManhDuy on 12/12/2016.
 */

public class CustomImage extends View {


    public CustomImage(Context context) {
        super(context);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static Bitmap getDrawCricle(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(100, 50, finalBitmap.getWidth(),
                finalBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(600,
                150,
                100, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getDrawStar(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(100, 50, finalBitmap.getWidth(),
                finalBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        Path path = new Path();
        path.moveTo(600, 350);
        path.lineTo(600 + 200, 350 + 200);
        path.lineTo(600 - 100 - 120, 350 + 70);
        path.lineTo(600 + 100 + 120, 350 + 70);
        path.lineTo(600 - 200, 350 + 200);
        path.moveTo(600, 350);
        path.close();
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getDrawHeart(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(10, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        Path path = new Path();
        float length = 120;
        float x = 150;
        float y = 800;
        canvas.rotate(45, x, y);
        path.moveTo(x, y);
        path.lineTo(x - length, y);
        path.arcTo(new RectF(x - length - (length / 2), y - length, x - (length / 2), y), 90, 180);
        path.arcTo(new RectF(x - length, y - length - (length / 2), x, y - (length / 2)), 180, 180);
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.rotate(-45, x, y);
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOne(canvas);
        drawCricle(canvas);
        drawThree(canvas);
        drawStar(canvas);
        drawHeart(canvas);
        rootImage(canvas);
        super.onDraw(canvas);
    }

    void drawOne(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Path path = new Path();
        int x = 50;
        int y = 50;
        path.moveTo(x, y);
        path.lineTo(x + 200, y);
        path.lineTo(x + 200, y + 200);
        path.lineTo(x, y + 200);
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, paint);
    }

    void drawCricle(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Bitmap bitmap = image.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();

        Bitmap roundBitmap = getDrawCricle(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    void drawThree(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Path path = new Path();
        int x = 50;
        int y = 350;
        path.moveTo(x, y);
        path.lineTo(x + 200, y);
        path.lineTo(x + 100, y + 200);
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, paint);
    }

    void drawStar(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Bitmap bitmap = image.copy(Bitmap.Config.ARGB_8888, true);
        int w = getWidth();
        Bitmap roundBitmap = getDrawStar(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    void drawHeart(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Bitmap bitmap = image.copy(Bitmap.Config.ARGB_8888, true);
        int w = getWidth();
        Bitmap roundBitmap = getDrawHeart(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    public void rootImage(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        canvas.drawBitmap(image, 400, 600, new Paint());
    }
}
