package vn.asiantech.training;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecycleViewCustom.onSetDetete {
    public ArrayList<AlarmObj> sAlarmArray = new ArrayList<>();
    private RecyclerView mRvListAlarm;
    private int selection;
    private RecycleViewCustom mAdapter;
    private String mDayArr[] = {
            "All",
            "Sunday",
            "MonDay",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday"
            , "Saturday"};
    private DatabaseHandler db;
    private SQLiteDatabase sqlData;
    private Context context;
    private Intent newService;

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
            int dayofweek = cursor.getInt(4);
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
        mAdapter = new RecycleViewCustom(MainActivity.this, sAlarmArray);
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
        final Dialog addNewContact = new Dialog(context);
        addNewContact.setContentView(R.layout.dialog_add_new_alarm);
        addNewContact.setTitle("Add new alarm");
        selection = 0;
        final EditText edTitle = (EditText) addNewContact.findViewById(R.id.etTitle);
        final EditText edHour = (EditText) addNewContact.findViewById(R.id.etHour);
        final EditText edMinute = (EditText) addNewContact.findViewById(R.id.etMinute);
        Button btnSave = (Button) addNewContact.findViewById(R.id.btn_save);
        Spinner spinner = (Spinner) addNewContact.findViewById(R.id.spDay);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        mDayArr
                );
        mAdapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = Integer.parseInt(edHour.getText().toString());
                int minutes = Integer.parseInt(edMinute.getText().toString());
                if (hours > 24 || minutes > 60) {
                    Toast.makeText(context, "Thời gian hông hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    sAlarmArray.add(new AlarmObj(edTitle.getText().toString(), Integer.parseInt(edHour.getText().toString()), Integer.parseInt(edMinute.getText().toString()), selection));
                    sqlData = db.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("title", edTitle.getText().toString());
                    contentValues.put("hour", Integer.parseInt(edHour.getText().toString()));
                    contentValues.put("minute", Integer.parseInt(edMinute.getText().toString()));
                    contentValues.put("dayofweek", selection);
                    sqlData.insert("AlarmManager", null, contentValues);
                    sqlData.close();
                    Intent intent = new Intent();
                    intent.setAction("com.alarm.CUSTOM_INTENT");
                    intent.putExtra("a", sAlarmArray);
                    sendBroadcast(intent);
                    stopService(newService);
                    startService(newService);
                    mAdapter.notifyDataSetChanged();
                    addNewContact.dismiss();
                }
            }
        });
        addNewContact.show();
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
}
