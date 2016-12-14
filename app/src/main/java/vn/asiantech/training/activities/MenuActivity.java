package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.R;
import vn.asiantech.training.views.HeaderOptionsView;

/**
 * Created by phuong on 11/12/2016.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, HeaderOptionsView.headerListener {
    private Button mBtnShape;
    private Button mBtnBitmap;
    private Button mBtnText;
    private Button mBtnChart;
    private HeaderOptionsView mHeaderOptionsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mBtnShape = (Button) findViewById(R.id.btnShape);
        mBtnBitmap = (Button) findViewById(R.id.btnBitmap);
        mBtnText = (Button) findViewById(R.id.btnText);
        mBtnChart = (Button) findViewById(R.id.btnChart);

        mBtnShape.setOnClickListener(this);
        mBtnChart.setOnClickListener(this);
        mBtnBitmap.setOnClickListener(this);
        mBtnText.setOnClickListener(this);

        mHeaderOptionsView = (HeaderOptionsView) findViewById(R.id.header_login_menu);

        mHeaderOptionsView.setCallListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShape:
                Intent intent = new Intent(MenuActivity.this, DrawShapeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnChart:
                intent = new Intent(this, DrawChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBitmap:
                intent = new Intent(this, DrawBitmapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnText:
                intent = new Intent(this, DrawTextActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.NAME_SHAREPREPERENCE, MODE_PRIVATE);
        sharedPreferences.edit().remove(MainActivity.ISLOGIN).commit();
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
