package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentFragment extends Fragment {
    MyArrayAdapter mAdapter = null;
    ListView mListView;
    ArrayList<SinhVien> mArr = new ArrayList<SinhVien>();
    ImageButton mFragImgBtn;
    private AddSVFragment mAddSVFragment;
    OnHeadlineSelectedListener2 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, null);
        mListView = (ListView) view.findViewById(R.id.fragStudentLv);
        mFragImgBtn = (ImageButton) view.findViewById(R.id.fragStudentImgBtn);

        MainActivity main = (MainActivity) getActivity();
        mArr = main.getmArr();
        mAdapter = new MyArrayAdapter(getActivity(), R.layout.custom_item_listview, mArr);
        mAddSVFragment = new AddSVFragment();

        mListView.setAdapter(mAdapter);
        mFragImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSVFragment fr = new AddSVFragment();
                replaceAFragment(fr, true, R.id.activity_main);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onButtonClicked2(mArr.get(i));

            }
        });


        return view;
    }


    public void replaceAFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.commit();
        }
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener2 {
        public void onArticleSelected2(SinhVien sv);
    }

    public void onButtonClicked2(SinhVien sv) {
        mCallback.onArticleSelected2(sv);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
