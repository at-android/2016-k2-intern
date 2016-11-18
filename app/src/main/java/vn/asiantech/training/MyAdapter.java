package vn.asiantech.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by HoangDuy on 17/11/2016.
 */
public class MyAdapter extends FragmentPagerAdapter {

    private int mQuantityQuestion;

    public MyAdapter(FragmentManager fm, int quantityQuestion) {
        super(fm);
        this.mQuantityQuestion = quantityQuestion;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = QuestionFragment.newInstance(position);
        return questionFragment;
    }


    @Override
    public int getCount() {
        return mQuantityQuestion;
    }

}
