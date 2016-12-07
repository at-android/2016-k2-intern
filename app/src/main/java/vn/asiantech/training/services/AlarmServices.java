package vn.asiantech.training.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import vn.asiantech.training.MainActivity;
import vn.asiantech.training.R;
import vn.asiantech.training.databases.DatabaseAlarm;
import vn.asiantech.training.models.Alarm;

/**
 * Created by phuong on 05/12/2016.
 */

public class AlarmServices extends Service {
    ArrayList<Alarm> mAlarms;
    private DatabaseAlarm mDatabaseAlarm;
    private boolean flag = true;
    private Handler mHandler = new Handler();
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.phuong.ADD_INTENT")) {
                Bundle bundle = intent.getExtras();
                Alarm alarm = bundle.getParcelable(MainActivity.BROADCAST_KEY);
                mAlarms.add(alarm);
                Log.d("TAG111", mAlarms.toString());
                mHandler.removeCallbacksAndMessages(null);
                if (getTimeWaitSmallest(mAlarms) > 0) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //lap lai de post delay
                            showForegroundNotification();
                            if (getTimeWaitSmallest(mAlarms) > 0) {
                                mHandler.postDelayed(this, getTimeWaitSmallest(mAlarms));
                            }
                        }
                    }, getTimeWaitSmallest(mAlarms));
                }


            }
            if (intent.getAction().equals("com.phuong.EDIT_INTENT")) {
                Bundle bundle = intent.getExtras();
                Alarm alarm = bundle.getParcelable(MainActivity.BROADCAST_KEY);
                int position = intent.getIntExtra(MainActivity.INTENT_KEY_POSITION, 0);
                mAlarms.set(position, alarm);
                Log.d("TAG112", mAlarms.toString());
                mHandler.removeCallbacksAndMessages(null);
                if (getTimeWaitSmallest(mAlarms) > 0) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //lap lai de post delay
                            showForegroundNotification();
                            if (getTimeWaitSmallest(mAlarms) > 0) {
                                mHandler.postDelayed(this, getTimeWaitSmallest(mAlarms));
                            }
                        }
                    }, getTimeWaitSmallest(mAlarms));
                }
            }
            if (intent.getAction().equals("com.phuong.DELETE_INTENT")) {
                Bundle bundle = intent.getExtras();
                ArrayList<Integer> mPositions = bundle.getIntegerArrayList(MainActivity.BROADCAST_KEY);
                for (int i : mPositions) {
                    mAlarms.remove(i);
                }
                mHandler.removeCallbacksAndMessages(null);
                if (getTimeWaitSmallest(mAlarms) > 0) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showForegroundNotification();
                            if (getTimeWaitSmallest(mAlarms) > 0) {
                                mHandler.postDelayed(this, getTimeWaitSmallest(mAlarms));
                            }
                        }
                    }, getTimeWaitSmallest(mAlarms));
                }

            }

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mDatabaseAlarm = new DatabaseAlarm(getApplication());
        mDatabaseAlarm.open();
        registerReceiver(mBroadcastReceiver, new IntentFilter("com.phuong.ADD_INTENT"));
        registerReceiver(mBroadcastReceiver, new IntentFilter("com.phuong.EDIT_INTENT"));
        registerReceiver(mBroadcastReceiver, new IntentFilter("com.phuong.DELETE_INTENT"));
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {
        mAlarms = mDatabaseAlarm.getData();
        if (getTimeWaitSmallest(mAlarms) > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int mSecondsAlarm = getTimeWaitSmallest(mAlarms);
                    //lap lai de post delay
                    showForegroundNotification();
                    if (mSecondsAlarm > 0) {
                        mHandler.postDelayed(this, mSecondsAlarm);
                    }
                }
            }, getTimeWaitSmallest(mAlarms));
        }
        return START_STICKY;


    }

    // ham de lay time ngan nhat
    public int getTimeWaitSmallest(ArrayList<Alarm> alarms) {
        ArrayList<Integer> mMinsAlarmWait = new ArrayList<>();
        mMinsAlarmWait.clear();
        //get time hien tai
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        //so phut hien tai cua he thong
        int minsNow = minute + hour * 60;
        Log.d("minsNow", minsNow + "");
        int minsAlarm = 0;

        //so sanh ngay
        for (Alarm alarm : alarms) {
            if (("true").equals(String.valueOf(alarm.ismStatus()))) {
                String[] days = alarm.getmRepeart().split("[,]");
                for (int i = 0; i < days.length; i++) {
                    if (Integer.parseInt(days[i].trim()) == date) {
                        minsAlarm = Integer.parseInt(alarm.getmMin()) + 60 * (Integer.parseInt(alarm.getmHour()));
                        if (minsAlarm > minsNow)
                            mMinsAlarmWait.add(minsAlarm - minsNow);
                    }
                }
            }
        }

        int mSecondsAlarm = 0;
        //sap xep so phut tu nho den lon
        Collections.sort(mMinsAlarmWait);
        for (Integer i : mMinsAlarmWait) {
            Log.i("TAG11", i + "");
        }
        if (mMinsAlarmWait.size() > 0) {
            mSecondsAlarm = mMinsAlarmWait.get(0) * 60000;
        }

        return mSecondsAlarm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseAlarm.close();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), AlarmServices.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Wake Up ")
                .setSmallIcon(R.mipmap.ic_alarm)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setOngoing(false)
                .setContentIntent(contentIntent)
                .setVibrate(new long[]{1000, 1000})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();
        startForeground(1, notification);
    }
}
