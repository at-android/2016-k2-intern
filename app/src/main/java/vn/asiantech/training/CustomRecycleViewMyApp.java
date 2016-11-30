package vn.asiantech.training;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/30/2016.
 */

public class CustomRecycleViewMyApp extends RecyclerView.Adapter<CustomRecycleViewMyApp.ViewHolder> {
    ArrayList<AppObject> appNameArr = new ArrayList<>();
    Context context;

    public CustomRecycleViewMyApp(FragmentActivity activity, ArrayList<AppObject> appName) {
        this.appNameArr = appName;
    }

    @Override
    public CustomRecycleViewMyApp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_app, null);
        CustomRecycleViewMyApp.ViewHolder rcv = new CustomRecycleViewMyApp.ViewHolder(view);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CustomRecycleViewMyApp.ViewHolder holder, int position) {
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(appNameArr.get(position).getAppPackage().toString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        holder.imgIcon.setImageDrawable(icon);
        holder.nameIcon.setText(appNameArr.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return appNameArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgIcon;
        public TextView nameIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            nameIcon = (TextView) itemView.findViewById(R.id.tv_name_of_app);
        }
    }
}
