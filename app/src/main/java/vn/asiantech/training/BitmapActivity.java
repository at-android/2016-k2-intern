package vn.asiantech.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class BitmapActivity extends AppCompatActivity implements ToolbarCustomView.OnCallBack {
    private ImageView mImgViewCycle;
    private ImageView mImgViewTriangle;
    private ImageView mImgViewHeart;
    private ImageView mImgViewStar;
    private ToolbarCustomView mCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mCallBack = (ToolbarCustomView) findViewById(R.id.toolbar);
        mCallBack.setCallBack(this);
        mImgViewCycle = (ImageView) findViewById(R.id.imgViewCycle);
        mImgViewTriangle = (ImageView) findViewById(R.id.imgViewTriangle);
        mImgViewHeart = (ImageView) findViewById(R.id.imgViewHeart);
        mImgViewStar = (ImageView) findViewById(R.id.imgViewStar);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.background1);

        mImgViewCycle.setImageBitmap(icon);
        mImgViewTriangle.setImageBitmap(icon);
        mImgViewHeart.setImageBitmap(icon);
        mImgViewStar.setImageBitmap(icon);
    }

    @Override
    public void LogOut() {
        finish();
    }
}
