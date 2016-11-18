package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static vn.asiantech.training.R.id.tvTitle;

public class MainActivity extends AppCompatActivity implements QuestionFragment.OnHeadlineSelectedListener {
    private static final int NUM_PAGES = 11;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<Question> arr = new ArrayList<Question>();
    private Button mBtnNext;
    private TextView mTvTitle;
    private TextView mBtnPrevious;
    private String[] mResultArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormwidget();
        initData();

        mResultArray = new String[NUM_PAGES];
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvTitle.setText("Cau " + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);

            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });

    }


    public void getFormwidget() {
        mBtnNext = (Button) findViewById(R.id.btnNext);
        mBtnPrevious = (Button) findViewById(R.id.btnPrevious);
        mTvTitle = (TextView) findViewById(tvTitle);
    }

    public void initData() {
        Question q1 = new Question("1. I ............Louisiana state University.", "A. am attending", "B. attend", "C. was attending", "D. attended", "A. am attending");
        Question q2 = new Question("2. He fell down when he ............towards the church.", "A. run", "B. runs", "C. was running", "D. had run", "C. was running");
        Question q3 = new Question("3. We ............there when our father died.", "A. still lived", "B. lived still", "C. was still living", "D. were still living", "D. were still living");
        Question q4 = new Question("4. They ............pingpong when their father comes back home.", "A. will play", "B. will be playing", "C. play", "D. would play", "B. will be playing");
        Question q5 = new Question("5. By Christmas, I.............for you for 6 months.", "A. Shall have been working", "B. shall work", "C. have been working", "D. shall be working", "A. Shall have been working");
        Question q6 = new Question("6. I............in the room now.", "A. am being", "B. was being", "C. have been being", "D. am", "D. am");
        Question q7 = new Question("7. I.............to New york three times this year.", "A. have been", "B. was", "C. were", "D. had been", "A. have been");
        Question q8 = new Question("8. I will come and see you before I.............for America.", "A. leave", "B. will leave", "C. have left", "D. shall leave", "A. leave");
        Question q9 = new Question("9. The little girl asked wha.............to her friend.", "A. has happened", "B. happened", "C. had happened", "D. would have been happened", "C. had happened");
        Question q10 = new Question("10. John ............a book when I saw him.", "A. is reading", "B. read", "C. was reading", "D. reading", "C. was reading");
        arr.add(q1);
        arr.add(q2);
        arr.add(q3);
        arr.add(q4);
        arr.add(q5);
        arr.add(q6);
        arr.add(q7);
        arr.add(q8);
        arr.add(q9);
        arr.add(q10);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {

            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    //receive data from QuestionFragment
    @Override
    public void onArticleSelected(String chosenKey, int position) {
        if (chosenKey.equals(arr.get(position).getResult())) {
            mResultArray[position] = "T";
        } else {
            mResultArray[position] = "F";
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position < 10) {
//                Log.i("position",position+"");
//                Log.i("positionForMain",mPager.getCurrentItem()+"");
                Question q = arr.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putSerializable("question", q);
                Fragment frag = new QuestionFragment();
                frag.setArguments(bundle);
                return frag;
            } else {
                Fragment frag = new ResultFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("Key", mResultArray);
                frag.setArguments(bundle);
                return frag;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
