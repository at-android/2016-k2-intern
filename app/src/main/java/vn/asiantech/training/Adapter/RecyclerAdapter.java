package vn.asiantech.training.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import vn.asiantech.training.Model.RealmPeople;
import vn.asiantech.training.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<RealmPeople> mList = new ArrayList<RealmPeople>();
    private RealmResults<RealmPeople> mRealmList;

    public RecyclerAdapter(List<RealmPeople> arr) {
        this.mList = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.custom_item_recycler, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTv.setText(mList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tvItem);
        }
    }

}
