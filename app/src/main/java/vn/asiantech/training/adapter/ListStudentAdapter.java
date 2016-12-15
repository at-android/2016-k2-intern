package vn.asiantech.training.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.MainActivity_;
import vn.asiantech.training.R;
import vn.asiantech.training.objects.StudentObj;

/**
 * Created by MaiManhDuy on 12/15/2016.
 */

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ViewHolder> {
    private List<StudentObj> studentArrays = new ArrayList<>();
    private sendPostionFormListToInfo mListener;

    public ListStudentAdapter(FragmentActivity fragmentActivity, List<StudentObj> studentArrays) {
        this.studentArrays = studentArrays;
    }

    @Override
    public ListStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity_) parent.getContext();
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_student, null);
        ListStudentAdapter.ViewHolder rcv = new ListStudentAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListStudentAdapter.ViewHolder holder, final int position) {
        holder.mTvSchool.setText(studentArrays.get(position).getSchool());
        holder.mTvAddress.setText(studentArrays.get(position).getAddress());
        holder.mTvName.setText(studentArrays.get(position).getName());
        holder.mTvOld.setText(String.format(studentArrays.get(position).getOld() + ""));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendPostionToInfo(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrays.size();
    }

    public interface sendPostionFormListToInfo {
        void sendPostionToInfo(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvSchool;
        public TextView mTvName;
        public TextView mTvAddress;
        public TextView mTvOld;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvSchool = (TextView) itemView.findViewById(R.id.tvItemSchool);
            mTvAddress = (TextView) itemView.findViewById(R.id.tvItemAddress);
            mTvName = (TextView) itemView.findViewById(R.id.tvItemName);
            mTvOld = (TextView) itemView.findViewById(R.id.tvItemOld);
        }
    }
}
