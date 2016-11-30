package vn.asiantech.training;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/30/2016.
 */

public class CustomRecycleViewContacts extends RecyclerView.Adapter<CustomRecycleViewContacts.ViewHolder> {
    private ArrayList<PhoneNumberObj> mArrayPhone = new ArrayList<>();

    public CustomRecycleViewContacts(FragmentActivity activity, ArrayList<PhoneNumberObj> mArrayPhone) {
        this.mArrayPhone = mArrayPhone;
    }

    @Override
    public CustomRecycleViewContacts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_my_contact, null);
        CustomRecycleViewContacts.ViewHolder rcv = new CustomRecycleViewContacts.ViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CustomRecycleViewContacts.ViewHolder holder, int position) {
        holder.tvName.setText(mArrayPhone.get(position).getName() + "");
        holder.tvNumber.setText(mArrayPhone.get(position).getPhone() + "");
    }

    @Override
    public int getItemCount() {
        return mArrayPhone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_my_contact_name);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_my_contact_number);
        }
    }
}
