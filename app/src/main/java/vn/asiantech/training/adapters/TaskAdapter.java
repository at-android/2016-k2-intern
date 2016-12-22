package vn.asiantech.training.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.listeners.ItemClickListener;
import vn.asiantech.training.models.Task;

/**
 * Created by phuong on 16/12/2016.
 */

public class TaskAdapter extends RecyclerView.Adapter {
    private static final int NAV_IMAGE_LOADING = 0;
    private static final int NAV_ITEM = 1;
    private List<Task> mTasks;
    private Context mContext;
    private ItemClickListener mListener;
    private OnLoadMoreListener mLoadMoreListener;
    private RecyclerView mRecyclerView;
    private boolean isLoading;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;

    public TaskAdapter(List<Task> tasks, Context mContext, ItemClickListener listener, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mTasks = tasks;
        mListener = listener;
        mRecyclerView = recyclerView;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mListener != null) {
                        mLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case NAV_IMAGE_LOADING: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_load_more, parent, false);
                viewHolder = new LoadHolder(view);
                break;
            }
            case NAV_ITEM: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_task, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            }
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Task task = mTasks.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.mTvTitle.setText(task.getMTitle());
            myViewHolder.mTvContent.setText(task.getMContent());
            if (task.getFavorite() == 1) {
                myViewHolder.mChkFav.setChecked(true);
            } else {
                myViewHolder.mChkFav.setChecked(false);
            }
            String[] colorRandom = task.getMRandomColor().split("[;]");
            myViewHolder.mCardView.setCardBackgroundColor(Color.rgb(Integer.parseInt(colorRandom[0]), Integer.parseInt(colorRandom[1]), Integer.parseInt(colorRandom[2])));
        } else if (holder instanceof LoadHolder) {
            LoadHolder loadingViewHolder = (LoadHolder) holder;
            loadingViewHolder.mProgressBar.setIndeterminate(true);
            loadingViewHolder.view.setVisibility(mIsCheck?View.VISIBLE:View.GONE);
        }
    }
    private boolean mIsCheck;
    public void setVisiBleProgressBar(boolean isCheck){
        mIsCheck=isCheck;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mTasks.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position==mTasks.size() ? NAV_IMAGE_LOADING : NAV_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
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

    public class LoadHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        View view;

        public LoadHolder(View itemView) {
            super(itemView);
            view=itemView;
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
