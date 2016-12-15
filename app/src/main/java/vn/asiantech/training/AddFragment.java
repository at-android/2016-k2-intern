package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_add)
public class AddFragment extends Fragment {

    public OnFragmentInteractionListener mListener;
    @ViewById(R.id.edtSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtAddress)
    EditText mEdtAddress;
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAge)
    EditText mEdtAge;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Click(R.id.btnAdd)
    void add() {
        Student student = new Student(mEdtSchool.getText().toString(), mEdtName.getText().toString(),
                mEdtAddress.getText().toString(),
                mEdtAge.getText().toString());
        if (mListener != null) {
            mListener.onFragmentAddInteraction(student);
        }
    }

    @Click(R.id.imgbBack)
    void backtoListStudent() {
        getActivity().onBackPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentAddInteraction(Student student);
    }
}
