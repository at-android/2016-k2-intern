package vn.asiantech.training;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmClockService extends Service {
    ArrayList<AlarmObj> a = new ArrayList<>();
    ArrayList<Integer> mangInt = new ArrayList<>();
    boolean first = false;
    BroadcastReceiver broadcastReceiver;
    private Calendar c;
    private Runnable mRun;
    private DatabaseHandler db;
    private SQLiteDatabase sqlData;
    private Context context;
    public AlarmClockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.alarm.CUSTOM_INTENT");
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
            int dayofweek = cursor.getInt(4);
            a.add(new AlarmObj(title, hour, minute, dayofweek));
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
/*     try {
         a = (ArrayList<AlarmObj>) intent.getSerializableExtra("a");
     }catch (Exception e){

     }*/
        final Handler mHandler = new Handler();
        mRun = new Runnable() {
            @Override
            public void run() {
                mangInt.clear();
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_WEEK);
                int second = c.get(Calendar.SECOND);
                int minute = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int nowtime = hour * 60 + minute;
                for (int i = 0; i < a.size(); i++) {
                    if (day == a.get(i).getDayofweek() || a.get(i).getDayofweek() == 0) {
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
        super.onDestroy();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), NotifiService.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringtone);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("BaoThuc")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setSound(sound)
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentIntent(contentIntent)
                .build();
        startForeground(1, notification);
        //  stopForeground(true);
    }

    public class MyReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            a.clear();
            a = (ArrayList<AlarmObj>) intent.getSerializableExtra("a");
        }
    }
}
