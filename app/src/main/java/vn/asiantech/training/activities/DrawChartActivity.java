package vn.asiantech.training.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.R;
import vn.asiantech.training.custom_layout.HeaderLayout;

public class DrawChartActivity extends AppCompatActivity implements HeaderLayout.onClickFormHeaderLayout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_chart);
        HeaderLayout headerLayout = (HeaderLayout) findViewById(R.id.headLayoutTitleChart);
        headerLayout.setCallBack(this);
        headerLayout.setVisibleBack(true);
        headerLayout.setVisibleLogout(false);
    }

    @Override
    public void onLogOut() {

    }

    @Override
    public void onBack() {
        finish();
    }
}
