package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 18/11/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] mList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvQuestion;
        private TextView mTvResult;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvQuestion = (TextView) itemView.findViewById(R.id.tv1);
            mTvResult = (TextView) itemView.findViewById(R.id.tv2);
        }
    }

    public RecyclerViewAdapter(String[] mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.custom_item_recyclerview, viewGroup, false);
        return new RecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mTvQuestion.setText((position + 1) + "");
        holder.mTvResult.setText(mList[position]);
    }

    @Override
    public int getItemCount() {
        return mList.length;
    }
}
