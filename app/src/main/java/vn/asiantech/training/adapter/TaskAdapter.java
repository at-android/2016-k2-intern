package vn.asiantech.training.adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.asiantech.training.R;
import vn.asiantech.training.activity.MainActivity;
import vn.asiantech.training.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    private List<Task> mArr = new ArrayList<Task>();
    public TaskAdapter(List<Task> list) {
        this.mArr = list;
    }
    private DataFromAdapter mCallback;
    private int currentStrokeColor;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.task_item, parent, false);
        mCallback = (MainActivity) parent.getContext();
        return new ViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder holder1 = holder;
        Random rnd = new Random();
        currentStrokeColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder1.linearLayout.setBackgroundColor(currentStrokeColor);
        if (mArr.get(position).getInterest()==1) {
            holder1.tv.setText("Title:\n"+mArr.get(position).getTitle() + "\n" +"Content:\n"+ mArr.get(position).getContent());
            holder1.img.setImageResource(R.drawable.ic_star_red_600_24dp);
        }
        else{
            holder1.tv.setText("Title:\n"+mArr.get(position).getTitle() + "\n" +"Content:\n"+ mArr.get(position).getContent());
            holder1.img.setImageResource(R.drawable.ic_star_red_100_24dp);
        }
        holder1.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mArr.get(position).getInterest()==0){
                    holder1.img.setImageResource(R.drawable.ic_star_red_600_24dp);
                    mArr.get(position).setInterest(1);
                }
                else{
                    holder1.img.setImageResource(R.drawable.ic_star_red_100_24dp);
                    mArr.get(position).setInterest(0);
                }
            }
        });
        holder1.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.SendData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView img;
        private LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tvContact);
            img = (ImageView)itemView.findViewById(R.id.imgViewContact);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.itemLinearLayout);
        }
    }

    public interface DataFromAdapter{
        public void SendData(int position);
    }

}


