package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/18/2016.
 */

public class FoodArrayAdapter extends RecyclerView.Adapter<FoodArrayAdapter.ViewHolder> {
    private ArrayList<FoodObject> sFoodArray = new ArrayList<>();

    public FoodArrayAdapter(FragmentActivity fragmentActivity, ArrayList<FoodObject> arrayList) {
        this.sFoodArray = arrayList;

    }

    @Override
    public FoodArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycle_view, null);
        FoodArrayAdapter.ViewHolder rcv = new FoodArrayAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(FoodArrayAdapter.ViewHolder holder, int position) {
        holder.mTvName.setText(sFoodArray.get(position).getName().toString());
        holder.mTvCost.setText(sFoodArray.get(position).getCost() + " VND");
        int type = sFoodArray.get(position).getType();
        switch (type) {
            case 1:
                holder.mImgType.setImageResource(R.drawable.type_1);
                holder.mTvType.setText("Foods");
                Log.d("so1", type + "");
                break;
            case 2:
                holder.mImgType.setImageResource(R.drawable.type_2);
                holder.mTvType.setText("Drinks");
                break;
            case 3:
                holder.mImgType.setImageResource(R.drawable.type_3);
                holder.mTvType.setText("Diffrent");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return sFoodArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;
        public TextView mTvType;
        public TextView mTvCost;
        public ImageView mImgType;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvNameInfo);
            mTvType = (TextView) itemView.findViewById(R.id.tvCostInfo);
            mTvCost = (TextView) itemView.findViewById(R.id.tvTypeInfo);
            mImgType = (ImageView) itemView.findViewById(R.id.imgTypeInfo);
        }
    }

}
