package vn.asiantech.training.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.R;

public class ShapeActivity extends AppCompatActivity implements Toolbar.onToolbarInteraction {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setCallback(this);
    }


    @Override
    public void logOut() {
        finish();
    }
}
