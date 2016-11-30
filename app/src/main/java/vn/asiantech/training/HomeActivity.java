package vn.asiantech.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements MyContactdFragment.OnFragmentInteractionListener, LocalContactFragment.OnFragmentInteractionListener, MyAppFragment.OnFragmentInteractionListener {
    private ImageButton mBtnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_home);
        mBtnLogout = (ImageButton) findViewById(R.id.imgBtnLogOut);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pre = getSharedPreferences("Info", MODE_PRIVATE);
                SharedPreferences.Editor edit = pre.edit();
                edit.putString("check", "false");
                edit.apply();
                startActivity(new Intent(HomeActivity.this, ManagerActivity.class));
            }
        });
    }

    @Override
    public void onContactCallBack() {

    }

    @Override
    public void onLocalCallBack() {

    }

    @Override
    public void onMyAppCallBack() {

    }
}
