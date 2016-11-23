package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/22/2016.
 */

public class RecycleViewAdapterLike extends RecyclerView.Adapter<RecycleViewAdapterLike.ViewHolder> {
    public OnSetPosition mListener;
    private ArrayList<ContactsObj> mContactsArray = new ArrayList<>();

    public RecycleViewAdapterLike(FragmentActivity activity, ArrayList<ContactsObj> arrayList) {
        this.mContactsArray = arrayList;
    }

    @Override
    public RecycleViewAdapterLike.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle_view, null);
        mListener = (MainActivity) parent.getContext();
        RecycleViewAdapterLike.ViewHolder rcv = new RecycleViewAdapterLike.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterLike.ViewHolder holder, int position) {
        if (mContactsArray.get(position).isLike() == true) {
            holder.mTvName.setText(mContactsArray.get(position).getName().toString());
            holder.mTvPhoneNumber.setText(mContactsArray.get(position).getPhoneNumber().toString());
            holder.mImgLike.setVisibility(View.INVISIBLE);
        } else {
        }
    }

    @Override
    public int getItemCount() {
        return mContactsArray.size();
    }

    public interface OnSetPosition {
        void pullPosition(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;
        public TextView mTvPhoneNumber;
        public ImageView mImgLike;
        public RelativeLayout mRlCustom;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
            mImgLike = (ImageView) itemView.findViewById(R.id.imgLike);
            mRlCustom = (RelativeLayout) itemView.findViewById(R.id.rl_custom);
        }
    }
}