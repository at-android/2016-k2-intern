package vn.asiantech.training.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import vn.asiantech.training.R;
import vn.asiantech.training.fragments.ListStudentFragment;
import vn.asiantech.training.fragments.ListStudentFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private ListStudentFragment mListStudentFragment ;

    @AfterViews
     void callFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, ListStudentFragment_.builder().build());
        ft.commit();
    }
}
