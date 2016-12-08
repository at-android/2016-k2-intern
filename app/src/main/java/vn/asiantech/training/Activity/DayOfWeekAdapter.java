package vn.asiantech.training.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.Bean.Day;
import vn.asiantech.training.R;

/**
 * Created by HoangDuy on 01/12/2016.
 */
public class DayOfWeekAdapter extends RecyclerView.Adapter<DayOfWeekAdapter.ViewHolder> {

    public InteractionListener mListener;
    private ArrayList<Day> mDay;
    private Context mContext;

    public DayOfWeekAdapter(ArrayList<Day> day, Context context) {
        mDay = day;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_day, parent, false);
        mListener = (ClockActivity) parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvDayofWeek.setText(mDay.get(position).getDayOfweek());
        if (mDay.get(position).isCheck()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mListener.onFragmentInteraction(position);
                } else {
                    mListener.onFragmentInteraction2(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDay.size();
    }

    interface InteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);

        void onFragmentInteraction2(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDayofWeek;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDayofWeek = (TextView) itemView.findViewById(R.id.tvDayofWeek);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
