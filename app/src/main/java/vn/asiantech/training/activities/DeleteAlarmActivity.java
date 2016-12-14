package vn.asiantech.training.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.adapters.RecyclerViewAlarmDeleteAdapter;
import vn.asiantech.training.databases.DatabaseAlarm;
import vn.asiantech.training.listeners.RecyclerItemListener;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 07/12/2016.
 */

public class DeleteAlarmActivity extends AppCompatActivity implements View.OnClickListener, RecyclerItemListener {
    public static final String INTENT_DELETE = "intent_delete";
    public static final int RESULT_CODE_DELETE = 20000;
    private RecyclerView mRecyclerView;
    private RecyclerViewAlarmDeleteAdapter mRecyclerViewAlarmAdapter;
    private List<Alarm> mAlarms;
    private DatabaseAlarm mDatabaseAlarm;
    private List<Alarm> mAlarmsSelecteds = new ArrayList<>();
    private Button mBtnDelete;
    private Button mBtnCancel;
    private List<Integer> mPositions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_alarm);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleViewDelete);
        mBtnDelete = (Button) findViewById(R.id.btnDelete);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);

        mDatabaseAlarm = new DatabaseAlarm(this);
        mDatabaseAlarm.open();

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(R.string.delete_alarm);


        mAlarms = new ArrayList<>();
        mAlarms = initData();
        mRecyclerViewAlarmAdapter = new RecyclerViewAlarmDeleteAdapter(mAlarms, this, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAlarmAdapter);

        mBtnDelete.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mPositions.size() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList(INTENT_DELETE, (ArrayList<Integer>) mPositions);
                    intent.putExtras(bundle);
                    setResult(RESULT_CODE_DELETE, intent);
                    finish();
                } else
                    onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Alarm> initData() {
        List<Alarm> alarmlist = mDatabaseAlarm.getData();
        return alarmlist;
    }

    @Override
    public void onItemclick(int position) {

    }

    @Override
    public void onItemChecked(int position, boolean status) {
        Alarm alarm = mAlarms.get(position);
        if (status) {
            mPositions.add(position);
            mAlarmsSelecteds.add(alarm);
        } else {
            mPositions.remove(position);
            mAlarmsSelecteds.remove(alarm);
        }
        mBtnDelete.setText(getString(R.string.delete_alarm_btn_delete) + "(" + mAlarmsSelecteds.size() + ")");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                showDialogDelete();
                break;
            case R.id.btnCancel:
                if (mPositions.size() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList(INTENT_DELETE, (ArrayList<Integer>) mPositions);
                    intent.putExtras(bundle);
                    setResult(RESULT_CODE_DELETE, intent);
                    finish();
                } else {
                    onBackPressed();
                }
                break;
        }
    }

    public void showDialogDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.dialog_title_delete));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.dialog_message_delete))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.dialog_btn_yes_delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (Alarm alarm : mAlarmsSelecteds) {
                            mDatabaseAlarm.deleteData(alarm.getId());
                            mAlarms.remove(alarm);
                            if (mPositions.size() > 0)
                                broadcastIntent(mPositions);
                        }
                        mRecyclerViewAlarmAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.dialog_btn_no_delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void broadcastIntent(List<Integer> positions) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(MainActivity.BROADCAST_KEY, (ArrayList<Integer>) positions);
        intent.putExtras(bundle);
        intent.setAction(getString(R.string.broadcast_delete_intent));
        sendBroadcast(intent);
    }
}
