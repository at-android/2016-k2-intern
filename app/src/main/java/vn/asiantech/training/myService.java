package vn.asiantech.training;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import java.util.Collections;

/**
 * Created by Administrator on 4/12/2016.
 */

public class myService extends Service {
    private int mSecond = 0;
    private int mMinute = 0;
    private int lengthOfMinutes = 0;
    private int mCurrentHour;
    private int mCurrentMinute;
    private int mCurrentDayOfWeek;
    private int mHourReceive;
    private int mMinuteReceive;
    private int mDayReceive;
    private ArrayList<Day> mArr = new ArrayList<Day>();
    private ArrayList<Integer> mArrDayOfWeek = new ArrayList<Integer>();
    private ArrayList<Integer> mArrLengthOfMinute = new ArrayList<Integer>();
    private Handler mHandler;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Calendar c = Calendar.getInstance();
        mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
        mCurrentMinute = c.get(Calendar.MINUTE);
        mCurrentDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle b = intent.getBundleExtra("data");
        mHourReceive = b.getInt("hour");
        mMinuteReceive = b.getInt("minute");
        mArr = b.getParcelableArrayList("array");
        //doi gia tri tu string sang int
        changeDateToValue(mArr);
        Log.i("mArrsize",mArr.get(0).getNameOfDay()+"");
        //them vao mang tinh thoi gian giua cac ngay
        getDataForLengthOfMinuteArray(mArrDayOfWeek);
        Log.i("ArrayOfWeek",mArrDayOfWeek.size()+"");
        Log.i("currentHour", mCurrentHour + "");
        Log.i("currentMinute", mCurrentMinute + "");
        Log.i("ServiceMinute", mMinuteReceive + "");
        Log.i("ServiceHour", mHourReceive + "");
        countSecond();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //doi ngay ra gia tri
    public void changeDateToValue(ArrayList<Day> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getNameOfDay().equals("Sunday")) {
                mArrDayOfWeek.add(1);
            }
            else if (arr.get(i).getNameOfDay().equals("Monday")) {
                mArrDayOfWeek.add(2);
            }
            else if (arr.get(i).getNameOfDay().equals("Tuesday")) {
                mArrDayOfWeek.add(3);
            }
            else if (arr.get(i).getNameOfDay().equals("Wednesday")) {
                mArrDayOfWeek.add(4);
            }
            else if (arr.get(i).getNameOfDay().equals("Thursday")) {
                mArrDayOfWeek.add(5);
            }
            else if (arr.get(i).getNameOfDay().equals("Friday")) {
                mArrDayOfWeek.add(6);
            }
            else if (arr.get(i).getNameOfDay().equals("Saturday")) {
                mArrDayOfWeek.add(7);
            }
        }
    }

    public void getDataForLengthOfMinuteArray(ArrayList<Integer> arr){
        for(int i=0;i<arr.size();i++){
            lengthOfMinutes = (arr.get(i)-mCurrentDayOfWeek)*24*60 + (mHourReceive - mCurrentHour) * 60 + (mMinuteReceive - mCurrentMinute);
            mArrLengthOfMinute.add(lengthOfMinutes);
        }
        Collections.sort(mArrLengthOfMinute);
    }

    public void countSecond() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSecond++;
                Log.i("second", mSecond + "");
                showForegroundNotification();
                mHandler.postDelayed(this, 1000);
                if (mSecond == 60) {
                    mMinute++;
                    mSecond = 0;
                    if (mMinute == lengthOfMinutes) {
                        showNotification();
                    }
                }
            }
        }, 1000);
    }

    private void showNotification() {
        //save data when show
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("abcddddd")
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
                .setContentText("test aaaaa")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentIntent(contentIntent)
                .build();
        startForeground(1, notification);
        //  stopForeground(true);
    }
}
