package vn.asiantech.training.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.ApplicationRecycleViewAdapter;

/**
 * Created by phuong on 29/11/2016.
 */
@EFragment(R.layout.fragment_tab3)
public class FragmentTab3 extends Fragment {
    @ViewById(R.id.rvContactDevice)
    RecyclerView mRecyclerView;
    private List<ApplicationInfo> mApplicationInfos;
    private ApplicationRecycleViewAdapter mApplicationRecycleViewAdapter;

    @AfterViews
    void initRecyclerViewData() {
        mApplicationInfos = getActivity().getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mApplicationRecycleViewAdapter = new ApplicationRecycleViewAdapter(mApplicationInfos, getActivity());
        mRecyclerView.setAdapter(mApplicationRecycleViewAdapter);
    }
}
