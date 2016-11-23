package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/22/2016.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    public OnSetPosition mListener;
    private ArrayList<ContactsObj> mContactsArray = new ArrayList<>();

    public RecycleViewAdapter(FragmentActivity activity, ArrayList<ContactsObj> arrayList) {
        this.mContactsArray = arrayList;
    }

    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle_view, null);
        mListener = (MainActivity) parent.getContext();
        RecycleViewAdapter.ViewHolder rcv = new RecycleViewAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, final int position) {
        holder.mTvName.setText(mContactsArray.get(position).getName().toString());
        holder.mTvPhoneNumber.setText(mContactsArray.get(position).getPhoneNumber().toString());
        if (mContactsArray.get(position).isLike() == true) {
            holder.mImgLike.setImageResource(R.drawable.ic_stars_black_24dp);
        } else {
            holder.mImgLike.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.pullPosition(position);
            }
        });
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

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
            mImgLike = (ImageView) itemView.findViewById(R.id.imgLike);
        }
    }
}
