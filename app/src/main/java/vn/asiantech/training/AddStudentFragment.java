package vn.asiantech.training;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentFragment extends Fragment {
    EditText edtName;
    EditText edtSchool;
    EditText edtAddress;
    EditText edtOld;
    Button btAddStudent;
    private CallbackUpdateView mListener;

    public AddStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        edtName = (EditText) view.findViewById(R.id.edtAddName);
        edtSchool = (EditText) view.findViewById(R.id.edtAddSchool);
        edtAddress = (EditText) view.findViewById(R.id.edtAddAddress);
        edtOld = (EditText) view.findViewById(R.id.edtAddOld);
        btAddStudent = (Button) view.findViewById(R.id.btAddStudent);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickUpdate(edtName.getText().toString(), edtSchool.getText().toString(), edtAddress.getText().toString(), edtOld.getText().toString());
            }
        });
    }

    public void daidieninterface(CallbackUpdateView getmListener) {
        mListener = getmListener;
    }

    interface CallbackUpdateView {
        void onClickUpdate(String name, String school, String address, String old);
    }
}
