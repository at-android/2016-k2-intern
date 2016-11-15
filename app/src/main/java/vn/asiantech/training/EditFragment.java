package vn.asiantech.training;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditFragment extends Fragment {
    OnHeadlineSelectedListener mCallback;
    private Student student;
    private int position;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            student = (Student) bundle.getSerializable("student");
            position = bundle.getInt("position");
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

        edtSchool.setText(student.getSchool());
        edtAddress.setText(student.getAddress());
        edtAge.setText(student.getAge());
        edtName.setText(student.getName());
        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(edtName.getText().toString());
                student.setAddress(edtAddress.getText().toString());
                student.setAge(edtAge.getText().toString());
                student.setSchool(edtSchool.getText().toString());
                onButtonPressed(student, position);
            }
        });

        return view;
    }

    public void onButtonPressed(Student student, int position) {
        if (mCallback != null) {
            mCallback.onArticleSelected(student, position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(Student student, int position);
    }
}
