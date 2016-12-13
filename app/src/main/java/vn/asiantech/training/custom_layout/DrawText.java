package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MaiManhDuy on 12/13/2016.
 */

public class DrawText extends View {
    private static final String TEXT_ONE = "Android by Google";
    private static final String TEXT_TWO = "Ios by Apple";
    private static final String TEXT_THREE = "Window by Microsoft";

    public DrawText(Context context) {
        super(context);
    }

    public DrawText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
        super.onDraw(canvas);
    }

    public void drawText(Canvas canvas) {
        Path mArc = new Path();
        RectF oval = new RectF(100, 100, 600, 700);
        mArc.addArc(oval, 0, 90);
        Paint paintText1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText1.setStyle(Paint.Style.FILL_AND_STROKE);
        paintText1.setColor(Color.GREEN);
        paintText1.setTextSize(65f);
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/hugs.ttf");
        paintText1.setTypeface(tf1);
        canvas.drawTextOnPath(TEXT_ONE, mArc, 0, 20, paintText1);
        Path path = new Path();
        RectF oval1 = new RectF(100, 100, 600, 700);
        path.addArc(oval1, 90, 180);
        Paint paintText2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText2.setStyle(Paint.Style.FILL_AND_STROKE);
        paintText2.setColor(Color.YELLOW);
        paintText2.setTextSize(50f);
        canvas.drawTextOnPath(TEXT_TWO, path, 0, 20, paintText2);
        Path pathThree = new Path();
        RectF ovalThree = new RectF(100, 100, 600, 700);
        pathThree.addArc(ovalThree, 155, 270);
        Paint paintTextThree = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTextThree.setStyle(Paint.Style.FILL_AND_STROKE);
        paintTextThree.setColor(Color.BLUE);
        paintTextThree.setTextSize(50f);
        Typeface tf3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/raider.ttf");
        paintTextThree.setTypeface(tf3);
        canvas.drawTextOnPath(TEXT_THREE, pathThree, 0, 20, paintTextThree);
        invalidate();
    }

}
