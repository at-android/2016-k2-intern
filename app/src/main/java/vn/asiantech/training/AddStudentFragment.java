package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentFragment extends Fragment {
    private EditText mEtName;
    private EditText mEtSchool;
    private EditText mEtAddress;
    private EditText mEtOld;
    private Button mBtnAddNewStudent;
    private ImageButton mBtnBack;
    private CallbackUpdateView mListener;

    public AddStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        mEtName = (EditText) view.findViewById(R.id.edtAddName);
        mEtSchool = (EditText) view.findViewById(R.id.edtAddSchool);
        mEtAddress = (EditText) view.findViewById(R.id.edtAddAddress);
        mEtOld = (EditText) view.findViewById(R.id.edtAddOld);
        mBtnAddNewStudent = (Button) view.findViewById(R.id.btAddStudent);
        mBtnBack = (ImageButton) view.findViewById(R.id.imgbtaddStudentBack);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBtnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentObject stOjNew = new StudentObject();
                stOjNew.setName(mEtName.getText().toString());
                stOjNew.setSchool(mEtSchool.getText().toString());
                stOjNew.setAddress(mEtAddress.getText().toString());
                stOjNew.setOld(mEtOld.getText().toString());
                if (mListener != null) {
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    mListener.onClickUpdate(stOjNew);
                }
            }
        });
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ListStudentFragment fragment = new ListStudentFragment();
                fragmentTransaction.replace(R.id.activity_main, fragment, "ListStudent");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddStudentFragment.CallbackUpdateView) {
            mListener = (AddStudentFragment.CallbackUpdateView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackUpdateView");
        }
    }

    public interface CallbackUpdateView {
        void onClickUpdate(StudentObject std);
    }
}
