package vn.asiantech.training.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.Activities.MainActivity;
import vn.asiantech.training.Object.AlarmObj;
import vn.asiantech.training.R;

/**
 * Created by MaiManhDuy on 12/5/2016.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private ArrayList<AlarmObj> mAlarmArr = new ArrayList<>();
    private Context context;
    private onSetDetete mListener;

    public AlarmAdapter(MainActivity mainActivity, ArrayList<AlarmObj> a) {
        this.context = mainActivity;
        this.mAlarmArr = a;
    }

    @Override
    public AlarmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_custom, null);
        mListener = (MainActivity) parent.getContext();
        AlarmAdapter.ViewHolder rcv = new AlarmAdapter.ViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(AlarmAdapter.ViewHolder holder, final int position) {
        holder.title.setText(mAlarmArr.get(position).getTitle().toString());
        holder.time.setText(mAlarmArr.get(position).getDayofweek() + "   " + mAlarmArr.get(position).getHour() + ":" + mAlarmArr.get(position).getMinute());
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
        public TextView title;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_content);
            time = (TextView) itemView.findViewById(R.id.time_content);
        }
    }
}
