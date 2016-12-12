package vn.asiantech.training;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 11/12/2016.
 */

public class ToolbarCustomView extends LinearLayout {
    private ImageView mImgLeft;
    private ImageView mImgRight;
    private TextView mTv;

    public ToolbarCustomView(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ToolbarCustomView, 0, 0);
        String titleText = typedArray.getString(R.styleable.ToolbarCustomView_titleText);
        boolean leftImg = typedArray.getBoolean(R.styleable.ToolbarCustomView_imgViewLeft,true);
        boolean rightImg = typedArray.getBoolean(R.styleable.ToolbarCustomView_imgViewRight,true);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_toolbar, this, true);
        mImgLeft = (ImageView)getChildAt(0);
        mTv = (TextView)getChildAt(1);
        mImgRight = (ImageView)getChildAt(2);

        mTv.setText(titleText);
        setImageLeftVisible(leftImg);
        setImageRightVisible(rightImg);
    }

    public ToolbarCustomView(Context context) {
        this(context, null);
    }

    public void setImageLeftVisible(boolean visible) {
        mImgLeft.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setImageRightVisible(boolean visible) {
        mImgRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
