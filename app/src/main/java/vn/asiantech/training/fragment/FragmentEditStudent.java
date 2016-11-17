package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.training.App;
import vn.asiantech.training.R;
import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 15/11/2016.
 */

public class FragmentEditStudent extends Fragment {
    private Student mStudent = new Student();
    private EditText mEdName;
    private EditText mEdAge;
    private EditText mEdSchool;
    private EditText mEdAddress;
    private Button mBtnEdit;
    private Integer mPosition = 0;
    private ImageView mImvBack;
    private FragmentListView mFragmentListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_student, null);

        modifineComponent(view);
        receiveDataEdit();
        setData(mStudent);

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(mEdName.getText().toString(), mEdAge.getText().toString(), mEdAddress.getText().toString(), mEdSchool.getText().toString());
                ((App) getActivity().getApplication()).editStudent(student, mPosition);
                mFragmentListView = new FragmentListView();
                switchFragment(mFragmentListView, false, R.id.frContain, "");
            }
        });

        mImvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack(FragmentListView.NAME_FRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        return view;
    }

    public void receiveDataEdit() {
        Bundle bundle = getArguments();
        mStudent = bundle.getParcelable(FragmentDetailStudent.STUDENT_EDIT);
        mPosition = bundle.getInt(FragmentListView.POSITION);

    }

    public void setData(Student student) {
        mEdName.setText(student.getmName());
        mEdAge.setText(student.getmAge());
        mEdAddress.setText(student.getmAddress());
        mEdSchool.setText(student.getmSchool());
    }

    public void modifineComponent(View view) {
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdAge = (EditText) view.findViewById(R.id.edAge);
        mEdAddress = (EditText) view.findViewById(R.id.edAddress);
        mEdSchool = (EditText) view.findViewById(R.id.edSchool);
        mBtnEdit = (Button) view.findViewById(R.id.btnEdit);
        mImvBack = (ImageView) view.findViewById(R.id.imvBack);
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }
}
