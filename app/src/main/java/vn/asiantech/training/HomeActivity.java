package vn.asiantech.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.adapter.ViewPagerAdapter;

/**
 * Created by phuong on 29/11/2016.
 */

public class HomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new ViewPagerAdapter
                (getSupportFragmentManager()));
    }
}
