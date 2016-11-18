package vn.asiantech.training.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.fragment.EditFoodFragment;
import vn.asiantech.training.model.Food;

/**
 * Created by phuong on 17/11/2016.
 */

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.MyViewHolder> {
    private static String POSITION = "position";
    private static String FOOD = "food";
    private ArrayList<Food> mFoods = new ArrayList<>();
    private Context mContext;
    private EditFoodFragment mEditFoodFragment;


    public FoodRecyclerAdapter(ArrayList<Food> mFood, Context context) {
        mFoods = mFood;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Food food = mFoods.get(position);
        holder.tvName.setText(food.getmName());
        holder.tvType.setText(food.getmType());
        holder.tvCost.setText(food.getmCost());
        Bitmap bitmap;
        if (food.getmPicture() == 1) {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.type1);
        } else {
            if (food.getmPicture() == 2) {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.type2);
            } else {
                bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.type3);
            }
        }
        holder.imvPicture.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvType;
        public TextView tvCost;
        public ImageView imvPicture;
        public ImageView imvNext;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvCost = (TextView) view.findViewById(R.id.tvCost);
            imvPicture = (ImageView) view.findViewById(R.id.imvPicture);
            imvNext = (ImageView) view.findViewById(R.id.imvNext);
        }
    }
}
