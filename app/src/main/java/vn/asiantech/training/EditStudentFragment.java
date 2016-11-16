package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditStudentFragment.OnWaitPositionForEdit} interface
 * to handle interaction events.
 * Use the {@link EditStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int KEY_POSITION;
    private EditText edtName;
    private EditText edtSchool;
    private EditText edtAddress;
    private EditText edtOld;
    private ImageButton btEditInfomation;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnWaitPositionForEdit mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        edtName = (EditText) view.findViewById(R.id.edtEditName);
        edtSchool = (EditText) view.findViewById(R.id.edtEditSchool);
        edtAddress = (EditText) view.findViewById(R.id.edtEditAddress);
        edtOld = (EditText) view.findViewById(R.id.edtEditOld);
        btEditInfomation = (ImageButton) view.findViewById(R.id.btEditStudent);
        final MainActivity mainActivity = (MainActivity) getActivity();
        edtName.setText(mainActivity.arr.get(KEY_POSITION).getName());
        edtSchool.setText(mainActivity.arr.get(KEY_POSITION).getSchool());
        edtAddress.setText(mainActivity.arr.get(KEY_POSITION).getAddress());
        edtOld.setText(mainActivity.arr.get(KEY_POSITION).getOld());
        btEditInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentObject studentObject = new StudentObject();
                studentObject.setName(edtName.getText().toString());
                studentObject.setSchool(edtSchool.getText().toString());
                studentObject.setAddress(edtAddress.getText().toString());
                studentObject.setOld(edtOld.getText().toString());
                mainActivity.arr.set(KEY_POSITION, studentObject);
                mListener.sendKeyPositionFormEditToInfo(KEY_POSITION);
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
        KEY_POSITION = position;
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
