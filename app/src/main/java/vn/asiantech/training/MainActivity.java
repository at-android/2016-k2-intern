package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SetTimeFragment.SendData, View.OnClickListener, EditFragment.SendDataFromEditFrag {
    public static final String ACTION = "vn.asiantech.training.CUSTOM_INTENT";
    public static final String GET_TIME_BUNDLE = "time";
    public static final String GET_POSITION_BUNDLE = "position";
    public static final String GET_FLAG_BUNDLE = "Flag";
    public static final String GET_DATA_BUNDLE = "data";
    private Button mBtnStart;
    private ImageView mImgView;
    private int mHour;
    private int mMinute;
    private int mFlag = 0;
    private List<Time> mArrs = new ArrayList<Time>();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper mDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidget();
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mBtnStart.setOnClickListener(this);
        mImgView.setOnClickListener(this);
        mDb = new DatabaseHelper(getBaseContext());
        mDb.open();
        mArrs = mDb.getData();
        mDb.close();
        mAdapter = new MyAdapter(mArrs, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getFormWidget() {
        mBtnStart = (Button) findViewById(R.id.btnStart);
        mImgView = (ImageView) findViewById(R.id.imgView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public void onArticleSelected(Time t) {
        mHour = Integer.parseInt(t.getHour());
        mMinute = Integer.parseInt(t.getMinute());
        Log.i("hourFromFrag", mHour + "");
        Log.i("minuteFromFrag", mMinute + "");
        Log.i("mArrSize", mArrs.size() + "");
        //   mAdapter.addItem(mArrs.size(), t);
        mArrs.add(t);
        mAdapter.updateList(mArrs);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                if (mFlag == 0) {
                    startService(new Intent(getBaseContext(), myService.class));
                    mFlag++;
                } else {
                    Intent i = new Intent(ACTION);
                    Bundle bundle = new Bundle();
                    bundle.putInt(GET_FLAG_BUNDLE, mFlag);
                    i.putExtra(GET_DATA_BUNDLE, bundle);
                    sendBroadcast(i);
                }
                break;
            case R.id.imgView:
                DialogFragment frag = new SetTimeFragment();
                frag.show(getSupportFragmentManager(), "SetTimeFragment");
                break;
        }
    }

    @Override
    public void TimeEdited(Time time, int position) {
        mHour = Integer.parseInt(time.getHour());
        mMinute = Integer.parseInt(time.getMinute());
        Log.i("position", position + "");
        mArrs.set(position, time);
        mAdapter.updateList(mArrs);
    }
}
