package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddSVFragment.OnHeadlineSelectedListener {
    private ArrayList<SinhVien> mArr = new ArrayList<SinhVien>();

    public ArrayList<SinhVien> getmArr() {
        return mArr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentFragment fr = new StudentFragment();
        replaceAFragment(fr, true, R.id.activity_main);
    }

    public void replaceAFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.commit();
        }
    }

    @Override
    public void onArticleSelected(SinhVien sv) {
        StudentFragment studentFragment = new StudentFragment();
        studentFragment.UpdateData(sv);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_main, studentFragment).commit();
    }
}
