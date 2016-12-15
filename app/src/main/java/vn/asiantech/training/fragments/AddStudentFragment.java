package vn.asiantech.training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.R;
import vn.asiantech.training.objects.StudentObj;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStudentFragment.onAddStudentFragmentCallBack} interface
 * to handle interaction events.
 * Use the {@link AddStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@EFragment(R.layout.fragment_add_student)
public class AddStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @ViewById(R.id.edtSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtName)
    EditText mEdtName;
    @ViewById(R.id.edtAddress)
    EditText mEdtAddress;
    @ViewById(R.id.edtOld)
    EditText mEdtOld;
    private onAddStudentFragmentCallBack mListener;

    public AddStudentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddStudentFragment newInstance(String param1, String param2) {
        AddStudentFragment fragment = new AddStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Click(R.id.btnAddStudent)
    void addStudent() {
        mListener.addStudentAndBackToList(new StudentObj(mEdtSchool.getText().toString(), mEdtName.getText().toString(), mEdtAddress.getText().toString(), Integer.parseInt(mEdtOld.getText().toString())));
    }

    @Click(R.id.imgBackToList)
    void backList() {
        mListener.backListFormAdd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onAddStudentFragmentCallBack) {
            mListener = (onAddStudentFragmentCallBack) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onAddStudentFragmentCallBack");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onAddStudentFragmentCallBack {
        void addStudentAndBackToList(StudentObj studentObj);

        void backListFormAdd();
    }
}
