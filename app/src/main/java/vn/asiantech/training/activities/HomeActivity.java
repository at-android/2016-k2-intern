package vn.asiantech.training.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vn.asiantech.training.R;
import vn.asiantech.training.custom_layout.HeaderLayout;

public class HomeActivity extends AppCompatActivity implements HeaderLayout.onClickFormHeaderLayout {
    private Button mBtnDrawShape;
    private Button mBtnDrawText;
    private Button mBtnCustomImage;
    private Button mBtnDrawChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HeaderLayout headerLayout = (HeaderLayout) findViewById(R.id.headLayoutTitleHome);
        headerLayout.setCallBack(this);
        headerLayout.setVisibleBack(false);
        headerLayout.setVisibleLogout(true);
        mBtnCustomImage = (Button) findViewById(R.id.btnDrawBitmap);
        mBtnDrawChart = (Button) findViewById(R.id.btnDrawChart);
        mBtnDrawText = (Button) findViewById(R.id.btnDrawText);
        mBtnDrawShape = (Button) findViewById(R.id.btnDrawShape);
        mBtnCustomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CustomImagesActivity.class));
            }
        });
        mBtnDrawChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DrawChartActivity.class));
            }
        });
        mBtnDrawShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ShapeActivity.class));
            }
        });
        mBtnDrawText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DrawTextAcitivy.class));
            }
        });
    }

    @Override
    public void onLogOut() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        SharedPreferences pre = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        SharedPreferences.Editor edit = pre.edit();
        edit.putString("status", "false");
        edit.commit();
        startActivity(intent);
    }

    @Override
    public void onBack() {

    }
}
