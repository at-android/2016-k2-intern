package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 19/11/2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int mQuantityQuestion;
    private ArrayList<Question> mArr = new ArrayList<Question>();
    public ViewPagerAdapter(FragmentManager fm, int QuantityQuestion,ArrayList<Question> arr) {
        super(fm);
        this.mQuantityQuestion = QuantityQuestion;
        this.mArr = arr;
    }

    @Override
    public Fragment getItem(int position) {
            Question q = mArr.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("question", q);
            bundle.putInt("position",position);
            QuestionFragment frag = new QuestionFragment();
            frag.setArguments(bundle);
            return frag;
    }

    @Override
    public int getCount() {
        return mQuantityQuestion;
    }
}
