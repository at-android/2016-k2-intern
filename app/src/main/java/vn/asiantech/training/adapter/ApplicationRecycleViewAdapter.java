package vn.asiantech.training.adapter;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import vn.asiantech.training.R;

/**
 * Created by phuong on 30/11/2016.
 */

public class ApplicationRecycleViewAdapter extends RecyclerView.Adapter<ApplicationRecycleViewAdapter.MyViewHolder> {
    private List<ApplicationInfo> mApps;
    private Activity mContext;

    public ApplicationRecycleViewAdapter(List<ApplicationInfo> apps, Activity context) {
        mApps = apps;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_tab_3, parent, false);

        return new ApplicationRecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ApplicationInfo app = mApps.get(position);
        Drawable icon = app.loadIcon(mContext.getPackageManager());
        holder.mImageIcon.setImageDrawable(icon);
        holder.mName.setText(mContext.getPackageManager().getApplicationLabel(app).toString());
        holder.mPackageName.setText(app.packageName);
        holder.mSize.setText(((int) (new File(app.publicSourceDir).length() / 1024)) * 1.0 / 1000 + " Mb");
        long installDate;
        try {
            installDate = mContext.getPackageManager().getPackageInfo(app.packageName, 0).firstInstallTime;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(installDate);
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            holder.mDate.setText(mDay + "/" + mMonth + "/" + mYear);

        } catch (
                PackageManager.NameNotFoundException e) {
            Log.e("apps", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageIcon;
        TextView mName;
        TextView mPackageName;
        TextView mSize;
        TextView mDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageIcon = (ImageView) itemView.findViewById(R.id.imageIcon);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPackageName = (TextView) itemView.findViewById(R.id.packageName);
            mSize = (TextView) itemView.findViewById(R.id.size);
            mDate = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
