package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.Adapter.TabsPagerAdapter;

import static vn.asiantech.training.R.id.pager;

public class HomeActivity extends AppCompatActivity implements AddContactFragment.SendData {
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(pager);
        mPager.setAdapter(adapter);
    }

    @Override
    public void onArticleSelected() {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
    }
}
