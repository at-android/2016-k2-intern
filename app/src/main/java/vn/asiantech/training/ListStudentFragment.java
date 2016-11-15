package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class ListStudentFragment extends Fragment {
    ArrayList<Student> students;
    int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        students = new ArrayList<Student>();
        students.add(new Student("DUT", "Duy Le", "Da Nang", "21"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "22"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "23"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "24"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "25"));
        students.add(new Student("DUT", "Duy Le", "Da Nang", "26"));
        if (bundle != null) {
            pos = bundle.getInt(Exfragment.POSITION);
            students.set(pos, (Student) bundle.getSerializable(Exfragment.STUDENT_NAME));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        ImageButton imgbAdd = (ImageButton) view.findViewById(R.id.imgbAdd);
        final ListView listView = (ListView) view.findViewById(R.id.listView);
        StudentAdapter studentArrayAdapter = new StudentAdapter(getActivity(), R.layout.fragment_list_student, students);
        listView.setAdapter(studentArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                FragmentManager fragmentManager = getFragmentManager();
                bundle.putSerializable(Exfragment.STUDENT_NAME, students.get(position));
                bundle.putInt(Exfragment.POSITION, position);
                InformationFragment info = new InformationFragment();
                info.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flContainer, info, "info").commit();
            }
        });

        imgbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
