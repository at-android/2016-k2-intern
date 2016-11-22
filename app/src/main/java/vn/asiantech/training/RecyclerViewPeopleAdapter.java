package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HoangDuy on 22/11/2016.
 */
public class RecyclerViewPeopleAdapter extends RecyclerView.Adapter<RecyclerViewPeopleAdapter.ViewHolder> {

    private static int FLAG = 0;
    public clickListener mListener;
    private List<People> mPeople;

    public RecyclerViewPeopleAdapter(List<People> peoples) {
        mPeople = peoples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_people, parent, false);
        mListener = (clickListener) parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.peopleName.setText(mPeople.get(position).getName());
        holder.phoneNumber.setText(mPeople.get(position).getPhoneNumber());
        holder.iconFavorite.setImageResource(mPeople.get(position).getIconFavorite());
        holder.iconFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FLAG++;
                mListener.onClick(position, FLAG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public interface clickListener {
        void onClick(int position, int flag);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView peopleName;
        private TextView phoneNumber;
        private ImageView iconFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            peopleName = (TextView) itemView.findViewById(R.id.tvPeopleName);
            phoneNumber = (TextView) itemView.findViewById(R.id.tvPhoneNumber);
            iconFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
        }
    }
}
