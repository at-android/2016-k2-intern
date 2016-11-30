package vn.asiantech.training;


import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ShowAppFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAppAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AppInfo> mArr = new ArrayList<AppInfo>();
    public ShowAppFragment() {
        // Required empty public constructor
    }


    public static ShowAppFragment newInstance() {
        ShowAppFragment fragment = new ShowAppFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_app, container, false);
        getInstalledApps(true);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewListApp);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerAppAdapter(mArr);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }



    private ArrayList<AppInfo> getInstalledApps(boolean getSysPackages) {
        List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            AppInfo app = new AppInfo();
            app.setName(p.applicationInfo.loadLabel(getActivity().getPackageManager()).toString());
            app.setIcon( p.applicationInfo.loadIcon(getActivity().getPackageManager()));
            mArr.add(app);
        }
        return mArr;
    }

}
