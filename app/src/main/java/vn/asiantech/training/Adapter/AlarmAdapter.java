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
import vn.asiantech.training.activities.MainActivity;
import vn.asiantech.training.object.AlarmObj;

/**
 * Created by MaiManhDuy on 12/5/2016.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private List<AlarmObj> mAlarmArr = new ArrayList<>();
    private Context mContext;
    private onSetDetete mListener;

    public AlarmAdapter(MainActivity mainActivity, List<AlarmObj> listAlarm) {
        this.mContext = mainActivity;
        this.mAlarmArr = listAlarm;
    }

    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_custom, null);
        mListener = (MainActivity) parent.getContext();
        AlarmAdapter.ViewHolder viewHolder = new AlarmAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlarmAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(mAlarmArr.get(position).getTitle().toString());
        holder.tvTime.setText(mAlarmArr.get(position).getDayofweek() + "   " + mAlarmArr.get(position).getHour() + ":" + mAlarmArr.get(position).getMinute());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.editPosition(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.deletePosition(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlarmArr.size();
    }

    public interface onSetDetete {
        void deletePosition(int position);

        void editPosition(int positon);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTimeContent);
        }
    }
}
