package vn.asiantech.training.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.Model.Contact;
import vn.asiantech.training.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Contact> mArr = new ArrayList<Contact>();

    public RecyclerAdapter(ArrayList<Contact> arr) {
        this.mArr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.custom_item_recycler, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTv.setText(mArr.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tvItem);
        }
    }

    /*ham update cho recyclerView*/
    public void updateList(ArrayList<Contact> arr){
        mArr = arr;
        notifyDataSetChanged();
    }

}
