package vn.asiantech.training.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.asiantech.training.R;
import vn.asiantech.training.activities.DialogActivity;
import vn.asiantech.training.database.DatabaseHandler;
import vn.asiantech.training.object.AlarmObj;

import static vn.asiantech.training.activities.MainActivity.LIST_ARRAY_NAME;

public class AlarmClockService extends Service {
    boolean isFirst;
    int time;
    private List<AlarmObj> arrAlarm = new ArrayList<>();
    private List<Integer> arrAlarmWait = new ArrayList<>();
    private BroadcastReceiver mBroadcastReceiver;
    private Calendar mCalendar;
    private Runnable mRunable;
    private DatabaseHandler mDb;
    private SQLiteDatabase mSqlData;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    public AlarmClockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(String.valueOf(R.string.action_custom_intent));
        intentFilter.addAction(String.valueOf(R.string.action_stop_music));
        mBroadcastReceiver = new MyReceive();
        registerReceiver(mBroadcastReceiver, intentFilter);
        mContext = getApplicationContext();
        mDb = new DatabaseHandler(mContext);
        mSqlData = mDb.getReadableDatabase();
        String query = "select * from AlarmManager";
        Cursor cursor = mSqlData.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minute = cursor.getInt(3);
            String dayofweek = cursor.getString(4);
            arrAlarm.add(new AlarmObj(title, hour, minute, dayofweek));
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final Handler mHandler = new Handler();
        mRunable = new Runnable() {
            @Override
            public void run() {
                arrAlarmWait.clear();
                mCalendar = Calendar.getInstance();
                int day = mCalendar.get(Calendar.DAY_OF_WEEK);
                int second = mCalendar.get(Calendar.SECOND);
                int minute = mCalendar.get(Calendar.MINUTE);
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int nowtime = hour * 60 + minute;

                for (int i = 0, lenght = arrAlarm.size(); i < lenght; i++) {
                    boolean isCheck = false;
                    String[] days = arrAlarm.get(i).getDayofweek().split(" ");
                    for (int a = 0, size = days.length; a < size; a++) {
                        if (String.valueOf(day).equals(days[a])) {
                            isCheck = true;
                        }
                        if (("0").equals(days[a])) {
                            isCheck = true;
                        }
                    }

                    if (isCheck) {
                        int check = (arrAlarm.get(i).getHour() * 60) + arrAlarm.get(i).getMinute();
                        if (check - nowtime > 0) {
                            arrAlarmWait.add(check - nowtime);
                        }
                    }
                }
                if (arrAlarmWait.size() == 0) {
                    time = 1440 - nowtime;
                } else {
                    time = arrAlarmWait.get(0);
                    for (int i = 0; i < arrAlarmWait.size(); i++) {
                        if (arrAlarmWait.get(i) < time) {
                            time = arrAlarmWait.get(i);
                        }
                    }
                }
                time = time * 60000 - (second * 1000);
                mHandler.postDelayed(mRunable, time);
                if (!isFirst) {
                    isFirst = true;
                } else {
                    showForegroundNotification();
                    if (arrAlarmWait.size() == 0) {
                        isFirst = false;
                    }
                }
            }
        };
        mRunable.run();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        stopForeground(true);
        mMediaPlayer.release();
        super.onDestroy();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        Intent showTaskIntent = new Intent(getApplicationContext(), DialogActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.app_name))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent)
                .build();
        notification.flags |= Notification.FLAG_INSISTENT;
        startForeground(1, notification);
        mMediaPlayer.start();
    }

    public class MyReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(R.string.action_custom_intent)) {
                arrAlarm.clear();
                arrAlarm = (ArrayList<AlarmObj>) intent.getSerializableExtra(LIST_ARRAY_NAME);
            }
            if (action.equals(String.valueOf(R.string.action_stop_music))) {
                mMediaPlayer.release();
            }

        }
    }
}
