package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 15/11/2016.
 */

public class FragmentDetailStudent extends Fragment implements View.OnClickListener {
    public static final String STUDENT_EDIT = "student";
    public static final String NAME_FRAGMENT = "FragmentDetailStudent";
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvSchool;
    private TextView mTvAddress;
    private ImageView mImvBack;
    private ImageView mImvNext;
    private Student mStudent;
    private Integer mPosition;
    private FragmentEditStudent mFragmentEditStudent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_student, null);
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvAge = (TextView) view.findViewById(R.id.tvAge);
        mTvAddress = (TextView) view.findViewById(R.id.tvAddress);
        mTvSchool = (TextView) view.findViewById(R.id.tvSchool);
        mImvBack = (ImageView) view.findViewById(R.id.imvBack);
        mImvNext = (ImageView) view.findViewById(R.id.imvNext);
        mFragmentEditStudent = new FragmentEditStudent();
        receiveData();
        mImvBack.setOnClickListener(this);
        mImvNext.setOnClickListener(this);

        setData(mStudent);
        return view;
    }

    public void setData(Student student) {
        mTvName.setText(student.getmName());
        mTvAge.setText(student.getmAge());
        mTvSchool.setText(student.getmSchool());
        mTvAddress.setText(student.getmAddress());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imvBack:
                getActivity().onBackPressed();
                break;
            case R.id.imvNext:
                Bundle bundle = new Bundle();
                bundle.putInt(FragmentListView.POSITION, mPosition);
                bundle.putParcelable(STUDENT_EDIT, mStudent);
                mFragmentEditStudent.setArguments(bundle);
                switchFragment(mFragmentEditStudent, true, R.id.frContain, NAME_FRAGMENT);
        }
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(id, fragment, nameFragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }

    public void receiveData() {
        Bundle bundle = getArguments();
        mStudent = bundle.getParcelable(FragmentListView.STUDENT_DETAIL);
        mPosition = bundle.getInt(FragmentListView.POSITION);
    }
}
