package vn.asiantech.training.adapter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.OpenNoteFragment;
import vn.asiantech.training.R;
import vn.asiantech.training.model.Note;

/**
 * Created by Administrator on 23/11/2016.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<Note> mArr = new ArrayList<Note>();
    private Context mContext;
    private MainActivity main;

    public NoteAdapter(ArrayList<Note> list, Context mContext, MainActivity main) {
        this.mArr = list;
        this.mContext = mContext;
        this.main = main;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Note note = mArr.get(position);
        holder.tv.setText(note.toString());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = main.getSupportFragmentManager();
                OpenNoteFragment frag = new OpenNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("note", note);
                frag.setArguments(bundle);

                fm.beginTransaction().addToBackStack(null).replace(R.id.container_body, frag).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvNote);
        }
    }

}
