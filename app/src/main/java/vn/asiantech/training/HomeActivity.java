package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements MyContactdFragment.OnFragmentInteractionListener, LocalContactFragment.OnFragmentInteractionListener, MyAppFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_home);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
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
