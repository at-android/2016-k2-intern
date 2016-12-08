package vn.asiantech.training.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import vn.asiantech.training.Bean.Time;
import vn.asiantech.training.Model.DBHelper;
import vn.asiantech.training.R;
import vn.asiantech.training.Service.MyService;

public class MainActivity extends AppCompatActivity implements AlarmAdapter.InteractionListener {

    public static final String ACTION = "vn.asiantech.training";
    public static final String KEY_BUNDLE = "bundle";
    public static final int RESULT_CODE = 1;
    public static final int RESULT_UPDATE = 2;
    public static final String KEY_ID = "id";
    public static final String KEY_TIME = "time";
    public static int FLAG = 0;
    public static int id = 0;
    public static int isRunning = 1;
    private Intent mIntent;
    private ArrayList<Time> mTimes;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTimes = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(this);
        mTimes = dbHelper.getAlarm();
        startService(new Intent(getBaseContext(), MyService.class));
        mLayout = new LinearLayoutManager(this);
        mAdapter = new AlarmAdapter(mTimes, this);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);

    }

    public ArrayList<Time> getAlarm() {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        return dbHelper.getAlarm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CODE) {
            Time time = (Time) data.getBundleExtra(KEY_BUNDLE).getSerializable(ClockActivity.KEY_TIME);
            mTimes.add(time);
            mAdapter.notifyDataSetChanged();
            insertDb(time);
            mIntent = new Intent(MainActivity.ACTION);
            ArrayList<Time> times = getAlarm();
            setBundle(mTimes);
            sendBroadcast(mIntent);
        } else if (resultCode == RESULT_UPDATE) {
            Time time = (Time) data.getBundleExtra(KEY_BUNDLE).getSerializable(ClockActivity.KEY_TIME);
            mTimes.get(id - 1).setHour(time.getHour());
            mTimes.get(id - 1).setMinute(time.getMinute());
            mTimes.get(id - 1).setDayofweek(time.getDayofweek());
            mAdapter.notifyDataSetChanged();
            updateDb(time, id, isRunning);
            mIntent = new Intent(MainActivity.ACTION);
            ArrayList<Time> times = getAlarm();
            setBundle(times);
            sendBroadcast(mIntent);
        }
    }

    public void setBundle(ArrayList<Time> times) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_TIME, times);
        mIntent.putExtra(MainActivity.KEY_BUNDLE, bundle);
    }

    public void insertDb(Time time) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.insert(mTimes.size(), time.getHour() + "", time.getMinute() + "", time.getDayofweek(), isRunning);
    }

    public void updateDb(Time time, int id, int isRunning) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.update(time, id, isRunning);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setAlarm) {
            Intent intent = new Intent(this, ClockActivity.class);
            startActivityForResult(intent, RESULT_CODE);
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(int position, int flag) {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.updateFlag(position, flag);
        ArrayList<Time> times = getAlarm();
        mIntent = new Intent(MainActivity.ACTION);
        setBundle(times);
        sendBroadcast(mIntent);
    }

    @Override
    public void onEditInteraction(int position) {
        id = position;
        Intent intent = new Intent(this, ClockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ID, position);
        intent.putExtra(KEY_BUNDLE, bundle);
        startActivityForResult(intent, RESULT_CODE);
    }
}
