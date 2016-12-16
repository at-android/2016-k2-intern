package vn.asiantech.training.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.asiantech.training.R;

/**
 * Created by HoangDuy on 01/12/2016.
 */
public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
    private List<ApplicationInfo> mAppsList = null;
    private Context mContext;
    private PackageManager mPackageManager;

    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<ApplicationInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.mContext = context;
        this.mAppsList = appsList;
        mPackageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((null != mAppsList) ? mAppsList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != mAppsList) ? mAppsList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_installed_app, null);
        }

        ApplicationInfo applicationInfo = mAppsList.get(position);
        if (null != applicationInfo) {
            TextView appName = (TextView) view.findViewById(R.id.tvAppName);
            ImageView iconview = (ImageView) view.findViewById(R.id.imgAppIcon);
            appName.setText(applicationInfo.loadLabel(mPackageManager).toString());
            iconview.setImageDrawable(applicationInfo.loadIcon(mPackageManager));
        }
        return view;
    }
};