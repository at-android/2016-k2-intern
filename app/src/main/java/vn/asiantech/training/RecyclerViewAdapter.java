package vn.asiantech.training;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HoangDuy on 18/11/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder> {
    private String[] mDataset;

    public RecyclerViewAdapter(String[] dataset) {
        mDataset = dataset;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_items, parent, false);
        Viewholder viewholder = new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.mTvquestion.setText(position + 1 + "");
        holder.mTvresult.setText(mDataset[position]);
        if (holder.mTvresult.getText().equals(QuizActivity.TRUE_ANSWER)) {
            holder.mTvresult.setTextColor(Color.GREEN);
        } else {
            holder.mTvresult.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        public TextView mTvquestion;
        public TextView mTvresult;

        public Viewholder(View itemView) {
            super(itemView);
            mTvquestion = (TextView) itemView.findViewById(R.id.tvQuestions);
            mTvresult = (TextView) itemView.findViewById(R.id.tvResults);
        }
    }
}
