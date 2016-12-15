package vn.asiantech.training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapters.RecyclerListStudentAdapter;
import vn.asiantech.training.listerners.callOnClickListener;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 14/12/2016.
 */
@EFragment(R.layout.fragment_list_student)
public class ListStudentFragment extends Fragment implements AddStudentFragment.callAddStudentListener, callOnClickListener, EditStudentFragment.callEditStudentListener {

    @ViewById(R.id.rvStudent)
    RecyclerView mRcStudent;
    @ViewById(R.id.imgAdd)
    ImageView mImgAdd;
    @FragmentArg
    Student student;

    private RecyclerListStudentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Student> mStudents;

    private AddStudentFragment mAddStudentFragment;
    private EditStudentFragment mEditStudentFragment;
    private DetailStudentFragment mDetailStudentFragment;

    @AfterViews
    void callRecyclerView() {
        mStudents = new ArrayList<>();
        mStudents = initData();

        mAdapter = new RecyclerListStudentAdapter(initData(), getContext(), this);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRcStudent.setLayoutManager(mLayoutManager);
        mRcStudent.setAdapter(mAdapter);

        mAddStudentFragment = AddStudentFragment_.builder().build();
        mAddStudentFragment.callAddListener(this);

        mEditStudentFragment = EditStudentFragment_.builder().build();
        mEditStudentFragment.callEditListener(this);
    }

    public List<Student> initData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Phuong", "19", "DHBK", "Quảng Nam"));
        students.add(new Student("Phuong", "19", "DHBK", "Quảng Nam"));
        return students;
    }

    @Click(R.id.imgAdd)
    void addStudent() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, mAddStudentFragment);
        ft.addToBackStack(ListStudentFragment.class.getName());
        ft.commit();
    }

    @Override
    public void addStudentListener(Student student) {
        mStudents.add(student);
        Log.d("TAG11", student.getName() + " ");
        Log.d("TAG11", "size" + mStudents.size());
        mAdapter.notifyDataSetChanged();
        Log.d("TAG111", "HERE ADD");
    }

    @Override
    public void onItemClickListener(Student student, int position) {
        mDetailStudentFragment = DetailStudentFragment_.builder().build();
        mDetailStudentFragment.mStudent = student;
        mDetailStudentFragment.mPosition = position;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, mDetailStudentFragment);
        ft.commit();

    }

    @Override
    public void editStudent(Student student, int position) {
        mStudents.set(position, student);
        Log.d("TAG11", student.getName() + " ");
        Log.d("TAG11", "size" + mStudents.size());
        mAdapter.notifyDataSetChanged();
        Log.d("TAG111", "HERE EDIT");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG111", "Resume ");
        Log.d("tag1", "student" + student);
        initData().add(student);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG111", "Start");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG111", "Created");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TAG111", "OnAttach");
    }
}
