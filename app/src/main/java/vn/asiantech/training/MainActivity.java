package vn.asiantech.training;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.adapter.ListStudentAdapter;
import vn.asiantech.training.fragments.AddStudentFragment_;
import vn.asiantech.training.fragments.EditStudentFragment_;
import vn.asiantech.training.fragments.InfoStudentFragment_;
import vn.asiantech.training.fragments.ListStudentFragment_;
import vn.asiantech.training.objects.StudentObj;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements ListStudentFragment_.onListStudentFragmentInterface, AddStudentFragment_.onAddStudentFragmentCallBack, ListStudentAdapter.sendPostionFormListToInfo, InfoStudentFragment_.OnFragmentInteractionListener, EditStudentFragment_.OnFragmentInteractionListener {
    public List<StudentObj> studentArrays = new ArrayList<>();

    @AfterViews
    void onShowListFragmentStudent() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, ListStudentFragment_.builder().build(), "ListStudent");
        fragmentTransaction.commit();
    }

    @AfterViews
    void onInsertData() {
        studentArrays.add(new StudentObj("FPT", "Hung", "Da Nang", 21));
        studentArrays.add(new StudentObj("Bach Khoa", "Nam", "HCM", 20));
        studentArrays.add(new StudentObj("Cong Nghe", "Thanh", "Hue", 20));
        studentArrays.add(new StudentObj("Ngoai Ngu", "Tuan", "Ha Noi", 19));
    }
    @Override
    public void onItentAddStudent() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, AddStudentFragment_.builder().build(), "AddStudent");
        fragmentTransaction.commit();
    }

    @Override
    public void addStudentAndBackToList(StudentObj studentObj) {
        studentArrays.add(studentObj);
        backListStudent();
    }

    @Override
    public void backListFormAdd() {
        backListStudent();
    }

    public void backListStudent() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, ListStudentFragment_.builder().build(), "ListStudent");
        fragmentTransaction.commit();
    }

    @Override
    public void sendPostionToInfo(int pos) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InfoStudentFragment_ infor = new InfoStudentFragment_();
        infor.getInforPosition(pos);
        fragmentTransaction.replace(R.id.activity_main, infor.builder().build(), "InforStudent");
        fragmentTransaction.commit();
    }


    @Override
    public void onBackListFormInfor() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, ListStudentFragment_.builder().build(), "ListStudent");
        fragmentTransaction.commit();
    }

    @Override
    public void onEditStudent(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EditStudentFragment_ infor = new EditStudentFragment_();
        infor.getPositionFormInfo(position);
        fragmentTransaction.replace(R.id.activity_main, infor.builder().build(), "EditStudent");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackInforFormEdit(int pos) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InfoStudentFragment_ infor = new InfoStudentFragment_();
        infor.getInforPosition(pos);
        fragmentTransaction.replace(R.id.activity_main, infor.builder().build(), "InfoStudent");
        fragmentTransaction.commit();
    }
}
