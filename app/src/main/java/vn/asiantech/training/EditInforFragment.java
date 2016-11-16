package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class EditInforFragment extends Fragment {
    private EditText mEdSchool;
    private EditText mEdName;
    private EditText mEdAge;
    private EditText mEdAddress;
    private ImageButton mImgBtnLeft;
    private OnHeadlineSelectedListener4 mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_infor, null);
        mEdSchool = (EditText) view.findViewById(R.id.edSchoolName);
        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdAge = (EditText) view.findViewById(R.id.edAge);
        mEdAddress = (EditText) view.findViewById(R.id.edAddress);
        mImgBtnLeft = (ImageButton) view.findViewById(R.id.imgBtnLeft);

        Bundle bundle = getArguments();
        SinhVien sv = (SinhVien) bundle.getSerializable("StudentInfor");

        mEdSchool.setText(sv.getSchoolName().toString());
        mEdName.setText(sv.getName().toString());
        mEdAge.setText(sv.getAge().toString());
        mEdAddress.setText(sv.getAddress().toString());

        mImgBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mEdSchool.getText().toString());
                sv.setName(mEdName.getText().toString());
                sv.setAge(mEdAge.getText().toString());
                sv.setAddress(mEdAddress.getText().toString());
                mCallback.onArticleSelected4(sv);
            }
        });

        return view;
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener4 {
        public void onArticleSelected4(SinhVien sv);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener4) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
