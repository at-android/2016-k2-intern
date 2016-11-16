package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class EditInforFragment extends Fragment {
    EditText mEdtSchool,mEdtName,mEdtAge,mEdtAddress;
    ImageButton mLeftArrow;
    OnHeadlineSelectedListener4 mCallback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_infor, null);
        mEdtSchool = (EditText)view.findViewById(R.id.EditInforSchoolName);
        mEdtName = (EditText)view.findViewById(R.id.EditInforName);
        mEdtAge = (EditText)view.findViewById(R.id.EditInforAge);
        mEdtAddress = (EditText)view.findViewById(R.id.EditInforAddress);
        mLeftArrow = (ImageButton)view.findViewById(R.id.EditInforLeftArrow);

        Bundle bundle = getArguments();
        SinhVien sv = (SinhVien)bundle.getSerializable("StudentInfor");

        mEdtSchool.setText(sv.getSchoolName().toString());
        mEdtName.setText(sv.getName().toString());
        mEdtAge.setText(sv.getAge().toString());
        mEdtAddress.setText(sv.getAddress().toString());

        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sv = new SinhVien();
                sv.setSchoolName(mEdtSchool.getText().toString());
                sv.setName(mEdtName.getText().toString());
                sv.setAge(mEdtAge.getText().toString());
                sv.setAddress(mEdtAddress.getText().toString());
                onButtonClicked4(sv);
            }
        });

        return view;
    }



    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener4 {
        public void onArticleSelected4(SinhVien sv);
    }

    public void onButtonClicked4(SinhVien sv) {
        mCallback.onArticleSelected4(sv);
    }

    @Override
    public void onAttach(Activity activity) {
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
