package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 29/11/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<People> mPeoples;

    public ContactAdapter(ArrayList<People> peoples) {
        this.mPeoples = peoples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_people, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mPeoples.get(position).getName());
        holder.tvPhoneNumber.setText(mPeoples.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvPhoneNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvPeopleName);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}
