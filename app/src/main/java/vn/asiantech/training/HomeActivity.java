package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.adapter.ViewPagerAdapter;

/**
 * Created by phuong on 29/11/2016.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {
    @ViewById(R.id.pager)
    ViewPager mViewPager;

    @AfterViews
    void initViewPager(){
        mViewPager.setAdapter(new ViewPagerAdapter
                (getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences(RegisterActivity.NAME_SHAREPREPERENCE, 0);
                preferences.edit().remove(LoginActivity.NAME_LOGIN).commit();
                Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
