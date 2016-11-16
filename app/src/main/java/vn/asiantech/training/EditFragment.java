package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditFragment extends Fragment {
    public OnHeadlineSelectedListener mCallback;
    private Student mStudent;
    private int mPosition;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mStudent = (Student) bundle.getSerializable(DemoFragmentActivity.KEY_STUDENT);
            mPosition = bundle.getInt(DemoFragmentActivity.KEY_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        final EditText edtSchool = (EditText) view.findViewById(R.id.edtSchool);
        final EditText edtAddress = (EditText) view.findViewById(R.id.edtAddress);
        final EditText edtAge = (EditText) view.findViewById(R.id.edtAge);
        final EditText edtName = (EditText) view.findViewById(R.id.edtName);
        ImageButton imgbBack = (ImageButton) view.findViewById(R.id.imgBack);

        edtSchool.setText(mStudent.getSchool());
        edtAddress.setText(mStudent.getAddress());
        edtAge.setText(mStudent.getAge());
        edtName.setText(mStudent.getName());
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudent.setName(edtName.getText().toString());
                mStudent.setAddress(edtAddress.getText().toString());
                mStudent.setAge(edtAge.getText().toString());
                mStudent.setSchool(edtSchool.getText().toString());
                if (mCallback != null) {
                    mCallback.onFragmentInteraction1(mStudent, mPosition);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    interface OnHeadlineSelectedListener {
        void onFragmentInteraction1(Student student, int position);
    }
}
