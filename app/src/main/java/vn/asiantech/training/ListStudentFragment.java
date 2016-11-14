package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ListStudentFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("DUT", "Duy Le", "Da Nang", "21"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "22"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "23"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "24"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "25"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "26"));
        ListView listView = (ListView) view.findViewById(R.id.listView);
        StudentAdapter studentArrayAdapter = new StudentAdapter(getActivity(), R.layout.fragment_list_student, students);
        listView.setAdapter(studentArrayAdapter);
        return view;
    }


}
