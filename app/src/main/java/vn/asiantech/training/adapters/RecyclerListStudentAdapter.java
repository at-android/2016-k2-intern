package vn.asiantech.training.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.listerners.callOnClickListener;
import vn.asiantech.training.models.Student;

/**
 * Created by phuong on 14/12/2016.
 */

public class RecyclerListStudentAdapter extends RecyclerView.Adapter<RecyclerListStudentAdapter.MyViewHolder> {
    private List<Student> mStudents = new ArrayList<>();
    private Context mContext;
    private callOnClickListener mCallOnClickListener;

    public RecyclerListStudentAdapter(List<Student> mStudents, Context mContext, callOnClickListener listener) {
        this.mStudents = mStudents;
        this.mContext = mContext;
        mCallOnClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_student, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.mTvName.setText(student.getName());
        holder.mTvSchool.setText(student.getSchool());
        holder.mTvAge.setText(student.getAge());
        holder.mTvAddress.setText(student.getAddress());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView mTvName;
        final TextView mTvAge;
        final TextView mTvSchool;
        final TextView mTvAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvAge = (TextView) itemView.findViewById(R.id.tvAge);
            mTvSchool = (TextView) itemView.findViewById(R.id.tvSchool);
            mTvAddress = (TextView) itemView.findViewById(R.id.tvAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallOnClickListener.onItemClickListener(mStudents.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
