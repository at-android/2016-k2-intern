package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment
public class StudentInforFragment extends Fragment {
    @ViewById(R.id.imgBtnLeft)
    ImageButton mImgBtnLeft;
    @ViewById(R.id.imgBtnRight)
    ImageButton mImgBtnRight;
    @ViewById(R.id.tvSchoolName)
    TextView mTvSchoolName;
    @ViewById(R.id.tvName)
    TextView mTvName;
    @ViewById(R.id.tvAge)
    TextView mTvAge;
    @ViewById(R.id.tvAddress)
    TextView mTvAddress;
    @FragmentArg
    SinhVien mSinhVien;
    OnHeadlineSelectedListener3 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_infor, null);
        return view;
    }

    @AfterViews
    public void createData() {
        SinhVien sv = mSinhVien;
        mTvSchoolName.setText(sv.getSchoolName());
        mTvName.setText(sv.getName());
        mTvAge.setText(sv.getAge());
        mTvAddress.setText(sv.getAddress());

    }

    @Click(R.id.imgBtnLeft)
    public void ClickImgBtnLeft() {
        StudentFragment frag = new StudentFragment_();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_main, frag).commit();
    }

    @Click(R.id.imgBtnRight)
    public void ClickImgBtnRight() {
        SinhVien sv = new SinhVien();
        sv.setSchoolName(mTvSchoolName.getText().toString());
        sv.setName(mTvName.getText().toString());
        sv.setAge(mTvAge.getText().toString());
        sv.setAddress(mTvAddress.getText().toString());
        mCallback.onArticleSelected3(sv);
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener3 {
        public void onArticleSelected3(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener3) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
