package vn.asiantech.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.training.fragment.QuestionFragment;

/**
 * Created by phuong on 18/11/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private int mQualityQuestion;

    public ViewPagerAdapter(FragmentManager fm, int qualityQuestion) {
        super(fm);
        mQualityQuestion = qualityQuestion;
    }

    @Override
    public int getCount() {
        return mQualityQuestion;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = QuestionFragment.newInstance(position);
        return questionFragment;
    }
}
