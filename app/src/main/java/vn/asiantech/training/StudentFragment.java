package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

@EFragment
public class StudentFragment extends Fragment {
    MyArrayAdapter mAdapter = null;
    @ViewById(R.id.lv)
    ListView mLv;
    ArrayList<SinhVien> mArr = new ArrayList<SinhVien>();
    OnHeadlineSelectedListener2 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, null);
        return view;
    }

    @AfterViews
    public void CreateDataListview(){
        MainActivity main = (MainActivity) getActivity();
        mArr = main.getmArr();
        mAdapter = new MyArrayAdapter(getActivity(), R.layout.custom_item_listview, mArr);
        mLv.setAdapter(mAdapter);
    }

    @Click(R.id.imgBtn)
    public void ClickImgBtn(){
        AddSVFragment fr = new AddSVFragment_();
        replaceAFragment(fr, true, R.id.activity_main);
    }

    @ItemClick(R.id.lv)
    public void clickItemLV(int position){
        mCallback.onArticleSelected2(mArr.get(position), position);
    }

    public void replaceAFragment(Fragment fragment, boolean addToBackStack, int id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener2 {
        public void onArticleSelected2(SinhVien sv, int position);
    }

    @Override
    public void onAttach(Context activity) {
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
