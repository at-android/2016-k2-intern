package vn.asiantech.training.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import vn.asiantech.training.R;

/**
 * Created by phuong on 11/12/2016.
 */

public class DrawBitmapView extends View {

    Paint mPaint = new Paint();

    public DrawBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Log.d("size", bitmap.getWidth() + " 1 " + bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //cutBitmapRectangle(canvas);

        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.cool_boy);
        Bitmap bitmap = bitmapOrg.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth() / 3;
        Bitmap roundBitmap = getRoundedCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 30, 0, null);

        Bitmap triangleBitmap = getTriangleCropBitmap(bitmap, w);
        canvas.drawBitmap(triangleBitmap, 400, 0, null);

        Bitmap starBitmap = getStarCropBitmap(bitmap, w);
        canvas.drawBitmap(starBitmap, 30, 300, null);


        Bitmap heartBitmap = getHeartCropBitmap(bitmap, w);
        canvas.drawBitmap(heartBitmap, 400, 300, null);


    }

    public Bitmap getTriangleCropBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());
        Point point1_draw = new Point(75, 0);
        Point point2_draw = new Point(0, 180);
        Point point3_draw = new Point(180, 180);
        Path path = new Path();
        path.moveTo(point1_draw.x, point1_draw.y);
        path.lineTo(point2_draw.x, point2_draw.y);
        path.lineTo(point3_draw.x, point3_draw.y);
        path.lineTo(point1_draw.x, point1_draw.y);
        path.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getStarCropBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());
        Path path = new Path();
        path.moveTo(30, 100);
        // top right
        path.lineTo(270, 100);
        // bottom left
        path.lineTo(70, 200);
        // top tip
        path.lineTo(150, 50);
        // bottom right
        path.lineTo(230, 200);
        // top left
        path.lineTo(30, 100);

        path.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getHeartCropBitmap(Bitmap bitmap,int radius){
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());
        Path path = new Path();

        float length = 100;
        float x = 150;
        float y = 200;

        canvas.rotate(45,x,y);

        path.moveTo(x,y);
        path.lineTo(x-length, y);
        path.arcTo(new RectF(x-length-(length/2),y-length,x-(length/2),y),90,180);
        path.arcTo(new RectF(x-length,y-length-(length/2),x,y-(length/2)),180,180);
        path.lineTo(x,y);

        path.close();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }
}