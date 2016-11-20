package vn.asiantech.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HoangDuy on 18/11/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public OnFragmentInteractionListener mListener;

    private List<Foody> mFoodyList;

    public HomeAdapter(List<Foody> foodyList) {
        this.mFoodyList = foodyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        mListener = (DemoRecyclerViewActivity) parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageAvatar.setImageResource(mFoodyList.get(position).getImage());
        holder.name.setText(mFoodyList.get(position).getName());
        holder.category.setText(mFoodyList.get(position).getCategory());
        holder.price.setText(mFoodyList.get(position).getPrice());
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodyList.size();
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageAvatar;
        private TextView name;
        private TextView price;
        private TextView category;
        private ImageView imageEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            imageAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            name = (TextView) itemView.findViewById(R.id.tvName);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
            category = (TextView) itemView.findViewById(R.id.tvCategory);
            imageEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
        }

    }
}
