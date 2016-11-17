package vn.asiantech.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 11/17/2016.
 */

public class QuizzAdapter extends FragmentStatePagerAdapter {
    public QuizzAdapter(FragmentManager fm, ArrayList<Fragment> listFragment) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment newInstance = null;
        switch (position) {
            case 0:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 1:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 2:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 3:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 4:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 5:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 6:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 7:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 8:
                newInstance = DoQuizzFragment.newInstance(position);
                break;
            case 9:
                newInstance = DoQuizzFragment.newInstance(position);
                break;


        }
        return newInstance;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
