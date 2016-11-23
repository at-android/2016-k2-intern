package vn.asiantech.training.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.Human;
import vn.asiantech.training.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private ArrayList<Human> mArr = new ArrayList<Human>();

    public FavoriteAdapter(ArrayList<Human> list) {
        this.mArr = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder holder1 = holder;
            holder1.tv.setText(mArr.get(position).getName() + "\n" + mArr.get(position).getPhoneNumber());
            holder1.img.setImageResource(R.drawable.ic_star_red_600_24dp);
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvFavorite);
            img = (ImageView) itemView.findViewById(R.id.imgViewFavorite);
        }
    }

}
