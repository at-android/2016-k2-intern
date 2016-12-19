package vn.asiantech.training.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.HomeActivity;
import vn.asiantech.training.objects.Task;

/**
 * Created by MaiManhDuy on 12/18/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Task> tasksArray = new ArrayList<>();

    public RecyclerViewAdapter(List<Task> arrTasks, HomeActivity homeActivity) {
        this.tasksArray = arrTasks;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        RecyclerViewAdapter.ViewHolder rcv = new RecyclerViewAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(tasksArray.get(position).getTitle());
        holder.tvContent.setText(tasksArray.get(position).getContent());
        int isLike = tasksArray.get(position).getFavorite();
        if (isLike == 1) {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_star_like);
        } else {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_star_not_like);
        }
    }

    @Override
    public int getItemCount() {
        return tasksArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvContent;
        public ImageView imageViewFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvCardViewTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvCardViewContent);
            imageViewFavorite = (ImageView) itemView.findViewById(R.id.imgCardViewLike);
        }
    }
}
