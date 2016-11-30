package vn.asiantech.training;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by MaiManhDuy on 11/29/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    Context context;
    private String mTabLitles[] = new String[]{"My contacts", "Local contacts", "My app"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                MyContactdFragment fragmenttab1 = new MyContactdFragment();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                LocalContactFragment fragmenttab2 = new LocalContactFragment();
                return fragmenttab2;

            // Open FragmentTab3.java
            case 2:
                MyAppFragment fragmenttab3 = new MyAppFragment();
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
