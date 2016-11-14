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
        StudentObject stOj1 = new StudentObject();
        stOj1.setName("Hoang Long");
        stOj1.setSchool("FPT Poly");
        stOj1.setAddress("Sai Gon");
        stOj1.setOld("21");
        studentArr.add(stOj1);
        StudentObject stOj2 = new StudentObject();
        stOj2.setName("Nhat Hai");
        stOj2.setSchool("FPT Poly");
        stOj2.setAddress("Ha Noi");
        stOj2.setOld("21");
        studentArr.add(stOj2);
        StudentObject stOj3 = new StudentObject();
        stOj3.setName("Phung Thien");
        stOj3.setSchool("FPT Poly");
        stOj3.setAddress("Hue");
        stOj3.setOld("21");
        studentArr.add(stOj3);
    }
}
