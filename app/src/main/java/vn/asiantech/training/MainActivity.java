package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import vn.asiantech.training.fragment.FragmentListView;

public class MainActivity extends AppCompatActivity {
    private Fragment mFramentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFramentListView = new FragmentListView();
        switchFragment(mFramentListView, true, R.id.frContain, FragmentListView.NAME_FRAGMENT);
    }

    public String getData() {
        return "thí is data";
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(id, fragment, nameFragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }

}
