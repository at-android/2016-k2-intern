package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Student;

/**
 * Created by phuong on 14/11/2016.
 */

public class FragmentAddStudent extends Fragment implements View.OnClickListener {
    public static final String NAME_FRAGMENT = "FragmentAddStudent";
    private EditText mEdName;
    private EditText mEdAge;
    private EditText mEdSchool;
    private EditText mEdAddress;
    private ImageView mImvBack;
    private Button mBtnAdd;
    private ListenerCallbackData mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_student, null);

        mEdName = (EditText) view.findViewById(R.id.edName);
        mEdAge = (EditText) view.findViewById(R.id.edAge);
        mEdAddress = (EditText) view.findViewById(R.id.edAddress);
        mEdSchool = (EditText) view.findViewById(R.id.edSchool);
        mBtnAdd = (Button) view.findViewById(R.id.btnAdd);
        mImvBack = (ImageView) view.findViewById(R.id.imBack);

        mBtnAdd.setOnClickListener(this);
        mImvBack.setOnClickListener(this);

        return view;
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        if (context instanceof Activity){
            a =(Activity) context;
            mListener = (ListenerCallbackData) a;
        }
    }*/

    public void setListennerSendData(ListenerCallbackData listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd: {
                Student student = new Student(mEdName.getText().toString(), mEdAge.getText().toString(),
                        mEdAddress.getText().toString(), mEdSchool.getText().toString());
                mListener.sendData(student);
                getActivity().onBackPressed();
                break;
            }
            case R.id.imBack:
                getActivity().onBackPressed();
                break;
        }
    }

    public interface ListenerCallbackData {
        void sendData(Student student);
    }

}
