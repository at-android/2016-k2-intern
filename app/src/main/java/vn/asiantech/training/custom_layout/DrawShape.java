package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by MaiManhDuy on 12/11/2016.
 */

public class DrawShape extends View {
    private Paint red;
    private Paint green;
    private Paint yeelow;
    private Paint violet;
    private Paint blue;
    private Paint black;
    private Paint lightBlue;
    private Paint brown;
    private Paint darkGreen;

    public DrawShape(Context context) {
        super(context);
        red = new Paint();
        red.setColor(Color.rgb(223, 0, 41));
        green = new Paint();
        green.setColor(Color.rgb(91, 189, 43));
        yeelow = new Paint();
        yeelow.setColor(Color.rgb(249, 240, 0));
        violet = new Paint();
        violet.setColor(Color.rgb(162, 0, 124));
        blue = new Paint();
        blue.setColor(Color.rgb(115, 136, 193));
        black = new Paint();
        black.setColor(Color.rgb(0, 0, 0));
        lightBlue = new Paint();
        lightBlue.setColor(Color.rgb(0, 178, 191));
        brown = new Paint();
        brown.setColor(Color.rgb(148, 83, 5));
        darkGreen = new Paint();
        darkGreen.setColor(Color.rgb(0, 102, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
        drawRectangle(canvas);
        drawOval(canvas);
        drawStar(canvas);
        drawQuadrangle(canvas);
        drawParallelogram(canvas);
        drawTriangle(canvas);
        drawIsoscelesTriangle(canvas);
        drawLaughtFace(canvas);
        drawHeart(canvas);
        super.onDraw(canvas);
    }

    public void drawCircle(Canvas canvas) {
        canvas.drawCircle(800, 200, 150, green);
    }

    public void drawRectangle(Canvas canvas) {
        canvas.drawRect(100, 50, 400, 350, red);
    }

    public void drawOval(Canvas canvas) {
        RectF rect = new RectF(100, 400, 100 + 300, 400 + 200);
        canvas.drawOval(rect, yeelow);
    }

    public void drawStar(Canvas canvas) {
        Path path = new Path();
        path.moveTo(800, 400);
// top to bottom right point
        path.lineTo((int) (800 + 200), (int) (400 + 200));
// bottom right to middle left point
        path.lineTo((int) (800 - 100 - 120), (int) (400 + 50));
// middle left to middle right point
        path.lineTo((int) (800 + 100 + 120), (int) (400 + 50));
//        // middle right to bottom left point
        path.lineTo((int) (800 - 200), (int) (400 + 200));
//        // bottom left to top point
        path.lineTo(800, 400);
        path.close();
        canvas.drawPath(path, violet);
    }

    public void drawQuadrangle(Canvas canvas) {
        Path path = new Path();
        int x = 100;
        int y = 650;
        path.moveTo(x, y);
        path.lineTo((int) (x + 300), (int) (y));
        path.lineTo((int) (x + 300), (int) (y + 100));
        path.lineTo((int) (x), (int) (y + 200));
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, blue);
    }

    public void drawParallelogram(Canvas canvas) {
        Path path = new Path();
        int x = 700;
        int y = 650;
        path.moveTo(x, y);
        path.lineTo((int) (x + 300), (int) (y));
        path.lineTo((int) (x + 300 - 100), (int) (y + 200));
        path.lineTo((int) (x + 300 - 100 - 300), (int) (y + 200));
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, black);
    }

    public void drawTriangle(Canvas canvas) {
        Path path = new Path();
        int x = 100;
        int y = 900;
        path.moveTo(x, y);
        path.lineTo(x + 200, y + 200);
        path.lineTo(x, y + 200);
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, lightBlue);
    }

    public void drawIsoscelesTriangle(Canvas canvas) {
        Path path = new Path();
        int x = 800;
        int y = 900;
        path.moveTo(x, y);
        path.lineTo(x + 200, y + 200);
        path.lineTo(x + 200 - 400, y + 200);
        path.moveTo(x, y);
        path.close();
        canvas.drawPath(path, brown);
    }

    public void drawHeart(Canvas canvas) {
        Path path = new Path();

        float length = 120;
        float x = 240;
        float y = 1350;

        canvas.rotate(45, x, y);

        path.moveTo(x, y);
        path.lineTo(x - length, y);
        path.arcTo(new RectF(x - length - (length / 2), y - length, x - (length / 2), y), 90, 180);
        path.arcTo(new RectF(x - length, y - length - (length / 2), x, y - (length / 2)), 180, 180);
        path.lineTo(x, y);
        path.close();

        canvas.drawPath(path, black);
    }

    public void drawLaughtFace(Canvas canvas) {
        darkGreen.setStrokeWidth(5);
        darkGreen.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF();
        oval.set(700, 1150, 900, 1350);
        canvas.drawArc(oval, 50, 270, true, darkGreen);
        canvas.drawCircle(800, 1200, 10, green);
    }
}
