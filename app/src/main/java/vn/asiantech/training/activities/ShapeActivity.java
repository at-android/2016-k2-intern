package vn.asiantech.training.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.R;
import vn.asiantech.training.custom_layout.HeaderLayout;

public class ShapeActivity extends AppCompatActivity implements HeaderLayout.onClickFormHeaderLayout {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        HeaderLayout headerLayout = (HeaderLayout) findViewById(R.id.headLayoutTitleDrawShape);
        headerLayout.setVisibleLogout(false);
        headerLayout.setVisibleBack(true);
        headerLayout.setCallBack(this);
    }

    @Override
    public void onLogOut() {

    }

    @Override
    public void onBack() {
        finish();
    }
}
