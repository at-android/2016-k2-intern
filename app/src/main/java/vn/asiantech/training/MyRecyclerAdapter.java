package vn.asiantech.training;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    public InteractionListener mListener;
    private ArrayList<Time> mTime;
    private Context mContext;

    public MyRecyclerAdapter(ArrayList<Time> time, Context context) {
        mTime = time;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_alarm, parent, false);
        mListener = (MainActivity) parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String s = "";
        String strings[] = mTime.get(position).getDayofweek().split(" ");
        for (int i = 0; i < strings.length; i++) {
            switch (strings[i]) {
                case "Monday":
                    s += "Mon ";
                    break;
                case "Tuesday":
                    s += "Tue ";
                    break;
                case "Wednesday":
                    s += "Wed ";
                    break;
                case "Thursday":
                    s += "Thu ";
                    break;
                case "Friday":
                    s += "Fri ";
                    break;
                case "Satusday":
                    s += "Sat ";
                    break;
                case "Sunday":
                    s += "Sun ";
                    break;
            }
        }
        holder.tvDayofWeek.setText(s);
        holder.tvTime.setText(mTime.get(position).getHour() + ":" + mTime.get(position).getMinute());
        if (mTime.get(position).getFlag() == 1) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mListener.onFragmentInteraction(position + 1, 0);
                } else {
                    mListener.onFragmentInteraction(position + 1, 1);
                }
            }
        });
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEditInteraction(position + 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTime.size();
    }

    interface InteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position, int flag);

        void onEditInteraction(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvDayofWeek;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDayofWeek = (TextView) itemView.findViewById(R.id.tvDayofWeek);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }
}