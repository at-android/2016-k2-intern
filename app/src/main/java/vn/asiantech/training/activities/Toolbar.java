package vn.asiantech.training.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.asiantech.training.R;

/**
 * Created by HoangDuy on 09/12/2016.
 */
public class Toolbar extends LinearLayout {
    public onToolbarInteraction mListener;
    private ImageView mImgBack;
    private ImageView mImgLogout;
    private TextView mTvtitle;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mListener = (HomeActivity) getParent();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Toolbar, 0, 0);
        String title = ta.getString(R.styleable.Toolbar_titleText);
        boolean isVisibilityImgBack = ta.getBoolean(R.styleable.Toolbar_imgBack, true);
        boolean isVisibilityImgLogout = ta.getBoolean(R.styleable.Toolbar_imgLogout, true);
        setBackgroundColor(getResources().getColor(R.color.colorToolbar));
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.toolbar, this, true);
        mTvtitle = (TextView) getChildAt(1);
        mImgBack = (ImageView) getChildAt(0);
        mImgLogout = (ImageView) getChildAt(2);
        mImgBack.setVisibility(isVisibilityImgBack ? VISIBLE : GONE);
        mImgLogout.setVisibility(isVisibilityImgLogout ? VISIBLE : GONE);
        mTvtitle.setText(title);
        mImgLogout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logOut();
            }
        });

        mImgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("aa", "aaa");

                mListener.logOut();
            }
        });
    }

    interface onToolbarInteraction {
        public void logOut();
    }
}
