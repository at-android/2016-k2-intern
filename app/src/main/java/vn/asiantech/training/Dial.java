package vn.asiantech.training;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Dial extends AppCompatActivity implements View.OnClickListener {


    private static String phoneNumber = "";
    private TextView mTv_phonenumber;
    private Button mBtn_n1;
    private Button mBtn_n2;
    private Button mBtn_n3;
    private Button mBtn_n4;
    private Button mBtn_n5;
    private Button mBtn_n6;
    private Button mBtn_n7;
    private Button mBtn_n8;
    private Button mBtn_n9;
    private Button mBtn_n0;
    private ImageButton mIbtn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        mBtn_n0 = (Button) findViewById(R.id.btnN0);
        mBtn_n1 = (Button) findViewById(R.id.btnN1);
        mBtn_n2 = (Button) findViewById(R.id.btnN2);
        mBtn_n3 = (Button) findViewById(R.id.btnN3);
        mBtn_n4 = (Button) findViewById(R.id.btnN4);
        mBtn_n5 = (Button) findViewById(R.id.btnN5);
        mBtn_n6 = (Button) findViewById(R.id.btnN6);
        mBtn_n7 = (Button) findViewById(R.id.btnN7);
        mBtn_n8 = (Button) findViewById(R.id.btnN8);
        mBtn_n9 = (Button) findViewById(R.id.btnN9);
        mIbtn_call = (ImageButton) findViewById(R.id.ibtnCall);
        mTv_phonenumber = (TextView) findViewById(R.id.tvPhonenumber);

        mBtn_n0.setOnClickListener(this);
        mBtn_n1.setOnClickListener(this);
        mBtn_n2.setOnClickListener(this);
        mBtn_n3.setOnClickListener(this);
        mBtn_n4.setOnClickListener(this);
        mBtn_n5.setOnClickListener(this);
        mBtn_n6.setOnClickListener(this);
        mBtn_n7.setOnClickListener(this);
        mBtn_n8.setOnClickListener(this);
        mBtn_n9.setOnClickListener(this);
        mIbtn_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnN0:
                phoneNumber += "0";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN1:
                phoneNumber += "1";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN2:
                phoneNumber += "2";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN3:
                phoneNumber += "3";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN4:
                phoneNumber += "4";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN5:
                phoneNumber += "5";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN6:
                phoneNumber += "6";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN7:
                phoneNumber += "7";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN8:
                phoneNumber += "8";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.btnN9:
                phoneNumber += "9";
                mTv_phonenumber.setText(phoneNumber);
                break;
            case R.id.ibtnCall:
                phoneNumber = "";
                Log.i("info", mTv_phonenumber.getText().toString());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mTv_phonenumber.getText().toString()));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }
                break;
        }
    }
}
