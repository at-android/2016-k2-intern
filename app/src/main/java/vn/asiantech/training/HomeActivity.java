package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ToolbarCustomView.OnCallBack {
    private String mPrefname = "my_data";
    private Button mBtnShape;
    private Button mBtnBitmap;
    private Button mBtnText;
    private Button mBtnChart;
    private Intent mIntent;
    private ToolbarCustomView mCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getFormWidget();
        mCallBack.setCallBack(this);
        mBtnShape.setOnClickListener(this);
        mBtnBitmap.setOnClickListener(this);
        mBtnText.setOnClickListener(this);
        mBtnChart.setOnClickListener(this);
    }

    public void getFormWidget() {
        mBtnShape = (Button) findViewById(R.id.btnShape);
        mBtnBitmap = (Button) findViewById(R.id.btnBitmap);
        mBtnText = (Button) findViewById(R.id.btnText);
        mBtnChart = (Button) findViewById(R.id.btnChart);
        mCallBack = (ToolbarCustomView) findViewById(R.id.toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShape:
                mIntent = new Intent(HomeActivity.this, ShapeActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnBitmap:
                mIntent = new Intent(HomeActivity.this, BitmapActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnText:
                mIntent = new Intent(HomeActivity.this, TextActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnChart:
                mIntent = new Intent(HomeActivity.this, GraphActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public void LogOut() {
        SharedPreferences pre = getSharedPreferences
                (mPrefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String valid = "false";
        editor.putString("valid", valid);
        editor.commit();
        System.exit(0);
    }
}
