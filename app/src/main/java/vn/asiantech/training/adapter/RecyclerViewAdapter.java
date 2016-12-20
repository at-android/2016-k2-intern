package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.interfaces.OnLoadMoreListener;
import vn.asiantech.training.objects.Task;

/**
 * Created by MaiManhDuy on 12/18/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    int pos;
    private List<Task> tasksArray = new ArrayList<>();
    private OnShowDialogEdit mListener;
    private Context context;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public RecyclerViewAdapter(List<Task> arrTasks, HomeActivity homeActivity, RecyclerView recyclerView) {
        this.tasksArray = arrTasks;
        this.context = homeActivity;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return tasksArray.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (HomeActivity) parent.getContext();
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
            return new TaskViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item_recyclerview, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        pos = position;
        if (holder instanceof TaskViewHolder) {
            TaskViewHolder userViewHolder = (TaskViewHolder) holder;
            userViewHolder.tvTitle.setText(tasksArray.get(position).getTitle());
            userViewHolder.tvContent.setText(tasksArray.get(position).getContent());
            int isLike = tasksArray.get(position).getFavorite();
            if (isLike == 1) {
                userViewHolder.imageViewFavorite.setImageResource(R.drawable.ic_star);
            } else {
                userViewHolder.imageViewFavorite.setImageResource(R.drawable.ic_star_border);
            }
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener.onShowDialog(pos);
                    return false;
                }
            });
            Random r = new Random();
            int textRd = r.nextInt(4);
            switch (textRd) {
                case 0:
                    userViewHolder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText1));
                    userViewHolder.tvContent.setTextColor(ContextCompat.getColor(context, R.color.colorText1));
                    break;
                case 1:
                    userViewHolder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText2));
                    userViewHolder.tvContent.setTextColor(ContextCompat.getColor(context, R.color.colorText2));
                    break;
                case 2:
                    userViewHolder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText3));
                    userViewHolder.tvContent.setTextColor(ContextCompat.getColor(context, R.color.colorText3));
                    break;
                case 3:
                    userViewHolder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.colorText4));
                    userViewHolder.tvContent.setTextColor(ContextCompat.getColor(context, R.color.colorText4));
                    break;
            }
            int backgroundRd = r.nextInt(4);
            switch (backgroundRd) {
                case 0:
                    userViewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBackGround1));
                    break;
                case 1:
                    userViewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBackGround2));
                    break;
                case 2:
                    userViewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBackGround3));
                    break;
                case 3:
                    userViewHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBackGround4));
                    break;
            }
            userViewHolder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChangeFavorite(position);
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        return tasksArray.size();
    }

    /*
        @Override
        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.tvTitle.setText(tasksArray.get(position).getTitle());
            holder.tvContent.setText(tasksArray.get(position).getContent());
            int isLike = tasksArray.get(position).getFavorite();
            if (isLike == 1) {
                holder.imageViewFavorite.setImageResource(R.drawable.ic_star);
            } else {
                holder.imageViewFavorite.setImageResource(R.drawable.ic_star_border);
            }
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener.onShowDialog(position);
                    return false;
                }
            });
            Random r = new Random();
            int textRd = r.nextInt(4);
            switch (textRd){
                case 0:holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.colorText1));
                    holder.tvContent.setTextColor(ContextCompat.getColor(context,R.color.colorText1));
                    break;
                case 1:holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.colorText2));
                    holder.tvContent.setTextColor(ContextCompat.getColor(context,R.color.colorText2));
                    break;
                case 2:holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.colorText3));
                    holder.tvContent.setTextColor(ContextCompat.getColor(context,R.color.colorText3));
                    break;
                case 3:holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.colorText4));
                    holder.tvContent.setTextColor(ContextCompat.getColor(context,R.color.colorText4));
                    break;
            }
            int backgroundRd = r.nextInt(4);
            switch (backgroundRd){
                case 0:holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBackGround1));break;
                case 1:holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBackGround2));break;
                case 2:holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBackGround3));break;
                case 3:holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBackGround4));break;
            }
            holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                mListener.onChangeFavorite(position);
                }
            });
        }*/
    public interface OnShowDialogEdit {
        void onShowDialog(int pos);

        void onChangeFavorite(int pos);
    }

   /* public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;
        public ImageView imageViewFavorite;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvCardViewTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvCardViewContent);
            imageViewFavorite = (ImageView) itemView.findViewById(R.id.imgCardViewLike);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }*/
}
