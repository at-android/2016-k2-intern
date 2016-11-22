package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    private static final String ARG_LIST_HUMAN = "Human";
    private ArrayList<Human> mArrHuman = new ArrayList<Human>();
    private RecyclerView mRecyclerview;
    private ContactAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ContactFragment() {
        // Required empty public constructor
    }


    public static ContactFragment newInstance(ArrayList<Human> arr) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST_HUMAN,(ArrayList<Human>)arr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrHuman = getArguments().getParcelableArrayList(ARG_LIST_HUMAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        mRecyclerview = (RecyclerView)view.findViewById(R.id.recyclerViewContact);
        mAdapter = new ContactAdapter(mArrHuman);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        return view;
    }

}
