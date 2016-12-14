package vn.asiantech.training.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.listeners.RecyclerItemListener;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 07/12/2016.
 */

public class RecyclerViewAlarmDeleteAdapter extends RecyclerView.Adapter<RecyclerViewAlarmDeleteAdapter.MyViewHolder> {
    private Context mContext;
    private List<Alarm> mAlarms;
    private RecyclerItemListener mItemListener;

    public RecyclerViewAlarmDeleteAdapter(List<Alarm> alarms, Context context, RecyclerItemListener listener) {
        mAlarms = alarms;
        mContext = context;
        mItemListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_recycler_delete, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Alarm alarm = mAlarms.get(position);
        holder.mTvTime.setText(alarm.getHour() + " : " + alarm.getMin());
        holder.mTvRepeat.setText(alarm.getRepeartChar());
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTime;
        private TextView mTvRepeat;
        private CheckBox mChkStatus;

        public MyViewHolder(View view) {
            super(view);
            mTvTime = (TextView) view.findViewById(R.id.tvTimeAlarm);
            mTvRepeat = (TextView) view.findViewById(R.id.tvRepeat);
            mChkStatus = (CheckBox) view.findViewById(R.id.tvStatus);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onItemclick(getAdapterPosition());
                }
            });
            mChkStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onItemChecked(getAdapterPosition(), mChkStatus.isChecked());
                }
            });
        }
    }
}
