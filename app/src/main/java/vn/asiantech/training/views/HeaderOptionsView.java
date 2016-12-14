package vn.asiantech.training.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.asiantech.training.R;


/**
 * Created by phuong on 09/12/2016.
 */

public class HeaderOptionsView extends RelativeLayout {
    public headerListener mListener;

    public HeaderOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderOptionsView, 0, 0);
        String mTitle = typedArray.getString(R.styleable.HeaderOptionsView_title);
        boolean isShowBtnBack = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowBtnBack, false);
        boolean isShowTvHome = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowTvHome, false);
        boolean isShowTvTitle = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowTvTitle, false);
        boolean isShowBtnNext = typedArray.getBoolean(R.styleable.HeaderOptionsView_isShowBtnNext, false);
        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_header, this, true);

        final ImageView mImvBack = (ImageView) getChildAt(0);
        ImageView mImvNext = (ImageView) getChildAt(3);
        TextView mTvTitle = (TextView) getChildAt(2);
        TextView mTvHome = (TextView) getChildAt(1);
        View mView = (View) getChildAt(4);

        mTvTitle.setText(mTitle);

        if (!isShowBtnBack) {
            mImvBack.setVisibility(GONE);
        }

        if (!isShowTvTitle) {
            mTvTitle.setVisibility(GONE);
        }

        if (!isShowTvHome) {
            mTvHome.setVisibility(GONE);
        }

        if (!isShowBtnNext) {
            mImvNext.setVisibility(GONE);
        }

        mImvNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.logOut();
            }
        });

    }

    public HeaderOptionsView(Context context) {
        super(context, null);
    }

    public void setCallListener(headerListener listener) {
        this.mListener = listener;
    }

    public interface headerListener {
        void logOut();
    }
}
