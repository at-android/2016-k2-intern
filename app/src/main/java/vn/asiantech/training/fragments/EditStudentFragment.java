package vn.asiantech.training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.MainActivity_;
import vn.asiantech.training.R;
import vn.asiantech.training.objects.StudentObj;

@EFragment(R.layout.fragment_edit_student)
public class EditStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int mPOSITION;
    @ViewById(R.id.edtEditSchool)
    EditText mEdtSchool;
    @ViewById(R.id.edtEditName)
    EditText mEdtName;
    @ViewById(R.id.edtEditAddress)
    EditText mEdtAddress;
    @ViewById(R.id.edtEditOld)
    EditText mEdtOld;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public EditStudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditStudentFragment newInstance(String param1, String param2) {
        EditStudentFragment fragment = new EditStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    public void getPositionFormInfo(int pos) {
        mPOSITION = pos;
    }

    @AfterViews
    void showDataUi() {
        MainActivity_ mainActivity = (MainActivity_) getActivity();
        mEdtSchool.setText(mainActivity.studentArrays.get(mPOSITION).getSchool());
        mEdtAddress.setText(mainActivity.studentArrays.get(mPOSITION).getAddress());
        mEdtName.setText(mainActivity.studentArrays.get(mPOSITION).getName());
        mEdtOld.setText(mainActivity.studentArrays.get(mPOSITION).getOld() + "");
    }

    @Click(R.id.btnEditStudent)
    void editStudent() {
        MainActivity_ mainActivity = (MainActivity_) getActivity();
        mainActivity.studentArrays.set(mPOSITION, new StudentObj(mEdtSchool.getText().toString(), mEdtName.getText().toString(), mEdtAddress.getText().toString(), Integer.parseInt(mEdtOld.getText().toString())));
        mListener.onBackInforFormEdit(mPOSITION);
    }

    @Click(R.id.imgBackInfoFormEdit)
    void backInfor() {
        mListener.onBackInforFormEdit(mPOSITION);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onBackInforFormEdit(int pos);
    }
}
