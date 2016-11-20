package vn.asiantech.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by MaiManhDuy on 11/17/2016.
 */

public class QuizzAdapter extends FragmentStatePagerAdapter {
    public QuizzAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment newInstance = null;
        newInstance = DoQuizzFragment.newInstance(position);
        return newInstance;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
