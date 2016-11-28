package vn.asiantech.training;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button mBtnRegister;
    private EditText mEdName;
    private EditText mEdPass;
    private EditText mEdCofirmPass;
    private EditText mEdEmail;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static boolean validateMail(String emailStr) {
        Matcher matcher = EMAIL_PATTERN.matcher(emailStr);
        return matcher.find();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register_rg);
        mEdName = (EditText) view.findViewById(R.id.tv_username_rg);
        mEdPass = (EditText) view.findViewById(R.id.tv_password_rg);
        mEdCofirmPass = (EditText) view.findViewById(R.id.tv_confim_password_rg);
        mEdEmail = (EditText) view.findViewById(R.id.tv_email_rg);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidate();
            }
        });
        return view;
    }

    public void checkValidate() {
        if (mEdEmail.getText().toString().equals("") || mEdPass.getText().toString().equals("") || mEdName.getText().toString().equals("") || mEdCofirmPass.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Please Insert Full Infomation", Toast.LENGTH_SHORT).show();
        } else {
            if (mEdCofirmPass.getText().toString().equals(mEdPass.getText().toString())) {
                boolean check = validateMail(mEdEmail.getText().toString());
                if (check) {
                    SharedPreferences pre = getActivity().getSharedPreferences("Info", MODE_PRIVATE);
                    SharedPreferences.Editor edit = pre.edit();
                    edit.putString("username", mEdName.getText().toString());
                    edit.putString("password", mEdPass.getText().toString());
                    edit.apply();
                    Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Email format is false", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Comfirm password is false", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
