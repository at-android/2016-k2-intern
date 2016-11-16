package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddStudentFragment.CallbackUpdateView, InfomationStudent.onWaitInformation, ListStudentFragment.getPositionformListView, EditStudentFragment.OnWaitPositionForEdit {
    public ArrayList<StudentObject> sMainStudentArray = new ArrayList<StudentObject>();
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        insertData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        ListStudentFragment fragment = new ListStudentFragment();
        mFragmentTransaction.replace(R.id.activity_main, fragment, "ListStudent").commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClickUpdate(StudentObject std) {
        sMainStudentArray.add(std);
        ListStudentFragment f = new ListStudentFragment();
        f.addData(std);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.activity_main, f, "ListStudent").commit();
    }

    public void insertData() {
        StudentObject stOj = new StudentObject();
        stOj.setName("Manh Duy");
        stOj.setSchool("FPT Poly");
        stOj.setAddress("Da Nang");
        stOj.setOld("21");
        sMainStudentArray.add(stOj);
        StudentObject stOj1 = new StudentObject();
        stOj1.setName("Hoang Long");
        stOj1.setSchool("FPT Poly");
        stOj1.setAddress("Sai Gon");
        stOj1.setOld("21");
        sMainStudentArray.add(stOj1);
        StudentObject stOj2 = new StudentObject();
        stOj2.setName("Nhat Hai");
        stOj2.setSchool("FPT Poly");
        stOj2.setAddress("Ha Noi");
        stOj2.setOld("21");
        sMainStudentArray.add(stOj2);
        StudentObject stOj3 = new StudentObject();
        stOj3.setName("Phung Thien");
        stOj3.setSchool("FPT Poly");
        stOj3.setAddress("Hue");
        stOj3.setOld("21");
        sMainStudentArray.add(stOj3);
    }

    @Override
    public void getInformation(StudentObject object) {

    }

    @Override
    public void editInformation(int keyPosition) {
        EditStudentFragment f = new EditStudentFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.activity_main, f, "EditStudent").commit();
        f.setKeyPosition(keyPosition);
    }

    @Override
    public void setPositionFormListToInfo(int position) {
        InfomationStudent f = new InfomationStudent();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.activity_main, f, "Information").commit();
        f.setKeyForInfoStudent(position);
    }

    @Override
    public void sendKeyPositionFormEditToInfo(int keyposition) {
        InfomationStudent f = new InfomationStudent();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentTransaction = fragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.activity_main, f, "Information").commit();
        f.setKeyForInfoStudent(keyposition);
    }
}
