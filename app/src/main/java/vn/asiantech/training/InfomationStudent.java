package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class InfomationStudent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<StudentObject> listStudent = new ArrayList<StudentObject>();
    private TextView tvName;
    private TextView tvSchool;
    private TextView tvAddress;
    private TextView tvOld;
    private int keyPosition;
    private ImageButton btnNext;
    private ImageButton btnBack;
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
        tvName = (TextView) view.findViewById(R.id.txtinfoName);
        tvSchool = (TextView) view.findViewById(R.id.txtInfoSchool);
        tvAddress = (TextView) view.findViewById(R.id.txtInfoAddress);
        tvOld = (TextView) view.findViewById(R.id.txtInfoOld);
        btnNext = (ImageButton) view.findViewById(R.id.immgbtInfoStudentNext);
        btnBack = (ImageButton) view.findViewById(R.id.imgbtInfoStudentBack);
        MainActivity main = (MainActivity) getActivity();
        listStudent = main.arr;
        tvName.setText(listStudent.get(keyPosition).getName());
        tvSchool.setText(listStudent.get(keyPosition).getSchool());
        tvAddress.setText(listStudent.get(keyPosition).getAddress());
        tvOld.setText(listStudent.get(keyPosition).getOld());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ListStudentFragment fragment = new ListStudentFragment();
                fragmentTransaction.replace(R.id.activity_main, fragment, "ListStudent");
                fragmentTransaction.commit();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.editInformation(keyPosition);
            }
        });
        return view;
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

    public void setKeyForInfoStudent(int position) {
        keyPosition = position;
    }

    public interface onWaitInformation {
        // TODO: Update argument type and name
        void getInformation(StudentObject object);

        void editInformation(int keyPosition);
    }
}
