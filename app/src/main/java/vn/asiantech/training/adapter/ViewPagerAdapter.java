package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.training.fragments.ListAddressFragment;

/**
 * Created by MaiManhDuy on 12/16/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    Context mContext;
    private String mTabLitles[] = new String[]{"My contacts", "Local contacts", "My app"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListAddressFragment fragmenttab1 = new ListAddressFragment();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                ListAddressFragment fragmenttab2 = new ListAddressFragment();
                return fragmenttab2;

            // Open FragmentTab3.java
            case 2:
                ListAddressFragment fragmenttab3 = new ListAddressFragment();
                return fragmenttab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabLitles[position];
    }
}
