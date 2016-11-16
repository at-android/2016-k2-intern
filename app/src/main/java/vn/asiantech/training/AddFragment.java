package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        final EditText edtSchool = (EditText) view.findViewById(R.id.edtSchool);
        final EditText edtAddress = (EditText) view.findViewById(R.id.edtAddress);
        final EditText edtName = (EditText) view.findViewById(R.id.edtName);
        final EditText edtAge = (EditText) view.findViewById(R.id.edtAge);
        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
        ImageButton imgbBack = (ImageButton) view.findViewById(R.id.imgbBack);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(edtSchool.getText().toString(), edtName.getText().toString(),
                        edtAddress.getText().toString(),
                        edtAge.getText().toString());
                onButtonPressed(student);
            }
        });

        imgbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Student student) {
        if (mListener != null) {
            mListener.onFragmentInteraction(student);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Student student);
    }
}
