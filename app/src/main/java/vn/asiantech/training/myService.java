package vn.asiantech.training;

import android.app.Notification;
import android.app.NotificationManager;
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
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 4/12/2016.
 */

public class myService extends Service {
    public static final String ACTION = "vn.asiantech.training.CUSTOM_INTENT";
    private DatabaseHelper db;
    private Handler mHandler;
    private ArrayList<Time> ArrContentTime = new ArrayList<Time>();
    private ArrayList<Time> ArrFromDB = new ArrayList<Time>();
    private long mSecond;
    private long mTimeMin;
    private int mFlag;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DatabaseHelper(getBaseContext());
        this.registerReceiver(new AlarmReceiver(), new IntentFilter(ACTION));
    }

    public void getDataFromDB() {
        db.open();
        ArrFromDB = db.getData();
        ArrContentTime = new ArrayList<>();
        Log.i("DB size",ArrFromDB.size()+"");
//        Log.i("schedule in 1 day", ArrFromDB.get(0).getDate() + "");
        for (int i = 0; i < ArrFromDB.size(); i++) {
            String s = "";
            s = ArrFromDB.get(i).getDate();
            Log.i("s=", s + "");
            s.trim();
            String s1[] = s.split(" ");
            Log.i("s1Length=", s1.length + "");
            for (int i1 = 0; i1 < s1.length; i1++) {
                Time t = new Time();
                t.setDate(s1[i1]);
                t.setHour(ArrFromDB.get(i).getHour());
                t.setMinute(ArrFromDB.get(i).getMinute());
                ArrContentTime.add(t);
            }
        }
        Log.i("ArrContentSize", ArrContentTime.size() + "");
        db.close();
    }

    public void FindMinTime() {
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        mTimeMin = 0;
        for (int i = 0; i < ArrContentTime.size(); i++) {
            c.add(Calendar.DATE, (Integer.parseInt(ArrContentTime.get(i).getDate()) - Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
            Log.i("date-date", (Integer.parseInt(ArrContentTime.get(i).getDate()) - Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) + "");
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ArrContentTime.get(i).getHour()));
            c.set(Calendar.MINUTE, Integer.parseInt(ArrContentTime.get(i).getMinute()));

            long tmp = c.getTimeInMillis() - time - Calendar.getInstance().get(Calendar.SECOND);
            //them 7 ngay vao khi da reo
            if (tmp == 0 || tmp < 0) {
                Calendar c2 = Calendar.getInstance();
                c2.add(Calendar.DATE, 7);
                tmp += c2.getTimeInMillis();
            }
            if (mTimeMin == 0 || mTimeMin > tmp) {
                mTimeMin = tmp;
            }
        }
        Log.i("timeMin", mTimeMin + "");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        getDataFromDB();
//        FindMinTime();
        mSecond = 0;
        countSecond();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void countSecond() {
        getDataFromDB();
        FindMinTime();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacksAndMessages(null);
                mSecond += 1000;
                Log.i("second", mSecond + "");
                showForegroundNotification();
                if (mSecond == mTimeMin) {
                    mSecond = 0;
                    showNotification();
                    FindMinTime();
                }
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void showNotification() {
        //save data when show
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("You Have A Message, Sir")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)//co the cancel
                .setOngoing(true);//khong the keo qua de tat
        builder.setVibrate(new long[]{1000, 1000});

        // Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(this, myService.class);

        // Creates the PendingIntent
        //co the getBroadcast,getService
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyPendingIntent);
        // Notifications are issued by sending them to the
        // NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds an anonymous Notification object from the builder, and
        // passes it to the NotificationManager
        mNotificationManager.notify(0, builder.build());
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        Intent showTaskIntent = new Intent(getApplicationContext(), myService.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                1000,
                showTaskIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Today is an awesome day")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentIntent(contentIntent)
                .build();
        startForeground(1, notification);
        //  stopForeground(true);
    }

    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION)){
                Bundle bundle = intent.getBundleExtra("data");
                mFlag = bundle.getInt("Flag");
                if(mFlag>0){
                    mSecond=0;
                    countSecond();
                }
            }
        }
    }
}
