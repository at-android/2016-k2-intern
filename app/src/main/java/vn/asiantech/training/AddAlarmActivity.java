package vn.asiantech.training;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import vn.asiantech.training.databases.DatabaseAlarm;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 01/12/2016.
 */

public class AddAlarmActivity extends AppCompatActivity implements View.OnClickListener {
    public static String INTENT_ADD = "intentAdd";
    private TextView mTvSetTime;
    private RelativeLayout mRelativeLayoutSetTime;
    private RelativeLayout mRelativeLayoutRepeat;
    private RelativeLayout mRelativeLayoutRingTon;
    private RelativeLayout mRelativeLayoutVibrate;
    private String mHourSelect = "";
    private String mMinSelect = "";
    private Calendar mcurrentTime = Calendar.getInstance();
    private String mDayRepeat = "";
    private String mDayRepeatInt = "";
    private TextView mTvRepeatDays;
    private RadioButton mRdCheck;
    private DatabaseAlarm mDatabaseAlarm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_alarm);
        mRelativeLayoutSetTime = (RelativeLayout) findViewById(R.id.layoutSetTime);
        mRelativeLayoutRepeat = (RelativeLayout) findViewById(R.id.layoutRepeat);
        mRelativeLayoutRingTon = (RelativeLayout) findViewById(R.id.layoutRingTone);
        mRelativeLayoutVibrate = (RelativeLayout) findViewById(R.id.layoutVibrate);
        mTvSetTime = (TextView) findViewById(R.id.tvSetTime);
        mTvRepeatDays = (TextView) findViewById(R.id.tvRepeatDay);
        mRdCheck = (RadioButton) findViewById(R.id.tvCheck);
        mTvSetTime.setText(mcurrentTime.get(Calendar.HOUR_OF_DAY) + ":" + mcurrentTime.get(Calendar.MINUTE));

        mDatabaseAlarm = new DatabaseAlarm(this);
        mDatabaseAlarm.open();

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(R.string.add_alarm);

        mRelativeLayoutSetTime.setOnClickListener(this);
        mRelativeLayoutRepeat.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_alarm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                if (("").equals(mHourSelect))
                    mHourSelect = String.valueOf(mcurrentTime.get(Calendar.HOUR_OF_DAY));
                if (("").equals(mMinSelect))
                    mMinSelect = String.valueOf(mcurrentTime.get(Calendar.MINUTE));

                //get checkbox
                boolean status = false;
                status = mRdCheck.isChecked();
                if ("".equals(mDayRepeat)) {
                    mDayRepeatInt = "1,2,3,4,5,6,7";
                    mDayRepeat = "Every Day";
                }
                mDatabaseAlarm.createData(mHourSelect, mMinSelect, mDayRepeatInt, mDayRepeat, String.valueOf(status));
                broadcastIntent(new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, status, mDayRepeat));

                Intent intent = new Intent();
                intent.putExtra(INTENT_ADD, new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, status, mDayRepeat));

                setResult(10000, intent);
                finish();
                break;
            case R.id.action_delete:
                showDialogDelete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutSetTime:
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                Log.d("abc 1 ", hour + " : " + minute);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTvSetTime.setText(selectedHour + ":" + selectedMinute);
                        mHourSelect = String.valueOf(selectedHour);
                        mMinSelect = String.valueOf(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Set Time");
                mTimePicker.show();
                break;
            case R.id.layoutRepeat:
                showDialogRepeat();
                break;
            case R.id.cbMonday:
                mDayRepeat += "Monday,";
                mDayRepeatInt += "2,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbTuesday:
                mDayRepeat += "Tuesday,";
                mDayRepeatInt += "3,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbWednesday:
                mDayRepeat += "Wednesday,";
                mDayRepeatInt += "4,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbThursday:
                mDayRepeat += "Thursday,";
                mDayRepeatInt += "5,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbFriday:
                mDayRepeat += "Friday,";
                mDayRepeatInt += "6,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbSaturday:
                mDayRepeat += "Saturday,";
                mDayRepeatInt += "7,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
            case R.id.cbSunday:
                mDayRepeat += "Sunday,";
                mDayRepeatInt += "1,";
                mTvRepeatDays.setText(mDayRepeat);
                break;
        }
    }

    public void showDialogRepeat() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Repeat");
        dialog.setContentView(R.layout.dialog_time_repeat);
        CheckBox mCbMonday = (CheckBox) dialog.findViewById(R.id.cbMonday);
        CheckBox mCbTuesday = (CheckBox) dialog.findViewById(R.id.cbTuesday);
        CheckBox mCbWebnesday = (CheckBox) dialog.findViewById(R.id.cbWednesday);
        CheckBox mCbThursday = (CheckBox) dialog.findViewById(R.id.cbThursday);
        CheckBox mCbFriday = (CheckBox) dialog.findViewById(R.id.cbFriday);
        CheckBox mCbSaturday = (CheckBox) dialog.findViewById(R.id.cbSaturday);
        CheckBox mCbSunday = (CheckBox) dialog.findViewById(R.id.cbSunday);

        if (!("").equals(mDayRepeat)) {
            String[] days = mDayRepeat.split("[,]");
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals(mCbMonday.getText().toString()))
                    mCbMonday.setChecked(true);
                if (days[i].equals(mCbTuesday.getText().toString()))
                    mCbTuesday.setChecked(true);
                if (days[i].equals(mCbWebnesday.getText().toString()))
                    mCbWebnesday.setChecked(true);
                if (days[i].equals(mCbThursday.getText().toString()))
                    mCbThursday.setChecked(true);
                if (days[i].equals(mCbFriday.getText().toString()))
                    mCbFriday.setChecked(true);
                if (days[i].equals(mCbSaturday.getText().toString()))
                    mCbSaturday.setChecked(true);
                if (days[i].equals(mCbSunday.getText().toString()))
                    mCbSunday.setChecked(true);
            }
        }

        mCbMonday.setOnClickListener(this);
        mCbTuesday.setOnClickListener(this);
        mCbWebnesday.setOnClickListener(this);
        mCbThursday.setOnClickListener(this);
        mCbFriday.setOnClickListener(this);
        mCbSaturday.setOnClickListener(this);
        mCbSunday.setOnClickListener(this);

        dialog.show();
    }

    public void showDialogDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Are your sure delete your alarm?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseAlarm.close();
    }

    public void broadcastIntent(Alarm alarm) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.BROADCAST_KEY, alarm);
        intent.putExtras(bundle);
        intent.setAction("com.phuong.ADD_INTENT");
        sendBroadcast(intent);
    }
}
