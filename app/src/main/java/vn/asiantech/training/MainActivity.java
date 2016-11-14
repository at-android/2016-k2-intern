package vn.asiantech.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import vn.asiantech.training.fragment.FragmentListView;

public class MainActivity extends AppCompatActivity {
    private Fragment mFramentListView;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = (FrameLayout) findViewById(R.id.frContain);
        mFramentListView = new FragmentListView();

        switchFragment(mFramentListView, true, R.id.frContain);
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        ft.commit();
    }
}
