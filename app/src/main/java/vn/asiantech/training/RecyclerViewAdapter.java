package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HoangDuy on 21/11/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Item> mItems;

    public RecyclerViewAdapter(List<Item> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Imgicon.setImageResource(mItems.get(position).getIcon());
        holder.Tvname.setText(mItems.get(position).getNameItem());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Imgicon;
        private TextView Tvname;

        public ViewHolder(View itemView) {
            super(itemView);
            Imgicon = (ImageView) itemView.findViewById(R.id.imgIcon);
            Tvname = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
