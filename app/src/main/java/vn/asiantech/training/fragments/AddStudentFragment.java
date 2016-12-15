package vn.asiantech.training.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 15/12/2016.
 */
@EFragment(R.layout.fragment_add_student)
public class AddStudentFragment extends Fragment {
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;
    @ViewById(R.id.edtSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtAddress)
    EditText mEdtAddress;
    @ViewById(R.id.btnAdd)
    Button mBtnAdd;
    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    private callAddStudentListener mListener;
    private ListStudentFragment mListStudentFragment;

    @AfterViews
    void initListFragment() {
        mListStudentFragment = ListStudentFragment_.builder().build();
    }

    @Click(R.id.btnAdd)
    void addStudent() {
        Student student = new Student(mEdtName.getText().toString(), mEdtAge.getText().toString(),
                mEdtAddress.getText().toString(), mEdtSchool.getText().toString());
        //  mListener.addStudentListener(student);
        Log.d("tag", "student" + student);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, ListStudentFragment_.builder().student(student).build());
        ft.commit();
        formatData();
        getActivity().onBackPressed();
    }

    @Click(R.id.imgBack)
    void callBackList() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, ListStudentFragment_.builder().build());
        ft.commit();
    }

    public void callAddListener(callAddStudentListener listener) {
        mListener = listener;
    }

    public void formatData() {
        mEdtName.setText("");
        mEdtAddress.setText("");
        mEdtAge.setText("");
        mEdtSchool.setText("");
    }

    public interface callAddStudentListener {
        void addStudentListener(Student student);
    }

}
