package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import vn.asiantech.training.adapter.ViewPagerAdapter;
import vn.asiantech.training.fragment.QuestionFragment;

/**
 * Created by phuong on 18/11/2016.
 */

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {
    public static final String TITLE = "QUESTION";
    public static final String RESULT = "RESULT";
    public static final String NEXT = "NEXT";
    public static final String ANSWER_TRUE = "T";
    public static final String ANSWER_FALSE = "F";
    public static final String PREVIOUS = "PREVIOUS";
    public static final String KEY_STRING_ARRAY = "result";
    private TextView mTvTitle;
    private TextView mTvPrevious;
    private TextView mTvNext;
    private ViewPager mVpgQuestion;
    private String[] questions;
    private String[] mAnswerSheet;
    private String[] mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrevious = (TextView) findViewById(R.id.tvPrevious);
        mTvNext = (TextView) findViewById(R.id.tvnext);
        mVpgQuestion = (ViewPager) findViewById(R.id.vpgQuestion);
        questions = getResources().getStringArray(R.array.questions);
        mAnswerSheet = new String[questions.length];
        mResult = new String[questions.length];

        mVpgQuestion.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), questions.length));
        mVpgQuestion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                mTvTitle.setText(TITLE + " " + (position + 1));
                display(position);
                mTvPrevious.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mVpgQuestion.setCurrentItem(position - 1);
                    }
                });
                mTvNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        accessToNext(position);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(String answer, int position) {
        mAnswerSheet[position] = answer;
    }

    public void display(int position) {
        if (position == (questions.length - 1)) {
            mTvNext.setText(RESULT);
        } else {
            if (position == 0) {
                mTvPrevious.setVisibility(View.INVISIBLE);
            } else {
                mTvNext.setText(NEXT);
                mTvPrevious.setText(PREVIOUS);
                mTvNext.setVisibility(View.VISIBLE);
                mTvPrevious.setVisibility(View.VISIBLE);
            }
        }
    }

    public void accessToNext(int position) {
        mVpgQuestion.setCurrentItem(position + 1);
        if (position == (questions.length - 1)) {
            String[] rightAnswer = getResources().getStringArray(R.array.rightAnswer);
            for (int i = 0; i < rightAnswer.length; i++) {
                if (rightAnswer[i].equals(mAnswerSheet[i])) {
                    mResult[i] = ANSWER_TRUE;
                } else {
                    mResult[i] = ANSWER_FALSE;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putStringArray(KEY_STRING_ARRAY, mResult);
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
