package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 23/11/2016.
 */
public class RecyclerViewNoteAdapter extends RecyclerView.Adapter<RecyclerViewNoteAdapter.ViewHolder> implements View.OnClickListener {
    public OnCallBackListener mListener;
    private ArrayList<Note> mNotes;
    private RecyclerView mRecylerView;

    public RecyclerViewNoteAdapter(RecyclerView recyclerView, ArrayList<Note> notes) {
        mNotes = notes;
        mRecylerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (OnCallBackListener) parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mNotes.get(position).getTitle());
        holder.tvDatetime.setText(mNotes.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, mRecylerView.getChildLayoutPosition(v));
    }

    public interface OnCallBackListener {
        void onClick(View v, int positon);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDatetime;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDatetime = (TextView) itemView.findViewById(R.id.tvDatetime);
        }
    }
}
