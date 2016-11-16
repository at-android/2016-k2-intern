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
    private ArrayList<Student> mStudents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        ImageButton imgbAdd = (ImageButton) view.findViewById(R.id.imgbAdd);
        final ListView listView = (ListView) view.findViewById(R.id.listView);
        StudentAdapter studentArrayAdapter = new StudentAdapter(getActivity(), R.layout.fragment_list_student, mStudents);
        listView.setAdapter(studentArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                FragmentManager fragmentManager = getFragmentManager();
                bundle.putSerializable(DemoFragmentActivity.KEY_STUDENT, mStudents.get(position));
                bundle.putInt(DemoFragmentActivity.KEY_POSITION, position);
                InformationFragment info = new InformationFragment();
                info.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.flContainer, info).commit();
            }
        });

        imgbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, addFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

    public void passData(ArrayList<Student> students) {
        this.mStudents = students;
    }
}
