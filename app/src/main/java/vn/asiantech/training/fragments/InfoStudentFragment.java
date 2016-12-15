package vn.asiantech.training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.training.MainActivity_;
import vn.asiantech.training.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoStudentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@EFragment(R.layout.fragment_info_student)
public class InfoStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int mPOSITION = 0;
    @ViewById(R.id.tvInforName)
    TextView tvInforName;
    @ViewById(R.id.tvInforAddress)
    TextView tvInforAddress;
    @ViewById(R.id.tvInforSchool)
    TextView tvInforSchool;
    @ViewById(R.id.tvInforOld)
    TextView tvInforOld;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public InfoStudentFragment() {
        // Required empty public constructor
    }

    public static InfoStudentFragment newInstance(String param1, String param2) {
        InfoStudentFragment fragment = new InfoStudentFragment();
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

    public void getInforPosition(int position) {
        mPOSITION = position;
    }

    @AfterViews
    void updateDataToUI() {
        MainActivity_ mainActivity = (MainActivity_) getActivity();
        tvInforSchool.setText(mainActivity.studentArrays.get(mPOSITION).getSchool());
        tvInforAddress.setText(mainActivity.studentArrays.get(mPOSITION).getAddress());
        tvInforName.setText(mainActivity.studentArrays.get(mPOSITION).getName());
        tvInforOld.setText(mainActivity.studentArrays.get(mPOSITION).getOld() + "");
    }

    @Click(R.id.imgBackToListFormInfo)
    void backListFormInfo() {
        mListener.onBackListFormInfor();
    }

    @Click(R.id.imgToEdit)
    void goToEditStudent() {
        mListener.onEditStudent(mPOSITION);
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
        void onBackListFormInfor();

        void onEditStudent(int position);
    }
}
