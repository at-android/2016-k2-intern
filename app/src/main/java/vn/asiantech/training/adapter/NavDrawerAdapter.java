package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.model.NavDrawerItem;

/**
 * Created by phuong on 24/11/2016.
 */

public class NavDrawerAdapter extends RecyclerView.Adapter {
    private final List mItems;
    protected final Context mContext;
    private static final int NAV_IMAGE_HEADER = 0;
    private static final int NAV_ITEM_INFORMATION = 1;

    public NavDrawerAdapter(Context mContext,List mItems) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case NAV_IMAGE_HEADER:
                View navHeaderItem = inflater.inflate(R.layout.header_menu_drawer, parent, false);
                viewHolder = new HeaderHolder(navHeaderItem);
                break;
            case NAV_ITEM_INFORMATION:
                View navInfotem = inflater.inflate(R.layout.item_drawer_row, parent, false);
                viewHolder = new MyViewHolder(navInfotem);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NAV_ITEM_INFORMATION:
                MyViewHolder mMyViewHolder = (MyViewHolder) holder;
                final NavDrawerItem mNavDrawerItem = (NavDrawerItem) mItems.get(position);
                mMyViewHolder.tvTitle.setText(mNavDrawerItem.getmTitle());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mItems.get(position) instanceof NavDrawerItem){
            return NAV_ITEM_INFORMATION;
        }
        if(mItems.get(position) instanceof String){
            return NAV_IMAGE_HEADER;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.title);
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public ImageView imvHeader;

        public HeaderHolder(View view) {
            super(view);
            imvHeader = (ImageView) view.findViewById(R.id.imvHeader);
        }
    }
}
