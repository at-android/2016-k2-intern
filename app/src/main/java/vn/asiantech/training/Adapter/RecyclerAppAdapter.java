package vn.asiantech.training.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.Model.AppInfo;
import vn.asiantech.training.R;

/**
 * Created by Administrator on 30/11/2016.
 */

public class RecyclerAppAdapter extends RecyclerView.Adapter<RecyclerAppAdapter.ViewHolder>{
        ArrayList<AppInfo> mArr = new ArrayList<AppInfo>();
    public RecyclerAppAdapter(ArrayList<AppInfo> arr) {
        this.mArr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.custom_item_app, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTv.setText(mArr.get(position).getName());
        holder.mImg.setImageDrawable(mArr.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
        private ImageView mImg;
        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tvItem);
            mImg = (ImageView) itemView.findViewById(R.id.imgItem);
        }
    }
}
