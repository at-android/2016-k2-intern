package vn.asiantech.training.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.asiantech.training.R;

/**
 * Created by MaiManhDuy on 12/20/2016.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;
    public TextView tvContent;
    public ImageView imageViewFavorite;
    public CardView cardView;

    public TaskViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvCardViewTitle);
        tvContent = (TextView) itemView.findViewById(R.id.tvCardViewContent);
        imageViewFavorite = (ImageView) itemView.findViewById(R.id.imgCardViewLike);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
    }
}
