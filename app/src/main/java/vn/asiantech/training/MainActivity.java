package vn.asiantech.training;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvNumber1;
    private TextView mTvNumber2;
    private TextView mTvNumber3;
    private TextView mTvNumber4;
    private TextView mTvNumber5;
    private TextView mTvNumber6;
    private TextView mTvNumber7;
    private TextView mTvNumber8;
    private TextView mTvNumber9;
    private TextView mTvNumber0;
    private ImageView mImvCall;
    private ImageView mImvDelete;
    private TextView mTvExpress;
    private String mExpress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvNumber0 = (TextView) findViewById(R.id.tvNumber0);
        mTvNumber1 = (TextView) findViewById(R.id.tvNumber1);
        mTvNumber2 = (TextView) findViewById(R.id.tvNumber2);
        mTvNumber3 = (TextView) findViewById(R.id.tvNumber3);
        mTvNumber4 = (TextView) findViewById(R.id.tvNumber4);
        mTvNumber5 = (TextView) findViewById(R.id.tvNumber5);
        mTvNumber6 = (TextView) findViewById(R.id.tvNumber6);
        mTvNumber7 = (TextView) findViewById(R.id.tvNumber7);
        mTvNumber8 = (TextView) findViewById(R.id.tvNumber8);
        mTvNumber9 = (TextView) findViewById(R.id.tvNumber9);
        mImvCall = (ImageView) findViewById(R.id.imvCall);
        mImvDelete = (ImageView) findViewById(R.id.imvDelete);
        mTvExpress = (TextView) findViewById(R.id.tvExpress);

        mTvNumber0.setOnClickListener(this);
        mTvNumber1.setOnClickListener(this);
        mTvNumber2.setOnClickListener(this);
        mTvNumber3.setOnClickListener(this);
        mTvNumber4.setOnClickListener(this);
        mTvNumber5.setOnClickListener(this);
        mTvNumber6.setOnClickListener(this);
        mTvNumber7.setOnClickListener(this);
        mTvNumber8.setOnClickListener(this);
        mTvNumber9.setOnClickListener(this);
        mImvDelete.setOnClickListener(this);
        mImvCall.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvNumber0:
                makeExpression("0");
                break;
            case R.id.tvNumber1:
                makeExpression("1");
                break;
            case R.id.tvNumber2:
                makeExpression("2");
                break;
            case R.id.tvNumber3:
                makeExpression("3");
                break;
            case R.id.tvNumber4:
                makeExpression("4");
                break;
            case R.id.tvNumber5:
                makeExpression("5");
                break;
            case R.id.tvNumber6:
                makeExpression("6");
                break;
            case R.id.tvNumber7:
                makeExpression("7");
                break;
            case R.id.tvNumber8:
                makeExpression("8");
                break;
            case R.id.tvNumber9:
                makeExpression("9");
                break;
            case R.id.imvCall:
                makeCall(mTvExpress.getText().toString());
                break;
            case R.id.imvDelete:
                deleteLastCharacter();
                break;
        }
    }

    public void makeExpression(String number) {
        mExpress += number;
        mTvExpress.setText(mExpress);
    }

    public void deleteLastCharacter() {
        if (mTvExpress.length() >= 1) {
            mExpress = mExpress.substring(0, mTvExpress.length() - 1);
            mTvExpress.setText(mExpress);
        }
    }

    public void makeCall(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(parse("tel:"+number));
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

}
