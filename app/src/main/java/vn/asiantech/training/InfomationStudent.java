package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class InfomationStudent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txtName, txtSchool, txtAddress, txtOld;
    ArrayList<StudentObject> listStudent = new ArrayList<StudentObject>();
    private int keyPosition;
    private String name;
    private String school;
    private String address;
    private String old;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private onWaitInformation mListener;

    public InfomationStudent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfomationStudent.
     */
    // TODO: Rename and change types and number of parameters
    public static InfomationStudent newInstance(String param1, String param2) {
        InfomationStudent fragment = new InfomationStudent();
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
        View view = inflater.inflate(R.layout.fragment_infomation_student, container, false);
        txtName = (TextView) view.findViewById(R.id.txtinfoName);
        txtSchool = (TextView) view.findViewById(R.id.txtInfoSchool);
        txtAddress = (TextView) view.findViewById(R.id.txtInfoAddress);
        txtOld = (TextView) view.findViewById(R.id.txtInfoOld);
        MainActivity main = (MainActivity) getActivity();
        listStudent = main.arr;
        txtName.setText(listStudent.get(keyPosition).getName());
        txtSchool.setText(listStudent.get(keyPosition).getSchool());
        txtAddress.setText(listStudent.get(keyPosition).getAddress());
        txtOld.setText(listStudent.get(keyPosition).getOld());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(StudentObject object) {
        if (mListener != null) {
            mListener.getInformation(object);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onWaitInformation) {
            mListener = (onWaitInformation) context;
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

    public void getInformation(int position) {
        keyPosition = position;
    }

    public interface onWaitInformation {
        // TODO: Update argument type and name
        void getInformation(StudentObject object);
    }
}
