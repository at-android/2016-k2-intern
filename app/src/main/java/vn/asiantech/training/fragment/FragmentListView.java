package vn.asiantech.training.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.StudentAdapter;
import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 14/11/2016.
 */

public class FragmentListView extends Fragment {
    private ListView mLvStudent;
    private StudentAdapter mStudentAdapter;
    private ArrayList<Student> mArrStudent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview,null);

        mLvStudent = (ListView) view.findViewById(R.id.lvStudent);

        mArrStudent = new ArrayList<>();
        mArrStudent.add(new Student("Phuong","22","Quang Nam","Bach Khoa"));
        mArrStudent.add(new Student("Phu","20","Quang Binh","Su Pham"));
        mArrStudent.add(new Student("Huong","23","Da Nang","Bach Khoa"));
        mArrStudent.add(new Student("Thanh","21","Quang Tri","Ngoai Ngu"));
        mArrStudent.add(new Student("Van","19","Quang Ngai","Bach Khoa"));
        mStudentAdapter = new StudentAdapter(getContext(),mArrStudent);
        mLvStudent.setAdapter(mStudentAdapter);

        return view;
    }
}
