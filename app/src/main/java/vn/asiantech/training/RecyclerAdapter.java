package vn.asiantech.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<FoodList> mArr = new ArrayList<FoodList>();
    private Context mContext;
    private MainActivity main;

    public RecyclerAdapter(ArrayList<FoodList> list, Context mContext, MainActivity main) {
        this.mArr = list;
        this.mContext = mContext;
        this.main = main;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgViewFood;
        private TextView mTv;
        private ImageView mImgViewArrow;

        public ViewHolder(View itemView) {

            super(itemView);
            mImgViewFood = (ImageView) itemView.findViewById(R.id.imgViewFood);
            mTv = (TextView) itemView.findViewById(R.id.tv);
            mImgViewArrow = (ImageView) itemView.findViewById(R.id.imgViewArrow);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.custom_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FoodList f = mArr.get(position);
        String txt = f.getName() + "\n" + f.getType() + "\n" + f.getPrice();
        holder.mTv.setText(txt);
        holder.mImgViewFood.setImageResource(mArr.get(position).getImage());
        holder.mImgViewArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
        holder.mImgViewArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = main.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                EditInfoFragment frag = new EditInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Food", f);
                bundle.putInt("position", position);
                frag.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.activity_main, frag).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

}
