package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {

    public static final String KEY_RESULT = "result";
    public static final String KEY_BUNDLE = "bundle";
    public static final String TITLE = "QUESTION";
    public static final String TV_GOPRE = "PRE";
    public static final String TV_GONEXT = "NEXT";
    public static final String TV_SHOWRESULT = "SHOW";
    public static final String TRUE_ANSWER = "T";
    public static final String FALSE_ANSWER = "F";
    String[] questions;
    private ViewPager mViewPager;
    private String[] mAnswerSheet;
    private String[] mResult;
    private TextView mTvGoPre;
    private TextView mTvGoNext;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTvGoNext = (TextView) findViewById(R.id.tvGoNext);
        mTvGoPre = (TextView) findViewById(R.id.tvGoPre);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        questions = getResources().getStringArray(R.array.questions);
        mAnswerSheet = new String[questions.length];
        mResult = new String[questions.length];
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), questions.length));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                mTvTitle.setText(TITLE + " " + (position + 1));
                display(position);
                mTvGoPre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(position - 1);
                    }
                });

                mTvGoNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goNext(position);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void display(int position) {
        if (position == questions.length - 1) {
            mTvGoNext.setText(TV_SHOWRESULT);
        } else if (position == 0) {
            mTvGoPre.setVisibility(View.INVISIBLE);
        } else {
            mTvGoNext.setText(TV_GONEXT);
            mTvGoPre.setText(TV_GOPRE);
            mTvGoPre.setVisibility(View.VISIBLE);
        }
    }

    public void goNext(int position) {
        mViewPager.setCurrentItem(position + 1);
        if (position == questions.length - 1) {
            IsClicked();
        }
    }

    @Override
    public void onFragmentInteraction(String answer, int position) {
        mAnswerSheet[position] = answer;
    }

    public void IsClicked() {
        String[] rightAnswer = getResources().getStringArray(R.array.rightAnswer);
        for (int i = 0; i < rightAnswer.length; i++) {
            if (rightAnswer[i].equals(mAnswerSheet[i])) {
                mResult[i] = TRUE_ANSWER;
            } else {
                mResult[i] = FALSE_ANSWER;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putStringArray(KEY_RESULT, mResult);
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra(KEY_BUNDLE, bundle);
        startActivity(intent);
    }
}
