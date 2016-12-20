package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.asiantech.training.model.Task;

public class AddFragment extends Fragment implements View.OnClickListener{
    private Button mBtnAdd;
    private Button mBtnCancel;
    private EditText mEdTitle;
    private EditText mEdContent;
    private DataFromEdFrag mCallback;
    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        mBtnAdd = (Button)view.findViewById(R.id.btnAdd);
        mBtnCancel = (Button)view.findViewById(R.id.btnCancel);
        mEdTitle = (EditText)view.findViewById(R.id.edTitle);
        mEdContent = (EditText)view.findViewById(R.id.edContent);
        mBtnAdd.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                Task task = new Task(mEdTitle.getText().toString(),mEdContent.getText().toString(),0);
                mCallback.SendDataFromAddFrag(task);
                getActivity().onBackPressed();
                break;
            case R.id.btnCancel:
                getActivity().onBackPressed();
                break;
        }
    }

    public interface DataFromEdFrag {
        public void SendDataFromAddFrag(Task task);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataFromEdFrag) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
