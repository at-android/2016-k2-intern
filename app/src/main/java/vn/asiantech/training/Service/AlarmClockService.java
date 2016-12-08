package vn.asiantech.training.Service;

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
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import vn.asiantech.training.Activities.DialogActivity;
import vn.asiantech.training.Database.DatabaseHandler;
import vn.asiantech.training.Object.AlarmObj;
import vn.asiantech.training.R;

public class AlarmClockService extends Service {
    boolean first = false;
    private ArrayList<AlarmObj> a = new ArrayList<>();
    private ArrayList<Integer> mangInt = new ArrayList<>();
    private BroadcastReceiver broadcastReceiver;
    private Calendar calendar;
    private Runnable mRun;
    private DatabaseHandler db;
    private SQLiteDatabase sqlData;
    private Context context;
    private MediaPlayer mediaPlayer;
    public AlarmClockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ringtone);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.alarm.CUSTOM_INTENT");
        intentFilter.addAction("com.alarm.STOP_MUSIC");
        broadcastReceiver = new MyReceive();
        registerReceiver(broadcastReceiver, intentFilter);
        context = getApplicationContext();
        db = new DatabaseHandler(context);
        sqlData = db.getReadableDatabase();
        String query = "select * from AlarmManager";
        Cursor cursor = sqlData.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String title = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minute = cursor.getInt(3);
            String dayofweek = cursor.getString(4);
            a.add(new AlarmObj(title, hour, minute, dayofweek));
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final Handler mHandler = new Handler();
        mRun = new Runnable() {
            @Override
            public void run() {
                mangInt.clear();
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int second = calendar.get(Calendar.SECOND);
                int minute = calendar.get(Calendar.MINUTE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int nowtime = hour * 60 + minute;

                for (int i = 0; i < a.size(); i++) {
                    boolean isCheck = false;
                    String[] days = a.get(i).getDayofweek().split(" ");
                    for (int a = 0; a < days.length; a++) {
                        if (days[a].equals(day + "")) {
                            isCheck = true;
                        }
                        if (days[a].equals("0")) {
                            isCheck = true;
                        }
                    }

                    if (isCheck == true) {
                        int check = (a.get(i).getHour() * 60) + a.get(i).getMinute();
                        if (check - nowtime > 0) {
                            mangInt.add(check - nowtime);
                        }
                    }
                }
                int mn;
                if (mangInt.size() == 0) {
                    mn = 1440 - nowtime;
                    Log.d("a", mn + "");
                } else {
                    mn = mangInt.get(0);
                    for (int i = 0; i < mangInt.size(); i++) {
                        if (mangInt.get(i) < mn) {
                            mn = mangInt.get(i);
                        }
                    }
                }
                mn = mn * 60000 - (second * 1000);
                mHandler.postDelayed(mRun, mn);
                if (first == false) {
                    first = true;
                } else {
                    showForegroundNotification();
                    if (mangInt.size() == 0) {
                        first = false;
                    }
                }
            }
        };
        mRun.run();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        stopForeground(true);
        mediaPlayer.release();
        super.onDestroy();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), DialogActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mediaPlayer.start();
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("BaoThuc")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent)
                .build();
        notification.flags |= Notification.FLAG_INSISTENT;
        startForeground(1, notification);
    }

    public class MyReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.alarm.CUSTOM_INTENT")) {
                a.clear();
                a = (ArrayList<AlarmObj>) intent.getSerializableExtra("a");
                Log.d("sad", action);
            }
            if (action.equals("com.alarm.STOP_MUSIC")) {
                mediaPlayer.release();
            }

        }
    }
}
