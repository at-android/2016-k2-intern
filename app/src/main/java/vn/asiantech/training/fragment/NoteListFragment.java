package vn.asiantech.training.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.NoteListAdapter;
import vn.asiantech.training.model.Note;

/**
 * Created by phuong on 22/11/2016.
 */

public class NoteListFragment extends Fragment {
    private static final String ARG_LIST_NOTE = "listnote";
    private RecyclerView mRecyclerView;
    private NoteListAdapter mNoteListAdapter;
    private ArrayList<Note> mNotes;

    public static NoteListFragment newInstance(List<Note> noteList) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARG_LIST_NOTE, (ArrayList<Note>) noteList);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNotes = getArguments().getParcelableArrayList(ARG_LIST_NOTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNote);
        mNoteListAdapter = new NoteListAdapter(getActivity().getBaseContext(), mNotes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mNoteListAdapter);
        return view;
    }

}
