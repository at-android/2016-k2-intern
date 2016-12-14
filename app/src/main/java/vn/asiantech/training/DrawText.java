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
 * Created by Administrator on 13/12/2016.
 */

public class DrawText extends View {
    private String mArr[] = {"Android by Google", "Ios by Apple", "Window by Microsoft"};
    private int mColor[] = {Color.RED, Color.BLUE, Color.GREEN};
    private Path mArc;
    private Paint mPaintText;

    public DrawText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {

        mArc = new Path();
        RectF oval = new RectF(50,100,650,700);;
        mArc.addArc(oval, 180, 60);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setColor(Color.RED);
        mPaintText.setTextSize(20f);
        canvas.drawTextOnPath(mArr[0], mArc, 0, 0, mPaintText);

        mArc = new Path();
        mArc.addArc(oval,220, 60);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setColor(Color.GREEN);
        mPaintText.setTextSize(20f);
        canvas.drawTextOnPath(mArr[1], mArc, 0, 0, mPaintText);

        mArc = new Path();
        mArc.addArc(oval, 250, 60);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setColor(Color.BLUE);
        mPaintText.setTextSize(20f);
        canvas.drawTextOnPath(mArr[2], mArc, 0, 0, mPaintText);

        invalidate();
    }
}
