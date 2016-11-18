package vn.asiantech.training;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DoQuizzFragment.waitCallBackPosition {
    public ArrayList<QuizzObj> sQuizzArray = new ArrayList<QuizzObj>();
    public ArrayList<String> ScoreArray = new ArrayList<String>();
    public int mPOSITION;
    private Button mBtnStartQuizz;
    private Button mBtnFinish;
    private ViewPager mPageQuizz;
    private Button mBtnNext;
    private Button mBtnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        insertData();
        init();
        mBtnNext.setVisibility(View.INVISIBLE);
        mBtnPrevious.setVisibility(View.INVISIBLE);
        mBtnStartQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnPrevious.setVisibility(View.VISIBLE);
                mBtnStartQuizz.setVisibility(View.INVISIBLE);
                mBtnFinish.setVisibility(View.INVISIBLE);
                FragmentManager fr = getSupportFragmentManager();
                PagerAdapter adapter = new QuizzAdapter(fr);
                mPageQuizz.setAdapter(adapter);
                mPageQuizz.setCurrentItem(0);
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNextAction();
            }
        });
        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPreviousAction();
            }
        });
        mPageQuizz.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPOSITION = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void insertData() {
        sQuizzArray.add(new QuizzObj("I ............Louisiana state University.", "am attending", "attend", "was attending", "attended", "am attending"));
        sQuizzArray.add(new QuizzObj("He fell down when he ............towards the church.", "run", "runs", "was running", "had run", "was running"));
        sQuizzArray.add(new QuizzObj("We ............there when our father died.", "still lived", "lived still", "was still living", "were still living", "were still living"));
        sQuizzArray.add(new QuizzObj("They ............pingpong when their father comes back home.", "will play", "will be playing", "play", "would play", "will be playing"));
        sQuizzArray.add(new QuizzObj("By Christmas, I.............for you for 6 months.", "Shall have been working", "shall work", "have been working", "shall be working", "Shall have been working"));
        sQuizzArray.add(new QuizzObj("I............in the room now.", "am being", "was being", "have been being", "am", "am"));
        sQuizzArray.add(new QuizzObj("I.............to New york three times this year.", "have been", "was", "were", "had been", "have been"));
        sQuizzArray.add(new QuizzObj("I will come and see you before I.............for America.", "leave", "will leave", "had left", "shall leave", "leave"));
        sQuizzArray.add(new QuizzObj("The little girl asked wha.............to her friend.", "has happened", "happened", "had happened", "would have been happened", "had happened"));
        sQuizzArray.add(new QuizzObj("John ............a book when I saw him.", "is reading", "read", "was reading", "reading", "was reading"));
    }

    private void init() {
        mBtnStartQuizz = (Button) findViewById(R.id.btnStartQuizz);
        mBtnFinish = (Button) findViewById(R.id.btnFinishActivity);
        mPageQuizz = (ViewPager) findViewById(R.id.vpContentQuizz);
        mBtnNext = (Button) findViewById(R.id.btnNext);
        mBtnPrevious = (Button) findViewById(R.id.btnPrevious);

    }

    private void btnNextAction() {
        FragmentManager fr = getSupportFragmentManager();
        PagerAdapter adapter = new QuizzAdapter(fr);
        mPageQuizz.setAdapter(adapter);
        int nowPosition = mPOSITION + 1;
        mPageQuizz.setCurrentItem(nowPosition, true);
        Log.d("nextbt", mPOSITION + "");
    }

    private void btnPreviousAction() {
        FragmentManager fr = getSupportFragmentManager();
        PagerAdapter adapter = new QuizzAdapter(fr);
        mPageQuizz.setAdapter(adapter);
        int nowPosition = mPOSITION - 1;
        mPageQuizz.setCurrentItem(nowPosition, true);
        Log.d("Previs", mPOSITION + "");
    }

    @Override
    public void onGetPositionFormDoQuizztoMain(int position) {
    }
}
