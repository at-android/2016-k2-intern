package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/23/2016.
 */

public class NoteRecyckeAdapter extends RecyclerView.Adapter<NoteRecyckeAdapter.ViewHolder> {
    public OnSetPosition mListener;
    private ArrayList<NotesObj> mNoteArray = new ArrayList<>();

    public NoteRecyckeAdapter(FragmentActivity activity, ArrayList<NotesObj> sNotesArray) {
        this.mNoteArray = sNotesArray;
    }

    @Override
    public NoteRecyckeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_note, null);
        mListener = (MainActivity) parent.getContext();
        NoteRecyckeAdapter.ViewHolder rcv = new NoteRecyckeAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(NoteRecyckeAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(mNoteArray.get(position).getTitleNote().toString());
        holder.tvTimes.setText(mNoteArray.get(position).getTimesNote().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.pullPositionOnListNote(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteArray.size();
    }

    public interface OnSetPosition {
        void pullPositionOnListNote(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvTimes;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle_note);
            tvTimes = (TextView) itemView.findViewById(R.id.tvTime_note);
        }
    }
}
