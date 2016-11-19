package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static vn.asiantech.training.R.id.tvTitle;

public class MainActivity extends AppCompatActivity implements QuestionFragment.OnHeadlineSelectedListener, View.OnClickListener {
    public static final int QUANTITY_QUESTION = 10;
    public static final String TRUE_ANSWER = "T";
    public static final String FALSE_ANSWER = "F";
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<Question> mArr = new ArrayList<Question>();
    private Button mBtnNext;
    private TextView mTvTitle;
    private TextView mBtnPrevious;
    private String[] mResultArray;
    private Button mBtnPlay;
    private Button mBtnQuit;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormwidget();
        initData();
        mResultArray = new String[QUANTITY_QUESTION];
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), QUANTITY_QUESTION, mArr);
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                mPosition = position;
                mTvTitle.setText("Question " + (position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        inviButton();
        mBtnNext.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
        mBtnQuit.setOnClickListener(this);
    }

    public void getFormwidget() {
        mBtnNext = (Button) findViewById(R.id.btnNext);
        mBtnPlay = (Button) findViewById(R.id.btnPlay);
        mBtnQuit = (Button) findViewById(R.id.btnQuit);
        mBtnPrevious = (Button) findViewById(R.id.btnPrevious);
        mTvTitle = (TextView) findViewById(tvTitle);
    }

    public void inviButton() {
        mBtnNext.setVisibility(View.INVISIBLE);
        mBtnPrevious.setVisibility(View.INVISIBLE);
        mPager.setVisibility(View.INVISIBLE);
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
        Question q9 = new Question("9. The little girl asked what.............to her friend.", "A. has happened", "B. happened", "C. had happened", "D. would have been happened", "C. had happened");
        Question q10 = new Question("10. John ............a book when I saw him.", "A. is reading", "B. read", "C. was reading", "D. reading", "C. was reading");
        mArr.add(q1);
        mArr.add(q2);
        mArr.add(q3);
        mArr.add(q4);
        mArr.add(q5);
        mArr.add(q6);
        mArr.add(q7);
        mArr.add(q8);
        mArr.add(q9);
        mArr.add(q10);
    }

    //receive data from QuestionFragment
    @Override
    public void onArticleSelected(String chosenKey, int position) {
        Log.i("positionTraVe", position + "");
        if (chosenKey.equals(mArr.get(position).getResult())) {
            mResultArray[position] = TRUE_ANSWER;
        } else {
            mResultArray[position] = FALSE_ANSWER;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnPrevious.setVisibility(View.VISIBLE);
                mPager.setVisibility(View.VISIBLE);
                mBtnPlay.setVisibility(View.INVISIBLE);
                mBtnQuit.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnNext:
                if (mPosition < 9) {
                    if (mPosition == 8) {
                        mBtnNext.setText("Result");
                    }
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                } else {
                    if (checkNotNull(mResultArray)) {
                        mTvTitle.setText("Result");
                        mBtnNext.setVisibility(View.INVISIBLE);
                        mBtnPrevious.setVisibility(View.INVISIBLE);
                        mPager.setVisibility(View.INVISIBLE);
                        FragmentManager fm = getSupportFragmentManager();
                        ResultFragment frag = new ResultFragment();
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("resultArray", mResultArray);
                        frag.setArguments(bundle);
                        fm.beginTransaction().replace(R.id.activity_main, frag).commit();
                    } else {
                        Toast.makeText(this, "Please Answer All Question", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btnPrevious:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                mBtnNext.setText("Next");
                break;
            case R.id.btnQuit:
                System.exit(1);
                break;
        }
    }

    public boolean checkNotNull(String[] arr) {
        for (int i = 0; i < QUANTITY_QUESTION; i++) {
            if (mResultArray[i] == null) {
                return false;
            }
        }
        return true;
    }
}
