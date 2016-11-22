package vn.asiantech.training;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private String[] mArrItem;
    private Context mContext;
    private MainActivity main;
    private ArrayList<Human> mArrHuman = new ArrayList<Human>();

    public NavigationAdapter(String[] list, ArrayList<Human> mArrHuman, Context mContext, MainActivity main) {
        this.mArrItem = list;
        this.mArrHuman = mArrHuman;
        this.mContext = mContext;
        this.main = main;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.navigation_item, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv.setText(mArrItem[position]);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tv.getText().equals(mArrItem[0])) {
                    FragmentManager fm = main.getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ContactFragment frag = ContactFragment.newInstance(mArrHuman);
                    ft.replace(R.id.content_frame, frag).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrItem.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvItem);
        }
    }


}
