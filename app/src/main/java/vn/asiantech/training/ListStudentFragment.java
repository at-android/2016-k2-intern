package vn.asiantech.training;


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
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListStudentFragment extends Fragment {
    public getPositionformListView mListener;
    ListStudentArrayAdapter myAdapter = null;
    ListView lvStudent;
    ArrayList<StudentObject> arraylist = new ArrayList<>();
    private ImageButton btAddNewStudent;

    public ListStudentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_student, container, false);
        lvStudent = (ListView) view.findViewById(R.id.listStudent);
        MainActivity main = (MainActivity) getActivity();
        arraylist = main.arr;
        myAdapter = new ListStudentArrayAdapter(getActivity(), R.layout.student_custom_list, arraylist);
        //insertData();
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
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.setPositionFormListToInfo(position);
            }
        });
        return view;
    }

    public void addData(StudentObject std) {
        arraylist.add(std);
        Log.d("size", arraylist.size() + "");
    }

    @Override
    public void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListStudentFragment.getPositionformListView) {
            mListener = (ListStudentFragment.getPositionformListView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListStudentFragment");
        }
    }

    public interface getPositionformListView {
        // TODO: Update argument type and name
        void setPositionFormListToInfo(int position);
    }
}
