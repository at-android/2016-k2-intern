package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddStudentFragment.CallbackUpdateView {
    public ArrayList<StudentObject> arr = new ArrayList<StudentObject>();
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ListStudentFragment fragment = new ListStudentFragment();
        fragmentTransaction.add(R.id.frLayoutMain, fragment, "ListStudent").commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClickUpdate(StudentObject std) {
        arr.add(std);
        ListStudentFragment f = new ListStudentFragment();
        f.addData(std);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frLayoutMain, f, "ListStudent").commit();
    }

}
