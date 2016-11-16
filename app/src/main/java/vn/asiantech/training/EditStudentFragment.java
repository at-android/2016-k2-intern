package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private int mKEY_POSITION;
    private EditText mEtName;
    private EditText mEtSchool;
    private EditText mEtAddress;
    private EditText mEtOld;
    private ImageButton mBtnEditInformation;
    // TODO: Rename and change types of parameters

    private OnWaitPositionForEdit mListener;

    public EditStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        mEtName = (EditText) view.findViewById(R.id.edtEditName);
        mEtSchool = (EditText) view.findViewById(R.id.edtEditSchool);
        mEtAddress = (EditText) view.findViewById(R.id.edtEditAddress);
        mEtOld = (EditText) view.findViewById(R.id.edtEditOld);
        mBtnEditInformation = (ImageButton) view.findViewById(R.id.btEditStudent);
        final MainActivity mainActivity = (MainActivity) getActivity();
        mEtName.setText(mainActivity.sMainStudentArray.get(mKEY_POSITION).getName());
        mEtSchool.setText(mainActivity.sMainStudentArray.get(mKEY_POSITION).getSchool());
        mEtAddress.setText(mainActivity.sMainStudentArray.get(mKEY_POSITION).getAddress());
        mEtOld.setText(mainActivity.sMainStudentArray.get(mKEY_POSITION).getOld());
        mBtnEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentObject studentObject = new StudentObject();
                studentObject.setName(mEtName.getText().toString());
                studentObject.setSchool(mEtSchool.getText().toString());
                studentObject.setAddress(mEtAddress.getText().toString());
                studentObject.setOld(mEtOld.getText().toString());
                mainActivity.sMainStudentArray.set(mKEY_POSITION, studentObject);
                mListener.sendKeyPositionFormEditToInfo(mKEY_POSITION);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWaitPositionForEdit) {
            mListener = (OnWaitPositionForEdit) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnWaitPositionForEdit");
        }
    }

    public void setKeyPosition(int position) {
        mKEY_POSITION = position;
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
    public interface OnWaitPositionForEdit {
        // TODO: Update argument type and name
        void sendKeyPositionFormEditToInfo(int keyposition);
    }
}
