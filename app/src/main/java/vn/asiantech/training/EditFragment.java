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


public class EditFragment extends Fragment implements View.OnClickListener{
    private EditText mEdTitle;
    private EditText mEdContent;
    private Button mBtnEdit;
    private Button mBtnCancel;
    private DataFromEdFrag mCallback;
    private Task mTask;
    private int mPosition;
    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(Task task,int position) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putParcelable("task",task);
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTask = getArguments().getParcelable("task");
            mPosition = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        mBtnEdit = (Button)view.findViewById(R.id.btnEdit);
        mBtnCancel = (Button)view.findViewById(R.id.btnCancel);
        mEdTitle = (EditText)view.findViewById(R.id.edTitle);
        mEdContent = (EditText)view.findViewById(R.id.edContent);

        mEdTitle.setText(mTask.getTitle());
        mEdContent.setText(mTask.getContent());
        mBtnEdit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:
                mTask.setTitle(mEdTitle.getText().toString());
                mTask.setContent(mEdContent.getText().toString());
                mCallback.SendData2(mTask,mPosition);
                getActivity().onBackPressed();
                break;
            case R.id.btnCancel:
                getActivity().onBackPressed();
                break;
        }
    }

    public interface DataFromEdFrag {
        public void SendData2(Task task,int position);
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
