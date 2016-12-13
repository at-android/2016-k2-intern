package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.R;

public class HomeActivity extends AppCompatActivity implements Toolbar.onToolbarInteraction, View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private Button mBtnDrawShape;
    private Button mBtnDrawText;
    private Button mBtnDrawBitmap;
    private Button mBtnDrawChart;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setCallback(this);
        mBtnDrawShape = (Button) findViewById(R.id.btnDrawShape);
        mBtnDrawShape.setOnClickListener(this);
        mBtnDrawText = (Button) findViewById(R.id.btnDrawText);
        mBtnDrawText.setOnClickListener(this);
        mBtnDrawBitmap = (Button) findViewById(R.id.btnDrawBitmap);
        mBtnDrawBitmap.setOnClickListener(this);
        mBtnDrawChart = (Button) findViewById(R.id.btnDrawChart);
        mBtnDrawChart.setOnClickListener(this);
    }

    @Override
    public void logOut() {
        mSharedPreferences = getSharedPreferences(LoginActivity.MYPREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(LoginActivity.IS_ACTIVE, false);
        editor.commit();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDrawShape:
                Intent intentDrawShape = new Intent(HomeActivity.this, ShapeActivity.class);
                startActivity(intentDrawShape);
                break;
            case R.id.btnDrawText:
                Intent intentDrawText = new Intent(HomeActivity.this, TextDrawing.class);
                startActivity(intentDrawText);
                break;
            case R.id.btnDrawBitmap:
                Intent intentDrawBitmap = new Intent(HomeActivity.this, BitmapDrawing.class);
                startActivity(intentDrawBitmap);
                break;
            case R.id.btnDrawChart:
                Intent intentDrawChart = new Intent(HomeActivity.this, Chart.class);
                startActivity(intentDrawChart);
                break;
        }
    }
}
