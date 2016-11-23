package vn.asiantech.training.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.NoteAdapter;
import vn.asiantech.training.model.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {
    private static final String ARG_LIST_NOTE = "Note";
    private ArrayList<Note> mArrNote = new ArrayList<Note>();
    private RecyclerView mRecyclerview;
    private NoteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public NoteFragment() {
        // Required empty public constructor
    }

    public static NoteFragment newInstance(ArrayList<Note> arr) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST_NOTE, (ArrayList<Note>) arr);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArrNote = getArguments().getParcelableArrayList(ARG_LIST_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note,container,false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerViewNote);
        mAdapter = new NoteAdapter(mArrNote);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        return view;
    }

}
