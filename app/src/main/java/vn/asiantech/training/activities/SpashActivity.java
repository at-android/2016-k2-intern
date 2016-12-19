package vn.asiantech.training.activities;

import android.content.Intent;
import android.os.Handler;

import org.androidannotations.annotations.EActivity;

import vn.asiantech.training.R;

/**
 * Created by phuong on 16/12/2016.
 */
@EActivity(R.layout.activity_spash)
public class SpashActivity extends BaseActivity {
    private Handler mHandler;

    @Override
    void inits() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpashActivity.this, MenuActivity_.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
