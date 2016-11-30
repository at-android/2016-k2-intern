package vn.asiantech.training.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.training.ContentProContactFragment;
import vn.asiantech.training.Fragment.ListContactFragment;
import vn.asiantech.training.Fragment.ShowAppFragment;

/**
 * Created by Administrator on 29/11/2016.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = ListContactFragment.newInstance();
                break;
            case 1:
                frag = ContentProContactFragment.newInstance();
                break;
            case 2:
                frag = ShowAppFragment.newInstance();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TAB " + (position + 1);
    }
}
