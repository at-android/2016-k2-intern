package vn.asiantech.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.asiantech.training.fragment.ContactFragment;
import vn.asiantech.training.fragment.ContactFragment_;
import vn.asiantech.training.fragment.InstalledAppFragment;
import vn.asiantech.training.fragment.OriginalContactFragment;
import vn.asiantech.training.model.People;

/**
 * Created by HoangDuy on 29/11/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_COUNT = 3;
    private String mTabTitles[] = new String[]{"INTRO", "Contact", "App"};
    private ArrayList<People> mPeoples;

    public PageAdapter(FragmentManager fm, ArrayList<People> peoples) {
        super(fm);
        mPeoples = peoples;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ContactFragment contactFragment = new ContactFragment_();
                return contactFragment;
            case 1:
                OriginalContactFragment originalContactFragment = new OriginalContactFragment();
                return originalContactFragment;

            case 2:
                InstalledAppFragment installedAppFragment = new InstalledAppFragment();
                return installedAppFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mTabTitles[position];
    }
}
