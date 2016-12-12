package vn.asiantech.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class BitmapActivity extends AppCompatActivity {
    private ImageView mImgViewCycle;
    private ImageView mImgViewTriangle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mImgViewCycle = (ImageView)findViewById(R.id.imgViewCycle);
        mImgViewTriangle = (ImageView)findViewById(R.id.imgViewTriangle);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.android);

        mImgViewCycle.setImageBitmap(icon);
        mImgViewTriangle.setImageBitmap(icon);
    }
}
