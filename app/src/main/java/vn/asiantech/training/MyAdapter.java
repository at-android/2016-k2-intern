package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/12/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Time> mArr = new ArrayList<Time>();
    private MainActivity main;

    public MyAdapter(ArrayList<Time> arr, MainActivity main) {
        this.mArr = arr;
        this.main = main;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.custom_item_adapter, parent, false);
        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTv.setText(mArr.get(position).toString());
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = main.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                EditFragment frag = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("time", mArr.get(position));
                bundle.putInt("position",position);
                frag.setArguments(bundle);
                frag.show(main.getSupportFragmentManager(),"abc");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public void addItem(int position, Time t) {
        mArr.add(position, t);
        notifyItemInserted(position);
    }

    public void updateList(ArrayList<Time> data) {
        mArr = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tvItem);
        }
    }
}
