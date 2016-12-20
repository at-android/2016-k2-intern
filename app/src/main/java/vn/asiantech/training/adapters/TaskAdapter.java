package vn.asiantech.training.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import vn.asiantech.training.R;
import vn.asiantech.training.activities.LoginActivity_;
import vn.asiantech.training.listeners.ItemClickListener;
import vn.asiantech.training.models.Task;
import vn.asiantech.training.models.TaskResult;
import vn.asiantech.training.networks.Api;
import vn.asiantech.training.networks.ApiClient;

/**
 * Created by phuong on 16/12/2016.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<Task> mTasks;
    private Context mContext;
    private ItemClickListener mListener;
    private OnLoadMoreListener mLoadMoreListener;

    public TaskAdapter(List<Task> tasks, Context mContext, ItemClickListener listener) {
        this.mContext = mContext;
        this.mTasks = tasks;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_task, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(position>=getItemCount()-1 && mLoadMoreListener!=null){
            mLoadMoreListener.onLoadMore();
        }
        Task task = mTasks.get(position);
        holder.mTvTitle.setText(task.getMTitle());
        holder.mTvContent.setText(task.getMContent());
        if(task.getFavorite()==1){
            holder.mChkFav.setChecked(true);
        }
        else{
            holder.mChkFav.setChecked(false);
        }
        String[] colorRandom = task.getMRandomColor().split("[;]");
        holder.mCardView.setCardBackgroundColor(Color.rgb(Integer.parseInt(colorRandom[0]), Integer.parseInt(colorRandom[1]), Integer.parseInt(colorRandom[2])));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvTitle;
        public TextView mTvContent;
        public CheckBox mChkFav;
        public CardView mCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mTvContent = (TextView) itemView.findViewById(R.id.tvContent);
            mChkFav = (CheckBox) itemView.findViewById(R.id.chkFav);
            mCardView = (CardView) itemView.findViewById(R.id.cardView);

            mChkFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.favClick(getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.itemClick(getAdapterPosition());
                }
            });

        }
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }
}
