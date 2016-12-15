package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_list_student)
public class ListStudentFragment extends Fragment {

    @ViewById(R.id.listView)
    ListView mListView;
    private ArrayList<Student> mStudents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @AfterViews
    void init() {
        StudentAdapter studentArrayAdapter = new StudentAdapter(getActivity(), R.layout.fragment_list_student, mStudents);
        mListView.setAdapter(studentArrayAdapter);
    }

    @ItemClick(R.id.listView)
    public void ItemClickListener(int position) {
        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putParcelable(DemoFragmentActivity.KEY_STUDENT, mStudents.get(position));
        bundle.putInt(DemoFragmentActivity.KEY_POSITION, position);
        InformationFragment info = new InformationFragment_();
        info.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.flContainer, info).commit();
    }

    @Click(R.id.imgbAdd)
    void add() {
        AddFragment addFragment = new AddFragment_();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContainer, addFragment).addToBackStack(null).commit();
    }

    public void passData(ArrayList<Student> students) {
        this.mStudents = students;
    }
}
