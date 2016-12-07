package vn.asiantech.training.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import vn.asiantech.training.Adapter.AlarmAdapter;
import vn.asiantech.training.Database.DatabaseHandler;
import vn.asiantech.training.Object.AlarmObj;
import vn.asiantech.training.R;
import vn.asiantech.training.Service.AlarmClockService;

public class MainActivity extends AppCompatActivity implements AlarmAdapter.onSetDetete {
    public ArrayList<AlarmObj> sAlarmArray = new ArrayList<>();
    private RecyclerView mRvListAlarm;
    private AlarmAdapter mAdapter;
    private String mDayArr[] = {
            "All",
            "Sunday",
            "MonDay",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"};
    private DatabaseHandler db;
    private SQLiteDatabase sqlData;
    private Context context;
    private Intent newService;
    private TextView mTvShowDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        context = this;
        db = new DatabaseHandler(context);
        sqlData = db.getReadableDatabase();
        String query = "select * from AlarmManager";
        Cursor cursor = sqlData.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minute = cursor.getInt(3);
            String dayofweek = cursor.getString(4);
            sAlarmArray.add(new AlarmObj(title, hour, minute, dayofweek));
        }
        newService = new Intent(this, AlarmClockService.class);
        newService.putExtra("a", sAlarmArray);
        stopService(newService);
        startService(newService);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        mRvListAlarm = (RecyclerView) findViewById(R.id.rvListAlarm);
        mRvListAlarm.setHasFixedSize(false);
        mRvListAlarm.setLayoutManager(llm);
        mAdapter = new AlarmAdapter(MainActivity.this, sAlarmArray);
        mRvListAlarm.setAdapter(mAdapter);
        ImageButton imgBtnAdd = (ImageButton) findViewById(R.id.imgBtnAdd);
        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Insert();
            }
        });

    }

    public void Insert() {
        final Dialog addNewContact = new Dialog(MainActivity.this);
        addNewContact.setContentView(R.layout.dialog_add_new_alarm);
        addNewContact.setTitle("Add new alarm");
        final EditText edTitle = (EditText) addNewContact.findViewById(R.id.etTitle);
        final TimePicker tpTime = (TimePicker) addNewContact.findViewById(R.id.tp_time);
        Button btnSave = (Button) addNewContact.findViewById(R.id.btn_save);
        mTvShowDay = (TextView) addNewContact.findViewById(R.id.tv_day);
        Button btnChoose = (Button) addNewContact.findViewById(R.id.btn_choose_day);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDayOfWeek();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = tpTime.getCurrentHour();
                int minutes = tpTime.getCurrentMinute();
                sAlarmArray.add(new AlarmObj(edTitle.getText().toString(), hours, minutes, mTvShowDay.getText().toString()));
                sqlData = db.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", edTitle.getText().toString());
                contentValues.put("hour", hours);
                contentValues.put("minute", minutes);
                contentValues.put("dayofweek", mTvShowDay.getText().toString());
                sqlData.insert("AlarmManager", null, contentValues);
                sqlData.close();
                Intent intent = new Intent();
                intent.setAction("com.alarm.CUSTOM_INTENT");
                intent.putExtra("a", sAlarmArray);
                sendBroadcast(intent);
                stopService(newService);
                startService(newService);
                resetAdapter();
                addNewContact.dismiss();
            }
        });
        addNewContact.show();
    }

    public void resetAdapter() {
        mAdapter = new AlarmAdapter(MainActivity.this, sAlarmArray);
        mRvListAlarm.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void dialogDayOfWeek() {
        final ArrayList seletedItems = new ArrayList();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select Day")
                .setMultiChoiceItems(mDayArr, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        //seletedItems.clear();
                        mTvShowDay.setText("");
                        if (isChecked) {
                            seletedItems.add(indexSelected);
                            if (indexSelected == 0) {
                                for (int i = 0; i < mDayArr.length; i++) {
                                    ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                                }
                            }
                            Log.d("a", mDayArr[indexSelected] + "");
                        } else {
                            seletedItems.remove(Integer.valueOf(indexSelected));
                            if (indexSelected == 0) {
                                for (int i = 0; i < mDayArr.length; i++) {
                                    ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                                }
                            } else {
                                ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                                seletedItems.clear();
                            }
                        }

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        for (int i = 0; i < seletedItems.size(); i++) {
                            mTvShowDay.setText(mTvShowDay.getText().toString() + " " + seletedItems.get(i));
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void deletePosition(int position) {
        sqlData = db.getWritableDatabase();
        sqlData.delete("AlarmManager", "id =" + (position + 1), null);
        sqlData.close();
        sAlarmArray.remove(position);
        Intent intent = new Intent();
        intent.setAction("com.alarm.CUSTOM_INTENT");
        intent.putExtra("a", sAlarmArray);
        sendBroadcast(intent);
        stopService(newService);
        startService(newService);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void editPosition(int positon) {
        editData(positon);
    }

    public void editData(final int pos) {
        final Dialog addNewContact = new Dialog(MainActivity.this);
        addNewContact.setContentView(R.layout.dialog_add_new_alarm);
        addNewContact.setTitle("Edit alarm");
        final EditText edTitle = (EditText) addNewContact.findViewById(R.id.etTitle);
        final TimePicker tpTime = (TimePicker) addNewContact.findViewById(R.id.tp_time);
        Button btnSave = (Button) addNewContact.findViewById(R.id.btn_save);
        mTvShowDay = (TextView) addNewContact.findViewById(R.id.tv_day);
        Button btnChoose = (Button) addNewContact.findViewById(R.id.btn_choose_day);
        edTitle.setText(sAlarmArray.get(pos).getTitle());
        mTvShowDay.setText(sAlarmArray.get(pos).getDayofweek());
        tpTime.setHour(sAlarmArray.get(pos).getHour());
        tpTime.setMinute(sAlarmArray.get(pos).getMinute());
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDayOfWeek();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = tpTime.getCurrentHour();
                int minutes = tpTime.getCurrentMinute();
                sAlarmArray.set(pos, new AlarmObj(edTitle.getText().toString(), hours, minutes, mTvShowDay.getText().toString()));
                sqlData = db.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", edTitle.getText().toString());
                contentValues.put("hour", hours);
                contentValues.put("minute", minutes);
                contentValues.put("dayofweek", mTvShowDay.getText().toString());
                sqlData.update("AlarmManager", contentValues, "id =" + (pos + 1), null);
                sqlData.close();
                Intent intent = new Intent();
                intent.setAction("com.alarm.CUSTOM_INTENT");
                intent.putExtra("a", sAlarmArray);
                sendBroadcast(intent);
                stopService(newService);
                startService(newService);
                resetAdapter();
                addNewContact.dismiss();
            }
        });
        addNewContact.show();
    }
}
