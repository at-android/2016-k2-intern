package vn.asiantech.training.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import vn.asiantech.training.R;
import vn.asiantech.training.database.DatabaseHandler;
import vn.asiantech.training.object.AlarmObj;
import vn.asiantech.training.service.AlarmClockService;

import static vn.asiantech.training.activities.MainActivity.DATABASE_NAME;
import static vn.asiantech.training.activities.MainActivity.DAY_OF_WEEK;
import static vn.asiantech.training.activities.MainActivity.HOUR;
import static vn.asiantech.training.activities.MainActivity.MINUTE;
import static vn.asiantech.training.activities.MainActivity.TITLE;

/**
 * Created by MaiManhDuy on 12/8/2016.
 */

public class DialogUtils {
    private static final String mDayArr[] = {
            "All",
            "Sunday",
            "MonDay",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"};

    public static void dialogCancleRingtone(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle(R.string.title_dialog_cancle_ringtone)
                .setMessage(R.string.message_dialog_cancle_ringtone)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(String.valueOf(R.string.action_stop_music));
                        activity.sendBroadcast(intent);
                        activity.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void dialogAddNewAlarm(final Activity activity) {
        final doSomeThingFormDialog mListener = (doSomeThingFormDialog) activity;
        final Dialog addNewContact = new Dialog(activity);
        addNewContact.setContentView(R.layout.dialog_add_new_alarm);
        addNewContact.setTitle(R.string.title_dialog_add_new_alarm);
        final EditText edTitle = (EditText) addNewContact.findViewById(R.id.edtTitle);
        final TimePicker tpTime = (TimePicker) addNewContact.findViewById(R.id.timePickerChooseTime);
        Button btnSave = (Button) addNewContact.findViewById(R.id.btn_save);
        final TextView mTvShowDay = (TextView) addNewContact.findViewById(R.id.tvDay);
        Button btnChoose = (Button) addNewContact.findViewById(R.id.btnChooseDay);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList seletedItems = new ArrayList();
                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setTitle(R.string.title_dialog_select_day)
                        .setMultiChoiceItems(mDayArr, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                mTvShowDay.setText("");
                                if (isChecked) {
                                    seletedItems.add(indexSelected);
                                    if (indexSelected == 0) {
                                        for (int i = 0, length = mDayArr.length; i < length; i++) {
                                            ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                                        }
                                    }
                                } else {
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                    if (indexSelected == 0) {
                                        for (int i = 0, length = mDayArr.length; i < length; i++) {
                                            ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                                        }
                                    } else {
                                        ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                                        seletedItems.clear();
                                    }
                                }

                            }
                        }).setPositiveButton(R.string.btn_ok_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                for (int i = 0; i < seletedItems.size(); i++) {
                                    mTvShowDay.setText(mTvShowDay.getText().toString() + " " + seletedItems.get(i));
                                }
                            }
                        }).setNegativeButton(R.string.btn_cancle_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).create();
                dialog.show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = tpTime.getCurrentHour();
                int minutes = tpTime.getCurrentMinute();
                Intent mNewService = new Intent(activity, AlarmClockService.class);
                mListener.addDataFormDialogToActivity(new AlarmObj(edTitle.getText().toString(), hours, minutes, mTvShowDay.getText().toString()));
                DatabaseHandler mDb = new DatabaseHandler(activity);
                SQLiteDatabase mSqlData = mDb.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(TITLE, edTitle.getText().toString());
                contentValues.put(HOUR, hours);
                contentValues.put(MINUTE, minutes);
                contentValues.put(DAY_OF_WEEK, mTvShowDay.getText().toString());
                mSqlData.insert(DATABASE_NAME, null, contentValues);
                mSqlData.close();
                mListener.sendBroadCastFormDialogToActivity();
                activity.stopService(mNewService);
                activity.startService(mNewService);
                addNewContact.dismiss();
            }
        });
        addNewContact.show();
    }

    public static void dialogEditAlarm(final Activity activity, final int pos, final AlarmObj alarmObj) {
        final Dialog addNewContact = new Dialog(activity);
        final doSomeThingFormDialog mListener = (doSomeThingFormDialog) activity;
        addNewContact.setContentView(R.layout.dialog_add_new_alarm);
        addNewContact.setTitle(R.string.title_dialog_edit_alarm);
        final EditText edTitle = (EditText) addNewContact.findViewById(R.id.edtTitle);
        final TimePicker tpTime = (TimePicker) addNewContact.findViewById(R.id.timePickerChooseTime);
        Button btnSave = (Button) addNewContact.findViewById(R.id.btn_save);
        final TextView mTvShowDay = (TextView) addNewContact.findViewById(R.id.tvDay);
        Button btnChoose = (Button) addNewContact.findViewById(R.id.btnChooseDay);
        edTitle.setText(alarmObj.getTitle());
        mTvShowDay.setText(alarmObj.getDayofweek());
        tpTime.setHour(alarmObj.getHour());
        tpTime.setMinute(alarmObj.getMinute());
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList seletedItems = new ArrayList();
                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setTitle(R.string.title_dialog_select_day)
                        .setMultiChoiceItems(mDayArr, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                mTvShowDay.setText("");
                                if (isChecked) {
                                    seletedItems.add(indexSelected);
                                    if (indexSelected == 0) {
                                        for (int i = 0, length = mDayArr.length; i < length; i++) {
                                            ((AlertDialog) dialog).getListView().setItemChecked(i, true);
                                        }
                                    }
                                } else {
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                    if (indexSelected == 0) {
                                        for (int i = 0, length = mDayArr.length; i < length; i++) {
                                            ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                                        }
                                    } else {
                                        ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                                        seletedItems.clear();
                                    }
                                }

                            }
                        }).setPositiveButton(R.string.btn_ok_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                for (int i = 0; i < seletedItems.size(); i++) {
                                    mTvShowDay.setText(mTvShowDay.getText().toString() + " " + seletedItems.get(i));
                                }
                            }
                        }).setNegativeButton(R.string.btn_cancle_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).create();
                dialog.show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours = tpTime.getCurrentHour();
                int minutes = tpTime.getCurrentMinute();
                DatabaseHandler mDb = new DatabaseHandler(activity);
                SQLiteDatabase mSqlData = mDb.getWritableDatabase();
                Intent mNewService = new Intent(activity, AlarmClockService.class);
                mListener.setDataFormDialogToActivity(pos, new AlarmObj(edTitle.getText().toString(), hours, minutes, mTvShowDay.getText().toString()));
                ContentValues contentValues = new ContentValues();
                contentValues.put(TITLE, edTitle.getText().toString());
                contentValues.put(HOUR, hours);
                contentValues.put(MINUTE, minutes);
                contentValues.put(DAY_OF_WEEK, mTvShowDay.getText().toString());
                mSqlData.update(DATABASE_NAME, contentValues, String.valueOf("id =") + String.valueOf(pos + 1), null);
                mSqlData.close();
                mListener.sendBroadCastFormDialogToActivity();
                activity.stopService(mNewService);
                activity.startService(mNewService);
                addNewContact.dismiss();
            }
        });
        addNewContact.show();
    }


    public interface doSomeThingFormDialog {
        void addDataFormDialogToActivity(AlarmObj alarmObj);

        void sendBroadCastFormDialogToActivity();

        void setDataFormDialogToActivity(int pos, AlarmObj alarmObj);
    }
}
