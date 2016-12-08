package vn.asiantech.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.training.adapters.RecyclerViewAlarmAdapter;
import vn.asiantech.training.databases.DatabaseAlarm;
import vn.asiantech.training.listeners.RecyclerItemListener;
import vn.asiantech.training.models.Alarm;
import vn.asiantech.training.services.AlarmServices;

public class MainActivity extends AppCompatActivity implements RecyclerItemListener {
    public static String INTENT_KEY = "alarm_intent";
    public static String INTENT_KEY_POSITION = "alarm_intent_position";
    public static String BROADCAST_KEY = "alarm_boadcast";
    private int REQUEST_ADD_INTENT = 1000;
    private int REQUEST_EDIT_INTENT = 2000;
    private int REQUEST_DELETE_INTENT = 3000;
    private RecyclerView mRecyclerView;
    private TextView mTvAdd;
    private RecyclerViewAlarmAdapter mRecyclerViewAlarmAdapter;
    private ArrayList<Alarm> mAlarms;
    private DatabaseAlarm mDatabaseAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mTvAdd = (TextView) findViewById(R.id.tvAdd);

        mDatabaseAlarm = new DatabaseAlarm(this);
        mDatabaseAlarm.open();
        mAlarms = new ArrayList<>();
        mAlarms = initData();
        mDatabaseAlarm.close();
        mRecyclerViewAlarmAdapter = new RecyclerViewAlarmAdapter(mAlarms, this, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAlarmAdapter);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_alarm);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(R.string.app_alarm);

        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
                startActivityForResult(intent, REQUEST_ADD_INTENT);
            }
        });

        Intent intent = new Intent(this, AlarmServices.class);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_INTENT && resultCode == (AddAlarmActivity.RESULT_ADD_INTENT)) {
            Alarm alarm = data.getParcelableExtra(AddAlarmActivity.INTENT_ADD);
            mAlarms.add(alarm);
            mRecyclerViewAlarmAdapter.notifyDataSetChanged();
        }
        if (requestCode == REQUEST_EDIT_INTENT && resultCode == (EditAlarmActivity.RESULT_EDIT_INTENT)) {
            Alarm alarm = data.getParcelableExtra(EditAlarmActivity.INTENT_EDIT);
            int position = data.getIntExtra(MainActivity.INTENT_KEY_POSITION, 0);
            mAlarms.set(position, alarm);
            mRecyclerViewAlarmAdapter.notifyDataSetChanged();
        }
        if (requestCode == REQUEST_DELETE_INTENT && resultCode == (DeleteAlarmActivity.RESULT_DELETE_INTENT)) {
            Bundle bundle = data.getExtras();
            ArrayList<Integer> mPositionDeleted = bundle.getIntegerArrayList(DeleteAlarmActivity.INTENT_DELETE);
            for (int in : mPositionDeleted) {
                mAlarms.remove(in);
            }
            mRecyclerViewAlarmAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public ArrayList<Alarm> initData() {
        ArrayList<Alarm> alarmArrayList = mDatabaseAlarm.getData();
        return alarmArrayList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Intent intent = new Intent(MainActivity.this, DeleteAlarmActivity.class);
                startActivityForResult(intent, REQUEST_DELETE_INTENT);
                break;
            case R.id.action_clock:
                intent = new Intent(MainActivity.this, ShowClockActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemclick(int position) {
        Alarm alarm = mAlarms.get(position);
        Intent intent = new Intent(MainActivity.this, EditAlarmActivity.class);
        intent.putExtra(INTENT_KEY, alarm);
        intent.putExtra(INTENT_KEY_POSITION, position);
        startActivityForResult(intent, REQUEST_EDIT_INTENT);
    }

    @Override
    public void onItemChecked(int position, boolean check) {
    }

}
