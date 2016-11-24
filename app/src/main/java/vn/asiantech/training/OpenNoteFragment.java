package vn.asiantech.training;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.asiantech.training.model.Note;


public class OpenNoteFragment extends Fragment {
    TextView mTvTitle;
    TextView mTvContent;
    TextView mTvTime;
    public OpenNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_note, container, false);
        mTvTitle = (TextView)view.findViewById(R.id.tvTitle);
        mTvContent = (TextView)view.findViewById(R.id.tvContent);
        mTvTime = (TextView)view.findViewById(R.id.tvTime);
        Bundle bundle = getArguments();
        Note note = (Note)bundle.getParcelable("note");
        mTvTitle.setText(note.getTitle());
        mTvContent.setText(note.getContent());
        mTvTime.setText(note.getTime());
        return view;
    }

}
