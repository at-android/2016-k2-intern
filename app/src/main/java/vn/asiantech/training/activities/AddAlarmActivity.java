package vn.asiantech.training.activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

import vn.asiantech.training.R;
import vn.asiantech.training.databases.DatabaseAlarm;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 07/12/2016.
 */

public class AddAlarmActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox mChkActive;
    private RelativeLayout mRlSetTime;
    private RelativeLayout mRlRepeat;
    private String mDayRepeatChar = "";
    private String mDayRepeatInt = "";
    private TextView mTvSetTime;
    private Calendar mcurrentTime;
    private String mHourSelect = "";
    private String mMinSelect = "";
    public final static String INTENT_ADD = "intentAdd";
    private DatabaseAlarm mDatabaseAlarm;
    public final static int RESULT_CODE_ADD = 10000;
    private TextView mTvRepeatDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        mChkActive = (CheckBox) findViewById(R.id.tvCheck);
        mRlSetTime = (RelativeLayout) findViewById(R.id.layoutSetTime);
        mRlRepeat = (RelativeLayout) findViewById(R.id.layoutRepeat);
        mTvSetTime = (TextView) findViewById(R.id.tvSetTime);
        mTvRepeatDay = (TextView) findViewById(R.id.tvRepeatDay);

        mcurrentTime = Calendar.getInstance();
        mHourSelect = String.valueOf(mcurrentTime.get(Calendar.HOUR_OF_DAY));
        mMinSelect = String.valueOf(mcurrentTime.get(Calendar.MINUTE));

        mRlRepeat.setOnClickListener(this);
        mRlSetTime.setOnClickListener(this);

        mDatabaseAlarm = new DatabaseAlarm(this);
        mDatabaseAlarm.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_alarm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                boolean isStatus = false;
                isStatus = mChkActive.isChecked();
                if ("".equals(mDayRepeatChar)) {
                    mDayRepeatInt = getResources().getString(R.string.string_repeat_day);
                    mDayRepeatChar = getResources().getString(R.string.string_repeat_day_char);;
                }
                mDatabaseAlarm.createData(mHourSelect, mMinSelect, mDayRepeatInt, mDayRepeatChar, String.valueOf(isStatus));
                broadcastIntent(new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, isStatus, mDayRepeatChar));

                Intent intent = new Intent();
                intent.putExtra(INTENT_ADD, new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, isStatus, mDayRepeatChar));

                setResult(RESULT_CODE_ADD, intent);
                finish();
                break;
            case R.id.action_delete:
                showDialogDelete();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialogRepeat() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(getResources().getString(R.string.dialog_title_repeat));
        dialog.setContentView(R.layout.dialog_time_repeat);
        final CheckBox mCbMonday = (CheckBox) dialog.findViewById(R.id.cbMonday);
        final CheckBox mCbTuesday = (CheckBox) dialog.findViewById(R.id.cbTuesday);
        final CheckBox mCbWebnesday = (CheckBox) dialog.findViewById(R.id.cbWednesday);
        final CheckBox mCbThursday = (CheckBox) dialog.findViewById(R.id.cbThursday);
        final CheckBox mCbFriday = (CheckBox) dialog.findViewById(R.id.cbFriday);
        final CheckBox mCbSaturday = (CheckBox) dialog.findViewById(R.id.cbSaturday);
        final CheckBox mCbSunday = (CheckBox) dialog.findViewById(R.id.cbSunday);
        Button mBtnOk = (Button) dialog.findViewById(R.id.btnOk);

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCbMonday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.monday_char);
                    mDayRepeatInt += getResources().getString(R.string.monday_int);
                }
                if (mCbTuesday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.tuesday_char);
                    mDayRepeatInt += getResources().getString(R.string.tuesday_int);
                }
                if (mCbWebnesday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.wed_char);
                    mDayRepeatInt += getResources().getString(R.string.wed_int);
                }
                if (mCbThursday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.thursday_char);
                    mDayRepeatInt += getResources().getString(R.string.thursday_int);
                }
                if (mCbFriday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.friday_char);
                    mDayRepeatInt += getResources().getString(R.string.friday_int);
                }
                if (mCbSaturday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.saturday_char);
                    mDayRepeatInt += getResources().getString(R.string.saturday_int);
                }
                if (mCbSunday.isChecked()) {
                    mDayRepeatChar += getResources().getString(R.string.sunday_char);
                    mDayRepeatInt += getResources().getString(R.string.sunday_int);
                }

                mTvRepeatDay.setText(mDayRepeatChar);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutSetTime:
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTvSetTime.setText(selectedHour + ":" + selectedMinute);
                        mHourSelect = String.valueOf(selectedHour);
                        mMinSelect = String.valueOf(selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle(getResources().getString(R.string.dialog_title_settime));
                mTimePicker.show();
                break;
            case R.id.layoutRepeat:
                showDialogRepeat();
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
                        onBackPressed();
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

    public void broadcastIntent(Alarm alarm) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.BROADCAST_KEY, alarm);
        intent.putExtras(bundle);
        intent.setAction(getString(R.string.broadcast_add_intent));
        sendBroadcast(intent);
    }
}