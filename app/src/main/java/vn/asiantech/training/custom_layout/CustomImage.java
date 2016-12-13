package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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

    @Override
    protected void onDraw(Canvas canvas) {
        drawOne(canvas);
        drawTwo(canvas);
        drawThree(canvas);
        drawStar(canvas);
        drawHeart(canvas);
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

    void drawTwo(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(600, 150, 100, paint);
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
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Path path = new Path();
        path.moveTo(600, 350);
        path.lineTo(600 + 200, 350 + 200);
        path.lineTo(600 - 100 - 120, 350 + 70);
        path.lineTo(600 + 100 + 120, 350 + 70);
        path.lineTo(600 - 200, 350 + 200);
        path.moveTo(600, 350);
        path.close();
        canvas.drawPath(path, paint);
    }

    void drawHeart(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourname);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
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
    }
}
