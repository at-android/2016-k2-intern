package vn.asiantech.training.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.ApplicationRecycleViewAdapter;

/**
 * Created by phuong on 29/11/2016.
 */

public class FragmentTab3 extends Fragment {
    private List<ApplicationInfo> mApplicationInfos;
    private ApplicationRecycleViewAdapter mApplicationRecycleViewAdapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvContactDevice);
        mApplicationInfos = getActivity().getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mApplicationRecycleViewAdapter = new ApplicationRecycleViewAdapter(mApplicationInfos, getActivity());
        mRecyclerView.setAdapter(mApplicationRecycleViewAdapter);
        return view;
    }
}
