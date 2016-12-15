package vn.asiantech.training.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.MainActivity;
import vn.asiantech.training.adapters.RecyclerListStudentAdapter;
import vn.asiantech.training.listerners.callOnClickListener;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 14/12/2016.
 */
@EFragment(R.layout.fragment_list_student)
public class ListStudentFragment extends Fragment implements callOnClickListener {

    @ViewById(R.id.rvStudent)
    RecyclerView mRcStudent;
    @ViewById(R.id.imgAdd)
    ImageView mImgAdd;

    private RecyclerListStudentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Student> mStudents;
    private DetailStudentFragment mDetailStudentFragment;

    @AfterViews
    void callRecyclerView() {

        MainActivity mainActivity = (MainActivity) getActivity();
        mAdapter = new RecyclerListStudentAdapter(mainActivity.mStudents, getContext(), this);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRcStudent.setLayoutManager(mLayoutManager);
        mRcStudent.setAdapter(mAdapter);
    }

    @Click(R.id.imgAdd)
    void addStudent() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frContain, AddStudentFragment_.builder().build());
        ft.addToBackStack(ListStudentFragment.class.getName());
        ft.commit();
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

    public void initData(List<Student> students) {
        mStudents = students;
    }
}
