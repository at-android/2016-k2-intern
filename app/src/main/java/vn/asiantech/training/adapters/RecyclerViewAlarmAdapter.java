package vn.asiantech.training.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.listeners.RecyclerItemListener;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 01/12/2016.
 */

public class RecyclerViewAlarmAdapter extends RecyclerView.Adapter<RecyclerViewAlarmAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Alarm> mAlarms;
    private RecyclerItemListener mItemListener;

    public RecyclerViewAlarmAdapter(ArrayList<Alarm> alarms, Context context, RecyclerItemListener listener) {
        mAlarms = alarms;
        mContext = context;
        mItemListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alarm_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Alarm alarm = mAlarms.get(position);
        holder.mTvTime.setText(alarm.getmHour() + " : " + alarm.getmMin());
        holder.mTvRepeat.setText(alarm.getmRepeartChar());
        holder.mTvStatus.setSelected(alarm.ismStatus());
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTime;
        TextView mTvRepeat;
        ImageView mTvStatus;

        public MyViewHolder(View view) {
            super(view);
            mTvTime = (TextView) view.findViewById(R.id.tvTimeAlarm);
            mTvRepeat = (TextView) view.findViewById(R.id.tvRepeat);
            mTvStatus = (ImageView) view.findViewById(R.id.tvStatus);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onItemclick(getAdapterPosition());
                }
            });
            mTvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onItemChecked(getAdapterPosition(), true);
                    mAlarms.get(getAdapterPosition()).setmStatus(!mAlarms.get(getAdapterPosition()).ismStatus());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
