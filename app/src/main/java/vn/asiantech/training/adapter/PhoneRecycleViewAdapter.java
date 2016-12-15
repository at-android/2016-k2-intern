package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.model.Phone;

/**
 * Created by phuong on 29/11/2016.
 */

public class PhoneRecycleViewAdapter extends RecyclerView.Adapter<PhoneRecycleViewAdapter.MyViewHolder> {
    private List<Phone> mPhones = new ArrayList<>();
    private Context mContext;

    public PhoneRecycleViewAdapter(List<Phone> phones, Context context) {
        mPhones = phones;
        mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_tab_1, parent, false);

        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Phone phone = mPhones.get(position);
        holder.mTvName.setText(phone.getName());
        holder.mTvPhone.setText(phone.getPhone());
    }


    @Override
    public int getItemCount() {
        return mPhones.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;
        public TextView mTvPhone;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        }
    }

}
