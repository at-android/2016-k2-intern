package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static int FLAG = 1;
    public clickListener mListener;
    private List<People> mPeoples;

    public RecyclerViewPeopleAdapter(List<People> peoples) {
        mPeoples = peoples;
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
        holder.peopleName.setText(mPeoples.get(position).getName());
        holder.phoneNumber.setText(mPeoples.get(position).getPhoneNumber());
        holder.iconFavorite.setImageResource(mPeoples.get(position).getIconFavorite());
        holder.iconFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite(position)) {
                    FLAG = 1;
                } else {
                    FLAG = 0;
                }
                Log.i("info", FLAG + "");
                mListener.onClick(position, FLAG);
            }
        });
    }

    public boolean isFavorite(int position) {
        if (mPeoples.get(position).getIconFavorite() == R.drawable.ic_favorite_green_500_24dp) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
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
