package vn.asiantech.training.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.training.fragment.FragmentTab1;
import vn.asiantech.training.fragment.FragmentTab2;
import vn.asiantech.training.fragment.FragmentTab3;

/**
 * Created by phuong on 29/11/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    Context context;
    // Tab Titles
    private String tabtitles[] = new String[]{"Tab1", "Tab2", "Tab3"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                FragmentTab1 fragmenttab1 = new FragmentTab1();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                FragmentTab2 fragmenttab2 = new FragmentTab2();
                return fragmenttab2;

            // Open FragmentTab3.java
            case 2:
                FragmentTab3 fragmenttab3 = new FragmentTab3();
                return fragmenttab3;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}
