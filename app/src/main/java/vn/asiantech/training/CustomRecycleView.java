package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/18/2016.
 */

public class CustomRecycleView extends
        RecyclerView.Adapter<CustomRecycleView.MyViewHolder> {
    private ArrayList<String> mResultArray = new ArrayList<>();

    public CustomRecycleView(FragmentActivity activity, ArrayList<String> scoreArray) {
        this.mResultArray = scoreArray;
    }

    @Override
    public CustomRecycleView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_custom, null);
        CustomRecycleView.MyViewHolder rcv = new CustomRecycleView.MyViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CustomRecycleView.MyViewHolder holder, int position) {
        int line = position + 1;
        holder.mQuestion.setText(line + "");
        holder.mResult.setText(mResultArray.get(position).toString());
        if (mResultArray.get(position).toString().equals("T")) {
            holder.mResult.setTextColor(ContextCompat.getColor(context, R.color.color_name));
        } else {
            holder.mResult.);
        }
    }

    @Override
    public int getItemCount() {
        return mResultArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mQuestion;
        public TextView mResult;

        public MyViewHolder(View view) {
            super(view);
            mQuestion = (TextView) view.findViewById(R.id.tvQuestionCustom);
            mResult = (TextView) view.findViewById(R.id.tvResultCustom);
        }
    }
}
