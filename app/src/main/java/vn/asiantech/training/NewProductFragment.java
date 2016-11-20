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
 * {@link NewProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProductFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String[] mTypes = {"Foods", "Drinks", "Diffrent"};
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button mBtnAddNew;
    private EditText mEtName;
    private EditText mEtCost;
    private Spinner mSpnType;
    private int mType;
    private ImageView mImgBack;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProductFragment newInstance(String param1, String param2) {
        NewProductFragment fragment = new NewProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_new_product, container, false);
        mSpnType = (Spinner) view.findViewById(R.id.spnNewType);
        mBtnAddNew = (Button) view.findViewById(R.id.btnAdd);
        mEtName = (EditText) view.findViewById(R.id.edtNewName);
        mEtCost = (EditText) view.findViewById(R.id.edtNewCost);
        mImgBack = (ImageView) view.findViewById(R.id.imgBackMenuFormAdd);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnType.setAdapter(adapter);
        mSpnType.setOnItemSelectedListener(this);
        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewProduct();
            }
        });
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FoodMenuFragment foodMenuFragment = new FoodMenuFragment();
                transaction.replace(R.id.activity_main, foodMenuFragment, "Menu").commit();
            }
        });
        return view;
    }

    public void onAddNewProduct() {
        MainActivity main = (MainActivity) getActivity();
        main.sFoodArray.add(new FoodObject(mEtName.getText().toString(), mType, Integer.parseInt(mEtCost.getText().toString())));
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FoodMenuFragment foodMenuFragment = new FoodMenuFragment();
        transaction.replace(R.id.activity_main, foodMenuFragment, "Menu").commit();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
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
        mType = 1;
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
    }
}
