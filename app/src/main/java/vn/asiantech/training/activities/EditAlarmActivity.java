package vn.asiantech.training.activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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
 * Created by phuong on 08/12/2016.
 */

public class EditAlarmActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String INTENT_EDIT = "intentEdit";
    public static final int RESULT_CODE_EDIT = 20000;
    private TextView mTvSetTime;
    private TextView mTvRepeatDay;
    private CheckBox mChkActive;
    private String mHourSelect = "";
    private String mMinSelect = "";
    private Calendar mcurrentTime;
    private String mDayRepeat = "";
    private String mDayRepeatInt = "";
    private Alarm mAlarm;
    private DatabaseAlarm mDatabaseAlarm;
    private RelativeLayout mRlSetTime;
    private RelativeLayout mRlRepeat;
    private int mPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_new_alarm);
        mRlSetTime = (RelativeLayout) findViewById(R.id.layoutSetTime);
        mRlRepeat = (RelativeLayout) findViewById(R.id.layoutRepeat);
        mTvSetTime = (TextView) findViewById(R.id.tvSetTime);
        mChkActive = (CheckBox) findViewById(R.id.tvCheck);
        mTvRepeatDay = (TextView) findViewById(R.id.tvRepeatDay);

        mAlarm = getIntent().getParcelableExtra(MainActivity.INTENT_KEY);
        mPosition = getIntent().getIntExtra(MainActivity.INTENT_KEY_POSITION, 0);
        mTvSetTime.setText(mAlarm.getHour() + ":" + mAlarm.getMin());
        mTvRepeatDay.setText(mAlarm.getRepeartChar());
        mChkActive.setChecked(mAlarm.isStatus());
        mDayRepeatInt = mAlarm.getRepeart();
        mDayRepeat = mAlarm.getRepeartChar();

        mDatabaseAlarm = new DatabaseAlarm(this);
        mDatabaseAlarm.open();

        mRlSetTime.setOnClickListener(this);
        mRlRepeat.setOnClickListener(this);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(R.string.edit_alarm);
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
                    mHourSelect = mAlarm.getHour();
                if (("").equals(mMinSelect))
                    mMinSelect = mAlarm.getMin();

                //get checkbox
                boolean status = false;
                status = mChkActive.isChecked();
                //edit
                mDatabaseAlarm.editData(mAlarm.getId(), mHourSelect, mMinSelect, mDayRepeatInt, mDayRepeat, String.valueOf(status));

                Intent intent = new Intent();
                intent.putExtra(INTENT_EDIT, new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, status, mDayRepeat));
                intent.putExtra(MainActivity.INTENT_KEY_POSITION, mPosition);
                setResult(RESULT_CODE_EDIT, intent);

                broadcastIntent(new Alarm(mHourSelect, mMinSelect, mDayRepeatInt, status, mDayRepeat));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutSetTime:
                mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

    public void showDialogRepeat() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(getResources().getString(R.string.dialog_title_repeat));
        dialog.setContentView(R.layout.dialog_time_repeat);
        final CheckBox mChkMonday = (CheckBox) dialog.findViewById(R.id.cbMonday);
        final CheckBox mChkTuesday = (CheckBox) dialog.findViewById(R.id.cbTuesday);
        final CheckBox mChkWebnesday = (CheckBox) dialog.findViewById(R.id.cbWednesday);
        final CheckBox mChkThursday = (CheckBox) dialog.findViewById(R.id.cbThursday);
        final CheckBox mChkFriday = (CheckBox) dialog.findViewById(R.id.cbFriday);
        final CheckBox mChkSaturday = (CheckBox) dialog.findViewById(R.id.cbSaturday);
        final CheckBox mChkSunday = (CheckBox) dialog.findViewById(R.id.cbSunday);
        Button mBtnOk = (Button) dialog.findViewById(R.id.btnOk);

        if (getResources().getString(R.string.string_repeat_day_char).equals(mDayRepeat)) {
            mChkMonday.setChecked(true);
            mChkTuesday.setChecked(true);
            mChkWebnesday.setChecked(true);
            mChkThursday.setChecked(true);
            mChkFriday.setChecked(true);
            mChkSaturday.setChecked(true);
            mChkSunday.setChecked(true);
        } else {
            String[] days = mDayRepeat.split("[,]");
            for (int i = 0; i < days.length; i++) {
                if (days[i].equals(mChkMonday.getText().toString())) {
                    mChkMonday.setChecked(true);
                }
                if (days[i].equals(mChkTuesday.getText().toString())) {
                    mChkTuesday.setChecked(true);
                }
                if (days[i].equals(mChkWebnesday.getText().toString())) {
                    mChkWebnesday.setChecked(true);
                }
                if (days[i].equals(mChkThursday.getText().toString())) {
                    mChkThursday.setChecked(true);
                }
                if (days[i].equals(mChkFriday.getText().toString())) {
                    mChkFriday.setChecked(true);
                }
                if (days[i].equals(mChkSaturday.getText().toString())) {
                    mChkSaturday.setChecked(true);
                }
                if (days[i].equals(mChkSunday.getText().toString())) {
                    mChkSunday.setChecked(true);
                }
            }
        }

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDayRepeat = "";
                mDayRepeatInt = "";

                if (mChkMonday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.monday_char);
                    mDayRepeatInt += getResources().getString(R.string.monday_int);
                }
                if (mChkTuesday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.tuesday_char);
                    mDayRepeatInt += getResources().getString(R.string.tuesday_int);
                }
                if (mChkWebnesday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.wed_char);
                    mDayRepeatInt += getResources().getString(R.string.wed_int);
                }
                if (mChkThursday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.thursday_char);
                    mDayRepeatInt += getResources().getString(R.string.thursday_int);
                }
                if (mChkFriday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.friday_char);
                    mDayRepeatInt += getResources().getString(R.string.friday_int);
                }
                if (mChkSaturday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.saturday_char);
                    mDayRepeatInt += getResources().getString(R.string.saturday_int);
                }
                if (mChkSunday.isChecked()) {
                    mDayRepeat += getResources().getString(R.string.sunday_char);
                    mDayRepeatInt += getResources().getString(R.string.sunday_int);
                }

                mTvRepeatDay.setText(mDayRepeat);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void broadcastIntent(Alarm alarm) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.BROADCAST_KEY, alarm);
        intent.putExtras(bundle);
        intent.putExtra(MainActivity.INTENT_KEY_POSITION, mPosition);
        intent.setAction(getString(R.string.broadcast_edit_intent));
        sendBroadcast(intent);
    }
}
