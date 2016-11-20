package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditInfoFragment.OnEditInfoListener} interface
 * to handle interaction events.
 * Use the {@link EditInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String[] mTypes = {"Foods", "Drinks", "Diffrent"};
    private Button mBtnUpdate;
    private EditText mEtName;
    private EditText mEtCost;
    private Spinner mSpnType;
    private int mPOSITION;
    private int mType;
    private ImageView mImgBack;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnEditInfoListener mListener;

    public EditInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditInfoFragment newInstance(String param1, String param2) {
        EditInfoFragment fragment = new EditInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_info, container, false);
        mSpnType = (Spinner) view.findViewById(R.id.spnType);
        mBtnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        mEtName = (EditText) view.findViewById(R.id.edtName);
        mEtCost = (EditText) view.findViewById(R.id.edtCost);
        mImgBack = (ImageView) view.findViewById(R.id.imgBtnBackMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnType.setAdapter(adapter);
        mSpnType.setOnItemSelectedListener(this);
        loadDataformMain();
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDataformMain();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FoodMenuFragment menuFragment = new FoodMenuFragment();
                transaction.replace(R.id.activity_main, menuFragment, "Menu").commit();
            }
        });
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FoodMenuFragment menuFragment = new FoodMenuFragment();
                transaction.replace(R.id.activity_main, menuFragment, "Menu").commit();
            }
        });
        return view;
    }

    public void loadDataformMain() {
        MainActivity main = (MainActivity) getActivity();
        mEtName.setText(main.sFoodArray.get(mPOSITION).getName().toString());
        mEtCost.setText(main.sFoodArray.get(mPOSITION).getCost() + "");
        int type = main.sFoodArray.get(mPOSITION).getType();
        switch (type) {
            case 1:
                mSpnType.setSelection(0);
                break;
            case 2:
                mSpnType.setSelection(1);
                break;
            case 3:
                mSpnType.setSelection(2);
        }
    }

    public void upDataformMain() {
        MainActivity main = (MainActivity) getActivity();
        int cost = Integer.parseInt(mEtCost.getText() + "");
        main.sFoodArray.set(mPOSITION, new FoodObject(mEtName.getText() + "", mType, cost));
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                mType = 1;
                break;
            case 1:
                mType = 2;
                break;
            case 2:
                mType = 3;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditInfoListener) {
            mListener = (OnEditInfoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditInfoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getPosition(int position) {
        mPOSITION = position;
    }

    public interface OnEditInfoListener {
        // TODO: Update argument type and name
        void onUpdateEdit(int position);
    }
}
