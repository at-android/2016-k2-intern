package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListStudentFragment extends Fragment implements AddStudentFragment.CallbackUpdateView {
    public static ArrayList<StudentObject> studentArr = null;
    ListStudentArrayAdapter myAdapter = null;
    ListView lvStudent;
    ImageButton btAddNewStudent;
    AddStudentFragment addStudentFragment = new AddStudentFragment();
    public ListStudentFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        insertData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        lvStudent = (ListView) view.findViewById(R.id.listStudent);
        addStudentFragment.daidieninterface(this);
        myAdapter = new ListStudentArrayAdapter(getActivity(), R.layout.student_custom_list, studentArr);
        lvStudent.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        btAddNewStudent = (ImageButton) view.findViewById(R.id.imgbtStartAddActivity);
        btAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddStudentFragment fragment = new AddStudentFragment();
                fragmentTransaction.add(R.id.frLayoutMain, fragment, "AddStudent");
                fragmentTransaction.commit();
            }
        });
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

    @Override
    public void onClickUpdate(String name, String school, String address, String old) {
        if (name.equals("") && school.equals("") && address.equals("") && old.equals("")) {
            StudentObject stOjNew = new StudentObject();
            stOjNew.setName(name);
            stOjNew.setSchool(school);
            stOjNew.setAddress(address);
            stOjNew.setOld(old);
            studentArr.add(stOjNew);
            myAdapter.notifyDataSetChanged();
        }
    }
}
