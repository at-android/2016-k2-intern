package vn.asiantech.training.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import vn.asiantech.training.Bean.Day;
import vn.asiantech.training.Bean.Time;
import vn.asiantech.training.Model.DBHelper;
import vn.asiantech.training.R;

public class ClockActivity extends AppCompatActivity implements DayOfWeekAdapter.InteractionListener {
    public static final String KEY_TIME = "time";
    public static int RESULT_CODE;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Day> mDays;
    private Time mTime;
    private boolean isCheck = false;
    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        isCheck = false;
        mDays = new ArrayList<>();
        Bundle bundle = getIntent().getBundleExtra(MainActivity.KEY_BUNDLE);
        String[] stringArray = getResources().getStringArray(R.array.day);
        for (int i = 0; i < stringArray.length; i++) {
            mDays.add(new Day(stringArray[i], false));
        }
        if (bundle != null) {
            RESULT_CODE = 2;
            int id = bundle.getInt(MainActivity.KEY_ID);
            mTime = getTimefromDb(id);
            String[] dayofweek = mTime.getDayofweek().split(" ");
            for (int i = 0; i < dayofweek.length; i++) {
                mDays.get(getDayofWeek(dayofweek[i])).setCheck(true);
            }
        } else {
            RESULT_CODE = 1;
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DayOfWeekAdapter(mDays, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    public Time getTimefromDb(int id) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        return dbHelper.getTime(id);
    }

    public void save(View view) {
        Time time = getmTime();
        if (isCheck == true) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_TIME, time);
            intent.putExtra(MainActivity.KEY_BUNDLE, bundle);
            setResult(RESULT_CODE, intent);
            finish();
        } else {
            Toast.makeText(this, "Nothing Day of Week is Checked!", Toast.LENGTH_LONG).show();
        }
    }

    public int getDayofWeek(String dayofweek) {
        switch (dayofweek) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Satusday":
                return 6;
        }
        return 9;
    }

    public Time getmTime() {
        String s = new String();
        for (int i = 0; i < mDays.size(); i++) {
            if (mDays.get(i).isCheck() == true) {
                s += mDays.get(i).getDayOfweek() + " ";
                isCheck = true;
            }
        }
        Time time = new Time(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute(), s, MainActivity.isRunning);
        return time;
    }

    @Override
    public void onFragmentInteraction(int position) {
        mDays.get(position).setCheck(true);
    }

    @Override
    public void onFragmentInteraction2(int position) {
        mDays.get(position).setCheck(false);
    }
}
