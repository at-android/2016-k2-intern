package vn.asiantech.training.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.fragments.AddStudentFragment;
import vn.asiantech.training.fragments.EditStudentFragment;
import vn.asiantech.training.fragments.ListStudentFragment;
import vn.asiantech.training.fragments.ListStudentFragment_;
import vn.asiantech.training.models.Student;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements AddStudentFragment.OnFragmentAddListener,EditStudentFragment.OnFragmentEditListener{
    private ListStudentFragment mListStudentFragment ;
    private EditStudentFragment mEditStudentFragment;
    public List<Student> mStudents;
    private FragmentManager mFragmentManager;

    @AfterViews
     void callFragment(){
        AddStudentFragment.onFragmentAddListener(this);
        mEditStudentFragment.onEditListener(this);
        mListStudentFragment = ListStudentFragment_.builder().build();
        mStudents = new ArrayList<>();
        mStudents = initData();
        mListStudentFragment.initData(mStudents);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.frContain, mListStudentFragment).commit();
    }

    public List<Student> initData(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Phuong", "19", "DHBK", "Quảng Nam"));
        students.add(new Student("Phuong", "19", "DHBK", "Quảng Nam"));
        return students;
    }

    @Override
    public void onFragmentAdd(Student student) {
        mStudents.add(student);
        ListStudentFragment listStudentFragment = ListStudentFragment_.builder().build();
        listStudentFragment.initData(mStudents);
        mFragmentManager.beginTransaction().replace(R.id.frContain, listStudentFragment).commit();
    }

    @Override
    public void onFragmentEdit(Student student,int position) {
        ListStudentFragment listStudentFragment = ListStudentFragment_.builder().build();
        mStudents.set(position, student);
        listStudentFragment.initData(mStudents);
        mFragmentManager.beginTransaction().replace(R.id.frContain, listStudentFragment).commit();
    }
}
