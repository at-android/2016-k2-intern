package vn.asiantech.training.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.model.Task;

/**
 * Created by HoangDuy on 16/12/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;
    public onAdapterInteraction mListener;
    OnLoadMoreListener loadMoreListener;
    private List<Task> mTasks;
    private List<Integer> mColors;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;

    public TaskAdapter(List<Task> tasks, List<Integer> colors, RecyclerView mRecyclerView) {
        mTasks = tasks;
        mColors = colors;
        Log.i("task size", mTasks.size() + "");
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return mTasks.get(position) == null ? TYPE_LOAD : TYPE_MOVIE;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity) parent.getContext();
        Log.i("viewtype", viewType + "");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_MOVIE) {
            return new ViewHolder(inflater.inflate(R.layout.row_task, parent, false));
        } else {
            Log.i("info", "loading");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder taskHolder = (ViewHolder) holder;
            taskHolder.mTitle.setText(mTasks.get(position).getTitle());
            taskHolder.mContent.setText(mTasks.get(position).getContent());
            taskHolder.mCardView.setCardBackgroundColor(mColors.get(position));
            setFav(position, taskHolder);
            taskHolder.mImgFavorite.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int fav = mTasks.get(position).getFavorite() == 0 ? 1 : 0;
                    mTasks.get(position).setFavorite(fav);
                    mListener.onFavoriteListener(mTasks.get(position));
                    setFav(position, taskHolder);
                    Log.i("info", position + "");
                    return false;
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setFav(int position, ViewHolder holder) {
        if (mTasks.get(position).getFavorite() == 0) {
            holder.mImgFavorite.setImageResource(R.drawable.ic_favorite_border_green_500_24dp);
        } else {
            holder.mImgFavorite.setImageResource(R.drawable.ic_favorite_green_500_24dp);
        }
    }


    @Override
    public int getItemCount() {
        Log.i("count", mTasks.size() + "");
        return mTasks == null ? 0 : mTasks.size();
    }

    public interface onAdapterInteraction {
        void onFavoriteListener(Task task);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}

class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitle;
    public TextView mContent;
    public ImageView mImgFavorite;
    public CardView mCardView;

    public ViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        mContent = (TextView) itemView.findViewById(R.id.tvContent);
        mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
    }
}
