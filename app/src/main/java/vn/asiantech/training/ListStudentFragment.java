package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListStudentFragment extends Fragment {
    ArrayList<StudentObject> studentArr = null;
    ListStudentArrayAdapter myAdapter = null;

    public ListStudentFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        insertData();
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        ListView lvStudent = (ListView) view.findViewById(R.id.listStudent);
        myAdapter = new ListStudentArrayAdapter(getActivity(), R.layout.student_custom_list, studentArr);
        lvStudent.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        return view;
    }


    public void insertData() {
        studentArr = new ArrayList<StudentObject>();
        StudentObject stOj = new StudentObject();
        stOj.setName("Manh Duy");
        stOj.setSchool("FPT Poly");
        stOj.setAddress("Da Nang");
        stOj.setOld("21");
        studentArr.add(stOj);
        stOj.setName("Hoang Long");
        stOj.setSchool("FPT Poly");
        stOj.setAddress("Sai Gon");
        stOj.setOld("21");
        studentArr.add(stOj);
        stOj.setName("Nhat Hai");
        stOj.setSchool("FPT Poly");
        stOj.setAddress("Ha Noi");
        stOj.setOld("21");
        studentArr.add(stOj);
        stOj.setName("Phung Thien");
        stOj.setSchool("FPT Poly");
        stOj.setAddress("Hue");
        stOj.setOld("21");
        studentArr.add(stOj);
    }
}
