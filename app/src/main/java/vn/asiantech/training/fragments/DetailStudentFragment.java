package vn.asiantech.training.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 15/12/2016.
 */
@EFragment(R.layout.fragment_detail_student)
public class DetailStudentFragment extends Fragment {
    @FragmentArg
    Student mStudent;
    @FragmentArg
    int mPosition;
    @ViewById(R.id.tvName)
    TextView mEdtName;
    @ViewById(R.id.tvAge)
    TextView mEdtAge;
    @ViewById(R.id.tvSchool)
    TextView mEdtSchool;
    @ViewById(R.id.tvAddress)
    TextView mEdtAddress;
    @ViewById(R.id.imgBack)
    ImageView mImgBack;
    @ViewById(R.id.imgNext)
    ImageView mImgNext;
    private EditStudentFragment mEditStudentFragment;

    @AfterViews
    void callDetailStudent() {
        mEdtName.setText(mStudent.getName());
        mEdtAge.setText(mStudent.getAge());
        mEdtSchool.setText(mStudent.getSchool());
        mEdtAddress.setText(mStudent.getAddress());
    }

    @Click(R.id.imgBack)
    void callBackList() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, ListStudentFragment_.builder().build());
        ft.commit();
    }

    @Click(R.id.imgNext)
    void callEditStudent() {
        mEditStudentFragment = EditStudentFragment_.builder().build();
        mEditStudentFragment.mStudent = mStudent;
        mEditStudentFragment.mPosition = mPosition;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, mEditStudentFragment);
        ft.commit();
    }
}
