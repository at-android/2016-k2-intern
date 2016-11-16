package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class StudentInforFragment extends Fragment implements View.OnClickListener{
    ImageButton mImgLeft, mImgRight;
    TextView mTvSchoolName, mTvName, mTvAge, mTvAddress;
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
        mImgLeft = (ImageButton) view.findViewById(R.id.InforLeftArrow);
        mImgRight = (ImageButton) view.findViewById(R.id.InforRightArrow);
        mTvSchoolName = (TextView) view.findViewById(R.id.InforSchoolName);
        mTvName = (TextView) view.findViewById(R.id.InforName);
        mTvAge = (TextView) view.findViewById(R.id.InforAge);
        mTvAddress = (TextView) view.findViewById(R.id.InforAddress);
        Bundle bundle = getArguments();
        SinhVien sv = (SinhVien) bundle.getSerializable("StudentInfor");
        mTvSchoolName.setText(sv.getSchoolName());
        mTvName.setText(sv.getName());
        mTvAge.setText(sv.getAge());
        mTvAddress.setText(sv.getAddress());

        mImgRight.setOnClickListener(this);
        mImgLeft.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.InforLeftArrow:

                break;
            case R.id.InforRightArrow:
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mTvSchoolName.getText().toString());
                sv.setName(mTvName.getText().toString());
                sv.setAge(mTvAge.getText().toString());
                sv.setAddress(mTvAddress.getText().toString());
                onButtonClicked3(sv);
                break;
        }
    }


    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener3 {
        public void onArticleSelected3(SinhVien sv);
    }

    public void onButtonClicked3(SinhVien sv) {
        mCallback.onArticleSelected3(sv);
    }

    @Override
    public void onAttach(Activity activity) {
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
