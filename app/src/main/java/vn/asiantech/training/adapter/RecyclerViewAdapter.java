package vn.asiantech.training.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.asiantech.training.R;

/**
 * Created by phuong on 21/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private String[] mResult;


    public RecyclerViewAdapter(String[] results) {
        mResult = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvQuestion.setText(position + 1 + " ");
        holder.tvResult.setText(mResult[position]);
    }

    @Override
    public int getItemCount() {
        return mResult.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuestion;
        public TextView tvResult;

        public MyViewHolder(View view) {
            super(view);
            tvQuestion = (TextView) view.findViewById(R.id.tvQuestion);
            tvResult = (TextView) view.findViewById(R.id.tvResult);
        }
    }
}
