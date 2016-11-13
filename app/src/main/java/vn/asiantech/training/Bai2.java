package vn.asiantech.training;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Bai2 extends AppCompatActivity implements View.OnClickListener{
    TextView mTv;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;
    Button mBtnStar;
    Button mBtn0;
    Button mBtnSharp;
    ImageButton mImgBtnCall,mImgBtnPhoneCall,mImgBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        getFormWidget();
        mImgBtnCall.setOnClickListener(this);
    }
    public void getFormWidget(){
        mTv = (TextView)findViewById(R.id.bai2Tv);
        mBtn0 = (Button)findViewById(R.id.bai2BtnZero);
        mBtn1 = (Button)findViewById(R.id.bai2BtnOne);
        mBtn2 = (Button)findViewById(R.id.bai2BtnTwo);
        mBtn3 = (Button)findViewById(R.id.bai2BtnThree);
        mBtn4 = (Button)findViewById(R.id.bai2BtnFour);
        mBtn5 = (Button)findViewById(R.id.bai2BtnFive);
        mBtn6 = (Button)findViewById(R.id.bai2BtnSix);
        mBtn7 = (Button)findViewById(R.id.bai2BtnSeven);
        mBtn8 = (Button)findViewById(R.id.bai2BtnEight);
        mBtn9 = (Button)findViewById(R.id.bai2BtnNine);
        mBtnStar = (Button)findViewById(R.id.bai2BtnStar);
        mBtnSharp = (Button)findViewById(R.id.bai2BtnSharp);
        mImgBtnCall = (ImageButton)findViewById(R.id.bai2ImgBtnCall);
        mImgBtnPhoneCall = (ImageButton)findViewById(R.id.bai2ImgBtnPhoneCall);
        mImgBtnBack = (ImageButton)findViewById(R.id.bai2ImgBtnBack);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bai2ImgBtnCall:
                doMakeCall();
                break;
        }
    }
    public void doMakeCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0934888706"));
        if(callIntent.resolveActivity(getPackageManager()) != null){
            startActivity(callIntent);
        }
    }
}
