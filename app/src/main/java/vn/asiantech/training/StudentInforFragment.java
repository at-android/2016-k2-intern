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

public class StudentInforFragment extends Fragment implements View.OnClickListener {
    private ImageButton mImgBtnLeft, mImgBtnRight;
    private TextView mTvSchoolName, mTvName, mTvAge, mTvAddress;
    private OnHeadlineSelectedListener3 mCallback;
    public static final String KeyStudent = "StudentInfor";
    public static final String KeyPosition = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_infor, null);
        mImgBtnLeft = (ImageButton) view.findViewById(R.id.imgBtnLeft);
        mImgBtnRight = (ImageButton) view.findViewById(R.id.imgBtnRight);
        mTvSchoolName = (TextView) view.findViewById(R.id.tvSchoolName);
        mTvName = (TextView) view.findViewById(R.id.tvName);
        mTvAge = (TextView) view.findViewById(R.id.tvAge);
        mTvAddress = (TextView) view.findViewById(R.id.tvAddress);
        Bundle bundle = getArguments();
        SinhVien sv = (SinhVien) bundle.getSerializable(KeyStudent);
        mTvSchoolName.setText(sv.getSchoolName());
        mTvName.setText(sv.getName());
        mTvAge.setText(sv.getAge());
        mTvAddress.setText(sv.getAddress());
        mImgBtnRight.setOnClickListener(this);
        mImgBtnLeft.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnLeft:
                StudentFragment frag = new StudentFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.activity_main, frag).commit();

                break;
            case R.id.imgBtnRight:
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mTvSchoolName.getText().toString());
                sv.setName(mTvName.getText().toString());
                sv.setAge(mTvAge.getText().toString());
                sv.setAddress(mTvAddress.getText().toString());
                mCallback.onArticleSelected3(sv);
                break;
        }
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener3 {
        public void onArticleSelected3(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener3) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
