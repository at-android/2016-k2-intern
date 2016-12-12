package vn.asiantech.training.custom_layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.asiantech.training.R;

/**
 * Created by MaiManhDuy on 12/9/2016.
 */
public class HeaderLayout extends RelativeLayout {
    onClickFormHeaderLayout mListener;
    private ImageButton mImage;
    private LinearLayout lnLayout;

    public HeaderLayout(Context context) {
        this(context, null);
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.Header, 0, 0);
        String titleText = a.getString(R.styleable.Header_titleText);
        a.recycle();
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header_layout, this, true);
        TextView title = (TextView) getChildAt(0);
        title.setText(titleText);
        lnLayout = (LinearLayout) getChildAt(1);
        mImage = (ImageButton) getChildAt(2);
        mImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.clickButton();
            }
        });
    }

    public void setCallBack(onClickFormHeaderLayout mListener) {
        this.mListener = mListener;
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public interface onClickFormHeaderLayout {
        void clickButton();
    }
}
