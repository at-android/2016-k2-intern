package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Note;

/**
 * Created by phuong on 22/11/2016.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {
    private Context mContext;
    private List<Note> mNotes;
    private callBackNoteLister mCallBackNoteLister;

    public NoteListAdapter(Context context, List<Note> notes) {
        mContext = context;
        mNotes = notes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_note, parent, false);
        mCallBackNoteLister = (callBackNoteLister) parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Note note = mNotes.get(position);
        holder.tvTitle.setText(note.getmTitle());
        holder.tvTime.setText(note.getmTimeCreated().toString());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallBackNoteLister != null) {
                    mCallBackNoteLister.listenerEditNote(note);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public interface callBackNoteLister {
        void listenerEditNote(Note note);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvTime;
        public ImageView imvEdit;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            imvEdit = (ImageView) view.findViewById(R.id.imvEdit);
        }
    }

}
