package vn.asiantech.training;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/5/2016.
 */

public class RecycleViewCustom extends RecyclerView.Adapter<RecycleViewCustom.ViewHolder> {
    private ArrayList<AlarmObj> mAlarmArr = new ArrayList<>();
    private Context context;
    private onSetDetete mListener;

    public RecycleViewCustom(MainActivity mainActivity, ArrayList<AlarmObj> a) {
        this.context = mainActivity;
        this.mAlarmArr = a;
    }

    @Override
    public RecycleViewCustom.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_custom, null);
        mListener = (MainActivity) parent.getContext();
        RecycleViewCustom.ViewHolder rcv = new RecycleViewCustom.ViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewCustom.ViewHolder holder, final int position) {
        String dayofweek = null;
        holder.title.setText(mAlarmArr.get(position).getTitle().toString());
        switch (mAlarmArr.get(position).getDayofweek()) {
            case 0:
                dayofweek = "All";
                break;
            case 1:
                dayofweek = "Sunday";
                break;
            case 2:
                dayofweek = "Monday";
                break;
            case 3:
                dayofweek = "Tuesday";
                break;
            case 4:
                dayofweek = "Wednesday";
                break;
            case 5:
                dayofweek = "Thursday";
                break;
            case 6:
                dayofweek = "Friday";
                break;
            case 7:
                dayofweek = "Saturday";
                break;
        }
        holder.time.setText(dayofweek + "   " + mAlarmArr.get(position).getHour() + ":" + mAlarmArr.get(position).getMinute());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.deletePosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlarmArr.size();
    }

    public interface onSetDetete {
        void deletePosition(int position);
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
