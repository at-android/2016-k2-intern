package vn.asiantech.training.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Task;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDialogFragment extends DialogFragment implements View.OnClickListener{
    private Button mBtnEdit;
    private Button mBtnCancel;
    private TextView mTvTitle;
    private TextView mTvContent;
    private Task mTask;
    private int mPosition;
    private DataFromEdDialogFrag mCallback;
    public EditDialogFragment() {
        // Required empty public constructor
    }

    public static EditDialogFragment newInstance() {
        EditDialogFragment fragment = new EditDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mPosition = bundle.getInt("position");
            mTask = bundle.getParcelable("task");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        mBtnEdit = (Button)view.findViewById(R.id.btnEdit);
        mBtnCancel = (Button)view.findViewById(R.id.btnCancel);
        mTvTitle = (TextView)view.findViewById(R.id.tvTitle);
        mTvContent = (TextView)view.findViewById(R.id.tvContent);
        mTvTitle.setText(mTask.getTitle());
        mTvContent.setText(mTask.getContent());
        mBtnEdit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:
                mCallback.onArticleSelected(mTask,mPosition);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    public interface DataFromEdDialogFrag {
        public void onArticleSelected(Task task,int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataFromEdDialogFrag) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}


