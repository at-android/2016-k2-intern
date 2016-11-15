package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentFragment extends Fragment  {
    MyArrayAdapter mAdapter = null;
    ListView mListView;
    ArrayList<SinhVien> mArr = new ArrayList<SinhVien>();
    ImageButton mFragImgBtn;
    private AddSVFragment mAddSVFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SinhVien sv1 = new SinhVien("DUT", "Tran Van A", "19", "Da Nang");
//        SinhVien sv2 = new SinhVien("DUT", "Tran Van B", "20", "Da Nang");
//        SinhVien sv3 = new SinhVien("DUT", "Tran Van C", "21", "Da Nang");
//        mArr.add(sv1);
//        mArr.add(sv2);
//        mArr.add(sv3);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, null);
        mListView = (ListView) view.findViewById(R.id.fragStudentLv);
        mFragImgBtn = (ImageButton) view.findViewById(R.id.fragStudentImgBtn);
//        SinhVien sv1 = new SinhVien("DUT", "Tran Van A", "19", "Da Nang");
//        SinhVien sv2 = new SinhVien("DUT", "Tran Van B", "20", "Da Nang");
//        SinhVien sv3 = new SinhVien("DUT", "Tran Van C", "21", "Da Nang");
//        mArr.add(sv1);
//        mArr.add(sv2);
//        mArr.add(sv3);
        MainActivity main = new MainActivity();
        mArr = main.getmArr();

        mAdapter = new MyArrayAdapter(getActivity(), R.layout.custom_item_listview, mArr);
        //nhan du lieu
        mAddSVFragment = new AddSVFragment();

        mListView.setAdapter(mAdapter);
        mFragImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSVFragment fr = new AddSVFragment();
                replaceAFragment(fr, true, R.id.activity_main);
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

    public void UpdateData(SinhVien sv) {
        Log.i("SinhVien",sv.getSchoolName());

//        mAdapter.notifyDataSaveChanged();
    }

}
