package vn.asiantech.training.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.training.ContentProContactFragment;
import vn.asiantech.training.ContentProContactFragment_;
import vn.asiantech.training.Fragment.ListContactFragment;
import vn.asiantech.training.Fragment.ListContactFragment_;
import vn.asiantech.training.Fragment.ShowAppFragment;
import vn.asiantech.training.Fragment.ShowAppFragment_;

/**
 * Created by Administrator on 29/11/2016.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListContactFragment frag = new ListContactFragment_();
                return frag;
            case 1:
                ContentProContactFragment frag1 = new ContentProContactFragment_();
                return frag1;
            case 2:
                ShowAppFragment frag2 = new ShowAppFragment_();
                return frag2;
            default: return null;
        }
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
