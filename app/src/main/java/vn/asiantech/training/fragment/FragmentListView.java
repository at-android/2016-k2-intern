package vn.asiantech.training.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.asiantech.training.App;
import vn.asiantech.training.R;
import vn.asiantech.training.adapter.StudentAdapter;
import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 14/11/2016.
 */

public class FragmentListView extends Fragment implements FragmentAddStudent.ListenerCallbackData {
    public static final String STUDENT_DETAIL = "student";
    public static final String POSITION = "position";
    public static final String NAME_FRAGMENT = "FragmentListView";
    private ListView mLvStudent;
    private StudentAdapter mStudentAdapter;
    private ArrayList<Student> mArrStudent = new ArrayList<>();
    private ImageView mImvAdd;
    private FragmentAddStudent mFragmentAddStudent;
    private FragmentDetailStudent mFragmentDetailStudent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, null);
        mLvStudent = (ListView) view.findViewById(R.id.lvStudent);
        mImvAdd = (ImageView) view.findViewById(R.id.imvAdd);
        mFragmentAddStudent = new FragmentAddStudent();
        mFragmentAddStudent.setListennerSendData(this);
        mFragmentDetailStudent = new FragmentDetailStudent();

        Log.d("here",String.valueOf(mArrStudent.size()));
        mStudentAdapter = new StudentAdapter(getContext(), mArrStudent);
        mLvStudent.setAdapter(mStudentAdapter);

        mImvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(mFragmentAddStudent, true, R.id.frContain, FragmentAddStudent.NAME_FRAGMENT);
            }
        });

        mLvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt(POSITION, i);
                bundle.putParcelable(STUDENT_DETAIL, mArrStudent.get(i));
                mFragmentDetailStudent.setArguments(bundle);
                switchFragment(mFragmentDetailStudent, true, R.id.frContain, "");
            }
        });

        return view;
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, int id, String nameFragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment, nameFragment);
        if (addToBackStack) {
            ft.addToBackStack(nameFragment);
        }
        ft.commit();
    }

    @Override
    public void sendData(Student student) {
        ((App) getActivity().getApplication()).addStudent(student);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mArrStudent = ((App) getActivity().getApplication()).getData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
