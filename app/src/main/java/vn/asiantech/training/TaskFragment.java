package vn.asiantech.training;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.model.Task;

public class TaskFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_LIST_TASK = "Task";
    private List<Task> mArrTask = new ArrayList<Task>();
    private RecyclerView mRecyclerview;
    private TaskAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView mImgViewPlus;
    private SendFromContact mCallback;

    public TaskFragment() {
        // Required empty public constructor
    }


    public static TaskFragment newInstance(List<Task> arr) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST_TASK, (ArrayList<Task>) arr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrTask = getArguments().getParcelableArrayList(ARG_LIST_TASK);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact2, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerViewContact);
        mImgViewPlus = (ImageView) view.findViewById(R.id.imgView);
        mAdapter = new TaskAdapter(mArrTask);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        mImgViewPlus.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgView:
                mCallback.SendFromContactFrag();
                break;
        }
    }

    public interface SendFromContact {
        public void SendFromContactFrag();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendFromContact) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}


