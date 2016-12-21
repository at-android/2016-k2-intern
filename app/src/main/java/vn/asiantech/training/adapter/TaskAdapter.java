package vn.asiantech.training.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.model.Task;

/**
 * Created by HoangDuy on 16/12/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    public onAdapterInteraction mListener;
    private List<Task> mTasks;
    private List<Integer> mColors;

    public TaskAdapter(List<Task> tasks, List<Integer> colors) {
        mTasks = tasks;
        mColors = colors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mListener = (MainActivity) parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position >= getItemCount() - 1) {

            Log.i("ad", position + "");
        }
        holder.mTitle.setText(mTasks.get(position).getTitle());
        holder.mContent.setText(mTasks.get(position).getContent());
        holder.mCardView.setCardBackgroundColor(mColors.get(position));
        setFav(position, holder);
        holder.mImgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fav = mTasks.get(position).getFavorite() == 0 ? 1 : 0;
                mTasks.get(position).setFavorite(fav);
                mListener.onFavoriteListener(mTasks.get(position));
                setFav(position, holder);
                Log.i("info", position + "");
            }
        });
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
        return mTasks.size();
    }

    public interface onAdapterInteraction {
        void onFavoriteListener(Task task);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mContent;
        private ImageView mImgFavorite;
        private CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mContent = (TextView) itemView.findViewById(R.id.tvContent);
            mImgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
