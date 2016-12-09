package vn.asiantech.training.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapter.AlarmAdapter;
import vn.asiantech.training.database.DatabaseHandler;
import vn.asiantech.training.dialog.DialogUtils;
import vn.asiantech.training.object.AlarmObj;
import vn.asiantech.training.service.AlarmClockService;

public class MainActivity extends AppCompatActivity implements AlarmAdapter.onSetDetete, DialogUtils.doSomeThingFormDialog {
    //MainActivity using show list alarm.
    public static final String DATABASE_NAME = "AlarmManager";
    public static final String LIST_ARRAY_NAME = "a";
    public static final String TITLE = "title";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String DAY_OF_WEEK = "dayofweek";
    public List<AlarmObj> alarmArrays = new ArrayList<>();
    private RecyclerView mRvListAlarm;
    private AlarmAdapter mAdapter;

    private DatabaseHandler mDb;
    private SQLiteDatabase mSqlData;
    private Intent mNewService;
    private TextView mTvShowDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mDb = new DatabaseHandler(this);
        mSqlData = mDb.getReadableDatabase();
        String query = "select * from AlarmManager";
        Cursor cursor = mSqlData.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minute = cursor.getInt(3);
            String dayofweek = cursor.getString(4);
            alarmArrays.add(new AlarmObj(title, hour, minute, dayofweek));
        }
        mNewService = new Intent(this, AlarmClockService.class);
        mNewService.putParcelableArrayListExtra(LIST_ARRAY_NAME, (ArrayList<? extends Parcelable>) alarmArrays);
        stopService(mNewService);
        startService(mNewService);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRvListAlarm = (RecyclerView) findViewById(R.id.recycleViewListAlarm);
        mRvListAlarm.setHasFixedSize(false);
        mRvListAlarm.setLayoutManager(linearLayoutManager);
        mAdapter = new AlarmAdapter(MainActivity.this, alarmArrays);
        mRvListAlarm.setAdapter(mAdapter);
        ImageButton imgBtnAdd = (ImageButton) findViewById(R.id.imgBtnAdd);
        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.dialogAddNewAlarm(MainActivity.this);
            }
        });

    }

    public void resetAdapter() {
        mAdapter = new AlarmAdapter(MainActivity.this, alarmArrays);
        mRvListAlarm.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deletePosition(int position) {
        mSqlData = mDb.getWritableDatabase();
        mSqlData.delete("AlarmManager", "id =" + (position + 1), null);
        mSqlData.close();
        alarmArrays.remove(position);
        Intent intent = new Intent();
        intent.setAction("com.alarm.CUSTOM_INTENT");
        intent.putParcelableArrayListExtra(LIST_ARRAY_NAME, (ArrayList<? extends Parcelable>) alarmArrays);
        sendBroadcast(intent);
        stopService(mNewService);
        startService(mNewService);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void editPosition(int positon) {
        editData(positon);
    }

    public void editData(final int pos) {
        DialogUtils.dialogEditAlarm(MainActivity.this, pos, alarmArrays.get(pos));
    }

    @Override
    public void addDataFormDialogToActivity(AlarmObj alarmObj) {
        alarmArrays.add(alarmObj);
        resetAdapter();
    }

    @Override
    public void sendBroadCastFormDialogToActivity() {
        Intent intent = new Intent();
        intent.setAction(String.valueOf(R.string.action_custom_intent));
        intent.putParcelableArrayListExtra(LIST_ARRAY_NAME, (ArrayList<? extends Parcelable>) alarmArrays);
        sendBroadcast(intent);
    }

    @Override
    public void setDataFormDialogToActivity(int pos, AlarmObj alarmObj) {
        alarmArrays.set(pos, alarmObj);
        resetAdapter();
    }
}
