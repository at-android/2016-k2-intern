package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_edit)
public class EditFragment extends Fragment {
    public OnHeadlineSelectedListener mCallback;
    @ViewById(R.id.edtSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtAddress)
    EditText mEdtAddress;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @FragmentArg("student")
    Student mStudent;
    @FragmentArg("position")
    int mPosition;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void init() {
        mEdtSchool.setText(mStudent.getSchool());
        mEdtAddress.setText(mStudent.getAddress());
        mEdtAge.setText(mStudent.getAge());
        mEdtName.setText(mStudent.getName());
    }

    @Click(R.id.imgBack)
    void backToInfo() {
        mStudent.setName(mEdtName.getText().toString());
        mStudent.setAddress(mEdtAddress.getText().toString());
        mStudent.setAge(mEdtAge.getText().toString());
        mStudent.setSchool(mEdtSchool.getText().toString());
        if (mCallback != null) {
            mCallback.onFragmentEditInteraction(mStudent, mPosition);
        }
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
        void onFragmentEditInteraction(Student student, int position);
    }
}
